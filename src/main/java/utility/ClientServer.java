package utility;

import config.PropertiesLoader;
import messages.http.*;
import messages.webSocket.SocketRoute;
import messages.webSocket.client.ClientJoinEventSMsg;
import messages.webSocket.client.ClientPlaylistStateSMsg;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.jetty.JettyWebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ClientServer {

    private MessageHttpSending messageHttpSending = new MessageHttpSending();
    private MessageWebSocketSending messageWebSocketSending = new MessageWebSocketSending();

    private final CopyOnWriteArrayList<String> usersList = new CopyOnWriteArrayList();
    private ExecutorService executorService;


    public static AtomicLong timer = new AtomicLong(0);
    public static boolean isReadyToStart = false;


    private String eventId;
    private String mediaId;
    private String playlistId;
    private JSON json;
    private PropertiesLoader propertiesLoader;
    private final long listenTime = 3 * 60 * 1000;
    public static AtomicInteger count = new AtomicInteger();
    public static AtomicInteger confirmedEventStart = new AtomicInteger();
    public static AtomicInteger confirmedJoinPlaylist = new AtomicInteger();
    public static AtomicInteger confirmedJoinEvent = new AtomicInteger();
    private String url;
    private int usersCount;
    private int countFrom;

    public ClientServer(String usersCount, String countFrom, String eventId ) {
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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveUsersToDB(){

        long start = System.currentTimeMillis();
        if (usersCount > 0) {
            executorService = Executors.newFixedThreadPool(usersCount);

            List<Future<String>> futures = new ArrayList<>();

            for (int i = countFrom; i < (usersCount + countFrom); i++) {

                futures.add(adduser(i));

//            CreateUserDto userDto = new CreateUserDto(i);
//
//            try {
//                String sJson = json.serialize(userDto);
//                GetNewUserIdDto userWitId = (GetNewUserIdDto)messageHttpSending.SendPostMessageToAnotherServer(sJson, "/auth/registration", GetNewUserIdDto.class);
//                if (userWitId != null){
//                    usersList.add(userWitId.userId);
//                }
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
                    System.out.println("after future saveUsersToDB");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
            long time = System.currentTimeMillis() - start;
        }

    }

    private Future<String> adduser(int cnt){
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();
        executorService.submit(()->{


            CreateUserDto userDto = new CreateUserDto(cnt);

            try {
                String sJson = json.serialize(userDto);
                GetNewUserIdDto userWitId = (GetNewUserIdDto)messageHttpSending.SendPostMessageToAnotherServer(sJson, "/auth/registration", GetNewUserIdDto.class);
                if (userWitId != null){
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

    public void getEvents(){

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

    private Future<String> getEvent(String userId){
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();
        executorService.submit(()->{

            try {
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/eventcrud/getevent/%s/%s", userId, eventId), GetEventDto.class );

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

    public void bookEvents(){

//        long start = System.currentTimeMillis();
        if (usersList.size() > 0) {
//            executorService = Executors.newFixedThreadPool(sendMessageusersList.size());
            List<Future<String>> futures = new ArrayList<>();

            for (String userId : usersList) {

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
                    System.out.println("after future removeUsers" +
                            "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
//        long time = System.currentTimeMillis() - start;
        }

    }

    private Future<String> bookEvent(String userId){
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        executorService.submit(()->{
            UserIdEventId userIdEventId = new UserIdEventId(userId, eventId);
            try {

                String sJson = json.serialize(userIdEventId);
                ResultStatus setEventDto = (ResultStatus)messageHttpSending.SendPostMessageToAnotherServer(sJson,"/eventcrud/bookevent", ResultStatus.class );


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

//    public void getPlaylists(){
//        long start = System.currentTimeMillis();
//        for (String userId : usersList){
//
//            try {
//                messageHttpSending.SendGetMessageToAnotherServer(String.format("/media/playlists/%s", mediaId), ResultStatus.class );
//
//            } catch (IOException e) {
//                System.out.printf(e.getMessage());
//                e.printStackTrace();
//            }
//        }
//        long time = System.currentTimeMillis() - start;
//    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void removeUsers(){

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

    private Future<String> removeUser(String userId){
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();
        executorService.submit(()->{
            try {
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeuser/%s", userId), Object.class );

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

    public void joinEvent(){

        if (usersList.size() > 0){
//            executorService = Executors.newFixedThreadPool(usersList.size());

            List<Future<String>> futures = new ArrayList<>();

            long userCount = 0;
            for (String userId : usersList){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                futures.add(listenEvent(userId, userCount));
                userCount++;

            }

            futures.forEach((f)-> {
                try {
                    String result = f.get();
                    System.out.println(result);
                    System.out.println("after future get");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });

        }
    }



    private Future<String> listenEvent(String userId, long userCount){

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();


        executorService.submit(()->{


            connectAndSend(userId, userCount);

            completableFuture.complete("listen finished listenEvent");
            return null;

        });

        System.out.println("return listenEvent");
        return completableFuture;
    }


    private void connectAndSend(String userId, long userCount){
        try {

            boolean isError = false;

            JettyWebSocketClient jettyWebSocketClient = new JettyWebSocketClient();
            WebSocketHandlerImpl webSocketHandler = new WebSocketHandlerImpl(eventId, userId, playlistId);
            jettyWebSocketClient.start();
            jettyWebSocketClient.doHandshake(webSocketHandler, null, new URI(url)).addCallback(new ListenableFutureCallback<WebSocketSession>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("onFailure");
                }

                @Override
                public void onSuccess(WebSocketSession webSocketSession) {
                    System.out.println("onSuccess");
                    ClientJoinEventSMsg clientJoinEventSMsg = new ClientJoinEventSMsg();
                    clientJoinEventSMsg.setEventId(eventId);
                    clientJoinEventSMsg.setUserId(userId);
                    clientJoinEventSMsg.setNeedConfirmation(false);
                    clientJoinEventSMsg.setRoute(SocketRoute.user_join_event);
                    try {
                        webSocketHandler.sendMessage(/*json.serialize(clientJoinEventSMsg)*/ new ObjectMapper().writeValueAsString(clientJoinEventSMsg));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
                while (!isReadyToStart && timer.get() == 0){
                    try {
//                        System.out.println("System.currentTimeMillis() - timer.get(): " + (System.currentTimeMillis() - timer.get()));
//                        System.out.println("userCount *10 " + (userCount *10));
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            while ((System.currentTimeMillis() - timer.get()) <= (userCount *20)){
                try {
//                        System.out.println("System.currentTimeMillis() - timer.get(): " + (System.currentTimeMillis() - timer.get()));
                        System.out.println("not ready");
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

//            System.out.println("!webSocketHandler.isReadyToStart && (System.currentTimeMillis() - timer.get()) <= (userCount *10) : "
//                    + (!webSocketHandler.isReadyToStart && (System.currentTimeMillis() - timer.get()) <= (userCount *10)));
//            }

            ClientPlaylistStateSMsg clientPlaylistStateSMsg = new ClientPlaylistStateSMsg();
            clientPlaylistStateSMsg.setEventId(eventId);
            clientPlaylistStateSMsg.setNeedConfirmation(false);
            clientPlaylistStateSMsg.setPlaylistId(playlistId);
            clientPlaylistStateSMsg.setRoute(SocketRoute.playlist_state);
            clientPlaylistStateSMsg.setUserId(userId);
//
            long startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) < listenTime ){
                try {

                    if (webSocketHandler.playlistState == true){
                        webSocketHandler.playlistState = false;
                        webSocketHandler.sendMessage(json.serialize(clientPlaylistStateSMsg));
                    }
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            webSocketHandler.connectionClose();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void removeAllUsers(){

        long start = System.currentTimeMillis();

        executorService = Executors.newCachedThreadPool();

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i <= usersCount; i++ ){

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

        futures.forEach((f)-> {
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
        long time = System.currentTimeMillis() - start;
    }

    private Future<String> removeAllUser(int cnt){
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();
        executorService.submit(()->{
            String user = "firstname" + cnt + "/lastname" + cnt;

            try {
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeallusersrefund/" + user), Object.class );
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeallusers/" + user), Object.class );

            } catch (IOException e) {
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }

        });

        System.out.println("return removeAllUser");
        return completableFuture;
    }
}
