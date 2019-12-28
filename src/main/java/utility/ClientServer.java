package utility;

import config.PropertiesLoader;
import messages.http.*;
import messages.webSocket.SocketRoute;
import messages.webSocket.client.ClientJoinEventSMsg;
import messages.webSocket.client.ClientPlaylistStateSMsg;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.jetty.JettyWebSocketClient;
import sun.security.krb5.internal.TGSRep;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ClientServer {

    private MessageHttpSending messageHttpSending = new MessageHttpSending();
    private MessageWebSocketSending messageWebSocketSending = new MessageWebSocketSending();

    private Map<JettyWebSocketClient, WebSocketHandlerImpl> socketClientsMap = new ConcurrentHashMap<>();


    private final CopyOnWriteArrayList<String> usersList = new CopyOnWriteArrayList();
    private ExecutorService executorService;

    private JettyWebSocketClient jettyWebSocketClient;
    private WebSocketHandlerImpl webSocketHandler ;


    public static AtomicLong timer = new AtomicLong(0);
    public static boolean isReadyToStart = false;

    long userCount = 0;


    private String eventId;
    private String mediaId;
    private String playlistId;
    private JSON json;
    private PropertiesLoader propertiesLoader;
    private final long listenTime = 3 * 60 * 1000;
    public static AtomicLong startTime = new AtomicLong(0);
    public static AtomicInteger count = new AtomicInteger();
    public static AtomicInteger succesConnection = new AtomicInteger();
    public static AtomicInteger confirmedJoinPlaylist = new AtomicInteger();
    public static AtomicInteger confirmedJoinEvent = new AtomicInteger();
    private String url;
    private int usersCount;
    private int countFrom;

    public ClientServer(String usersCount, String countFrom, String eventId) {
        this.propertiesLoader = new PropertiesLoader();
        this.json = new JSON();
        this.mediaId = propertiesLoader.getProperty("mediaId");
//        this.eventId = propertiesLoader.getProperty("eventId_1");
        this.eventId = eventId;
        this.playlistId = propertiesLoader.getProperty("playlistId");
        this.url = propertiesLoader.getProperty("websocketUrl");
        this.usersCount = Integer.parseInt(usersCount);
        this.countFrom = Integer.parseInt(countFrom);

    }

    public void joinEvent() {

        jettyWebSocketClient = new JettyWebSocketClient();
        webSocketHandler = new WebSocketHandlerImpl(eventId, playlistId, executorService);
        jettyWebSocketClient.start();

        if (usersList.size() > 0) {
//            executorService = Executors.newFixedThreadPool(usersList.size());
            executorService = Executors.newCachedThreadPool();

            List<Future<String>> futures = new ArrayList<>();


            for (String userId : usersList) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("usersList size: " + usersList.size());
                futures.add(listenEvent(userId, userCount));
                userCount++;

            }

            futures.forEach((f) -> {
                try {
                    String result = f.get();
                    System.out.println(result);
                    System.out.println("after future joinEvent");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    private Future<String> listenEvent(String userId, long userCount) {

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        executorService.submit(() -> {

//            connectAndSend(userId, userCount);

            connectUsersAddToMapAndJoinToEvent(userId, userCount);

            completableFuture.complete("listen finished listenEvent");
            return null;

        });

        System.out.println("return listenEvent");
        return completableFuture;
    }

    private void connectUsersAddToMapAndJoinToEvent(String userId, long userCount) {

        try {

            System.out.println("user number: " + userCount);
            jettyWebSocketClient.doHandshake(webSocketHandler, null, new URI(url)).addCallback(new ListenableFutureCallback<WebSocketSession>() {


                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("onFailure");
                }

                @Override
                public void onSuccess(WebSocketSession webSocketSession) {
                    System.out.println(succesConnection.incrementAndGet());
                    System.out.println("onSuccess");
                    ClientJoinEventSMsg clientJoinEventSMsg = new ClientJoinEventSMsg();
                    clientJoinEventSMsg.setEventId(eventId);
                    clientJoinEventSMsg.setUserId(userId);
                    clientJoinEventSMsg.setNeedConfirmation(false);
                    clientJoinEventSMsg.setRoute(SocketRoute.user_join_event);
                    try {
                        webSocketHandler.sendMessage(webSocketSession, new ObjectMapper().writeValueAsString(clientJoinEventSMsg));
                        webSocketHandler.socketSessionMap.put(webSocketSession, userId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
//            jettyWebSocketClient.stop();
            return;
        }
//        socketClientsMap.put(jettyWebSocketClient, webSocketHandler);

    }

    public void startAllUsersToGetPlaylistState() {

//        long startTime = System.currentTimeMillis();
//        while (!isReadyToStart && timer.get() == 0){
//            System.out.println("not started");
//        }
//
//        List<Future<String>> futures = new ArrayList<>();
//
//        socketClientsMap.forEach((k, v) -> {
//
//            futures.add(getPlaylistState(v, v.userId));
//
//        });
//
//        futures.forEach((f) -> {
//            try {
//                String result = f.get();
//                System.out.println(result);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        });

        while (startTime.get() == 0){
//            System.out.println("not started");
        }

        while ((System.currentTimeMillis() - startTime.longValue()) < listenTime) {

        }

        // close connection after test end
//        socketClientsMap.forEach((k, v) -> {
//            v.connectionClose();
//            k.stop();
//        });

        webSocketHandler.socketSessionMap.forEach((k, v) -> {
            try {
                k.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

//    private Future<String> getPlaylistState(WebSocketHandlerImpl webSocketHandler, String userId) {
//
//        CompletableFuture<String> completableFuture
//                = new CompletableFuture<>();
//
//        executorService.submit(() -> {
//            if (!isReadyToStart && timer.get() == 0) {
//                completableFuture.complete("not started");
//                return new CompletableFuture<>();
//            } else if ((System.currentTimeMillis() - timer.get()) <= (userCount * 20)) {
//                completableFuture.complete("not ready");
//                return new CompletableFuture<>();
//            }
//
//            ClientPlaylistStateSMsg clientPlaylistStateSMsg = new ClientPlaylistStateSMsg();
//            clientPlaylistStateSMsg.setEventId(eventId);
//            clientPlaylistStateSMsg.setNeedConfirmation(false);
//            clientPlaylistStateSMsg.setPlaylistId(playlistId);
//            clientPlaylistStateSMsg.setRoute(SocketRoute.playlist_state);
//            clientPlaylistStateSMsg.setUserId(userId);
//
//            if (webSocketHandler.playlistState == true) {
//                webSocketHandler.playlistState = false;
//                webSocketHandler.sendMessage(json.serialize(clientPlaylistStateSMsg));
//            }
//            completableFuture.complete("listen finished adduser");
//            return null;
//        });
//
//        System.out.println("return getplaylist state");
//        return completableFuture;
//
//    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveUsersToDB() {

        if (usersCount > 0) {
//            executorService = Executors.newFixedThreadPool(1000);
            executorService = Executors.newCachedThreadPool();

            List<Future<String>> futures = new ArrayList<>();

            for (int i = countFrom; i < (usersCount + countFrom); i++) {

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                futures.add(adduser(i));

            }

            futures.forEach((f) -> {
                try {
                    String result = f.get();
                    System.out.println(result);
                    System.out.println("after future saveUsersToDB");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private Future<String> adduser(int cnt) {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        executorService.submit(() -> {

            CreateUserDto userDto = new CreateUserDto(cnt);

            try {
                String sJson = json.serialize(userDto);
                GetNewUserIdDto userWitId = (GetNewUserIdDto) messageHttpSending.SendPostMessageToAnotherServer(sJson, "/auth/registration", GetNewUserIdDto.class);
                if (userWitId != null) {
                    usersList.add(userWitId.userId);
                    System.out.println("addUser");
                }

            } catch (IOException e) {
                System.out.printf(e.getMessage());
//                e.printStackTrace();
            }

            completableFuture.complete("listen finished adduser");
            return null;

        });

        System.out.println("return addusers");
        return completableFuture;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public void getEvents() {

        long start = System.currentTimeMillis();
        if (usersList.size() > 0) {
//            executorService = Executors.newFixedThreadPool(usersList.size());
            List<Future<String>> futures = new ArrayList<>();

            for (String userId : usersList) {

                futures.add(getEvent(userId));
//
//            try {
//                messageHttpSending.SendGetMessageToAnotherServer(String.format("/eventcrud/getevent/%s/%s", userId, eventId), GetEventDto.class );
//
//            } catch (IOException e) {
//                System.out.printf(e.getMessage());
////                e.printStackTrace();
//            }
            }

            futures.forEach((f) -> {
                try {
                    String result = f.get();
                    System.out.println(result);
                    System.out.println("after future getEvent");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
            long time = System.currentTimeMillis() - start;
        }

    }

    private Future<String> getEvent(String userId) {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();
        executorService.submit(() -> {

            try {
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/eventcrud/getevent/%s/%s", userId, eventId), GetEventDto.class);

            } catch (IOException e) {
                System.out.printf(e.getMessage());
//                e.printStackTrace();
            }
            completableFuture.complete("listen finished getEvent");
            return null;

        });

        System.out.println("return getEvent");
        return completableFuture;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void bookEvents() {

//        long start = System.currentTimeMillis();
        if (usersList.size() > 0) {
//            executorService = Executors.newFixedThreadPool(sendMessageusersList.size());
            List<Future<String>> futures = new ArrayList<>();

            for (String userId : usersList) {

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                futures.add(bookEvent(userId));

//            UserIdEventId userIdEventId = new UserIdEventId(userId, eventId);
//            try {
//
//                String sJson = json.serialize(userIdEventId);
//                ResultStatus setEventDto = (ResultStatus)messageHttpSending.SendPostMessageToAnotherServer(sJson,"/eventcrud/bookevent", ResultStatus.class );
//
//
//            } catch (IOException e) {
//                System.out.printf(e.getMessage());
////                e.printStackTrace();
//            }
            }

            futures.forEach((f) -> {
                try {
                    String result = f.get();
                    System.out.println(result);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
//        long time = System.currentTimeMillis() - start;
        }

    }

    private Future<String> bookEvent(String userId) {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        executorService.submit(() -> {
            UserIdEventId userIdEventId = new UserIdEventId(userId, eventId);
            try {

                String sJson = json.serialize(userIdEventId);
                ResultStatus setEventDto = (ResultStatus) messageHttpSending.SendPostMessageToAnotherServer(sJson, "/eventcrud/bookevent", ResultStatus.class);


            } catch (IOException e) {
                System.out.printf(e.getMessage());
//                e.printStackTrace();
            }
            completableFuture.complete("listen finished bookEvent");
            return null;

        });

//        System.out.println("return addusers bookEvent");
        return completableFuture;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void removeUsers() {

        long start = System.currentTimeMillis();
        if (usersList.size() > 0) {
//            executorService = Executors.newFixedThreadPool(usersList.size());
            List<Future<String>> futures = new ArrayList<>();
            for (String userId : usersList) {

                futures.add(removeUser(userId));
//
//            try {
//                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeuser/%s", userId), Object.class );
//
//            } catch (IOException e) {
//                System.out.printf(e.getMessage());
//                e.printStackTrace();
//            }
            }

            futures.forEach((f) -> {
                try {
                    String result = f.get();
                    System.out.println(result);
                    System.out.println("after future removeUsers" +
                            "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
            long time = System.currentTimeMillis() - start;
        }

    }

    private Future<String> removeUser(String userId) {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();
        executorService.submit(() -> {
            try {
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeuser/%s", userId), Object.class);

            } catch (IOException e) {
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }
            completableFuture.complete("listen finished removeUser");
            return null;

        });

        System.out.println("return removeUser");
        return completableFuture;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void removeAllUsers() {

//        executorService = Executors.newCachedThreadPool();
        executorService = Executors.newFixedThreadPool(1000);

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i <= usersCount; i++) {

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            futures.add(removeAllUser(i));

//            String user = "firstname" + i + "/lastname" + i;
//
//            try {
//                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeallusersrefund/" + user), Object.class );
//                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeallusers/" + user), Object.class );
//
//            } catch (IOException e) {
//                System.out.printf(e.getMessage());
//                e.printStackTrace();
//            }
        }

        futures.forEach((f) -> {
            try {
                String result = f.get();
                System.out.println(result);
                System.out.println("after future remove all users");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private Future<String> removeAllUser(int cnt) {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();
        executorService.submit(() -> {
            String user = "firstname" + cnt + "/lastname" + cnt;

            try {
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeallusersrefund/" + user), Object.class);
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeallusers/" + user), Object.class);

            } catch (IOException e) {
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }

        });

        System.out.println("return removeAllUser");
        return completableFuture;
    }

    //    private Future<String> listenEvent(String userId, long userCount) {
//
//        CompletableFuture<String> completableFuture
//                = new CompletableFuture<>();
//
//
//        executorService.submit(() -> {
//
//            connectAndSend(userId, userCount);
//
//            completableFuture.complete("listen finished listenEvent");
//            return null;
//
//        });
//
//        System.out.println("return listenEvent");
//        return completableFuture;
//    }

//    private void connectAndSend(String userId, long userCount) {
//        try {
//
//            boolean isError = false;
//
//            JettyWebSocketClient jettyWebSocketClient = new JettyWebSocketClient();
//            WebSocketHandlerImpl webSocketHandler = new WebSocketHandlerImpl(eventId, userId, playlistId);
//            jettyWebSocketClient.start();
//            jettyWebSocketClient.doHandshake(webSocketHandler, null, new URI(url)).addCallback(new ListenableFutureCallback<WebSocketSession>() {
//
//
//
//                @Override
//                public void onFailure(Throwable throwable) {
//                    System.out.println("onFailure");
//                }
//
//                @Override
//                public void onSuccess(WebSocketSession webSocketSession) {
//                    System.out.println("onSuccess");
//                    ClientJoinEventSMsg clientJoinEventSMsg = new ClientJoinEventSMsg();
//                    clientJoinEventSMsg.setEventId(eventId);
//                    clientJoinEventSMsg.setUserId(userId);
//                    clientJoinEventSMsg.setNeedConfirmation(false);
//                    clientJoinEventSMsg.setRoute(SocketRoute.user_join_event);
//                    try {
//                        webSocketHandler.sendMessage(/*json.serialize(clientJoinEventSMsg)*/ new ObjectMapper().writeValueAsString(clientJoinEventSMsg));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//            while (!isReadyToStart && timer.get() == 0){
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            while ((System.currentTimeMillis() - timer.get()) <= (userCount *20)){
//                try {
////                        System.out.println("System.currentTimeMillis() - timer.get(): " + (System.currentTimeMillis() - timer.get()));
//                    System.out.println("not ready");
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            ClientPlaylistStateSMsg clientPlaylistStateSMsg = new ClientPlaylistStateSMsg();
//            clientPlaylistStateSMsg.setEventId(eventId);
//            clientPlaylistStateSMsg.setNeedConfirmation(false);
//            clientPlaylistStateSMsg.setPlaylistId(playlistId);
//            clientPlaylistStateSMsg.setRoute(SocketRoute.playlist_state);
//            clientPlaylistStateSMsg.setUserId(userId);
////
//            long startTime = System.currentTimeMillis();
//            while ((System.currentTimeMillis() - startTime) < listenTime ){
//                try {
//
//                    if (webSocketHandler.playlistState == true){
//                        webSocketHandler.playlistState = false;
//                        webSocketHandler.sendMessage(json.serialize(clientPlaylistStateSMsg));
//                    }
//                    Thread.sleep(5000);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            webSocketHandler.connectionClose();
//
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }

}
