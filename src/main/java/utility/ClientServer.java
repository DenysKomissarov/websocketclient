package utility;

import config.PropertiesLoader;
import handlers.TestMessageHandler;
import handlers.impls.*;
import messages.http.*;
import messages.webSocket.SocketRoute;
import messages.webSocket.WebSocketMessages;
import messages.webSocket.client.ClientDeliveryConfirmationSMsg;
import messages.webSocket.client.ClientJoinEventSMsg;
import messages.webSocket.client.ClientJoinPlaylistSMsg;
import messages.webSocket.client.ClientPlaylistStateSMsg;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.jetty.JettyWebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientServer {

    private MessageHttpSending messageHttpSending = new MessageHttpSending();
    private MessageWebSocketSending messageWebSocketSending = new MessageWebSocketSending();

    private final CopyOnWriteArrayList<String> usersList = new CopyOnWriteArrayList();
    private ExecutorService executorService;


    private String eventId_1;
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
    private final int usersCount = 801;

    public ClientServer() {
        this.propertiesLoader = new PropertiesLoader();
        this.json = new JSON();
        this.mediaId = propertiesLoader.getProperty("mediaId");
        this.eventId_1 = propertiesLoader.getProperty("eventId_1");
        this.playlistId = propertiesLoader.getProperty("playlistId");
        this.url = propertiesLoader.getProperty("websocketUrl");

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveUsersToDB(){

        long start = System.currentTimeMillis();
        if (usersCount > 0) {
            executorService = Executors.newFixedThreadPool(usersCount);

            List<Future<String>> futures = new ArrayList<>();

            for (int i = 0; i < usersCount; i++) {

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
//                messageHttpSending.SendGetMessageToAnotherServer(String.format("/eventcrud/getevent/%s/%s", userId, eventId_1), GetEventDto.class );
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
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/eventcrud/getevent/%s/%s", userId, eventId_1), GetEventDto.class );

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
//            executorService = Executors.newFixedThreadPool(usersList.size());
            List<Future<String>> futures = new ArrayList<>();

            for (String userId : usersList) {

                futures.add(bookEvent(userId));

//            UserIdEventId userIdEventId = new UserIdEventId(userId, eventId_1);
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
            UserIdEventId userIdEventId = new UserIdEventId(userId, eventId_1);
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

        System.out.println("return addusers bookEvent");
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

            for (String userId : usersList){

                futures.add(listenEvent(userId));

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



    private Future<String> listenEvent(String userId){

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();


        executorService.submit(()->{

            URI uri = null;
            try {
//                System.out.println("before bookEvent");
//                bookEvent(userId);
//                System.out.println("after bookEvent");
//                System.out.println("thread " + count.getAndIncrement());
//                uri = new URI("ws://localhost:8080/echo");
//                uri = new URI("wss://uandidance-events.com/echo");
                JettyWebSocketClient jettyWebSocketClient = new JettyWebSocketClient();
                WebSocketHandlerImpl webSocketHandler = new WebSocketHandlerImpl(eventId_1, userId, playlistId);
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
                        clientJoinEventSMsg.setEventId(eventId_1);
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

               /* while (!webSocketHandler.isSucess){
                    Thread.sleep(10);
//                    System.out.println("wait");
                }*/


//                System.out.println("before connect");
//                WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(uri);
//                System.out.println("after connect");


//                UserJoinEventMessageHandlerImpl messageHandler = new UserJoinEventMessageHandlerImpl();
//                EventStartMessageHandlerImpl messageHandler = new EventStartMessageHandlerImpl();
//
                // add listener
//                clientEndPoint.addMessageHandler(messageHandler);
//
//                while (!webSocketHandler.route.equals("user_join_event")){
//                    Thread.sleep(10);
////                    System.out.println("wait");
//
//                }


                //user join event
                /*ClientJoinEventSMsg clientJoinEventSMsg = new ClientJoinEventSMsg();
                clientJoinEventSMsg.setEventId(eventId_1);
                clientJoinEventSMsg.setUserId(userId);
                clientJoinEventSMsg.setNeedConfirmation(false);
                clientJoinEventSMsg.setRoute(SocketRoute.user_join_event);
                webSocketHandler.sendMessage(*//*json.serialize(clientJoinEventSMsg)*//* new ObjectMapper().writeValueAsString(clientJoinEventSMsg));
*/


//                clientEndPoint.sendMessage("{\"route\":\"user_join_event\", \"user_id\":\"" + userId + "\", \"is_need_confirmation\":1, \"event_id\":\"" + eventId_1 +"\"}");
//                clientEndPoint.sendMessage(json.serialize(clientJoinEventSMsg));
//                System.out.println("user join event send " +  userId);


                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//                while (!webSocketHandler.route.equals("user_join_event")){
//                    Thread.sleep(10);
////                    System.out.println("wait");
//
//                }
//
////                System.out.println("user join event received " +  userId);
//
//
//                //confirmation user join event
//
//
//                ClientDeliveryConfirmationSMsg deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
//                deliveryConfirmationSMsg.setEventId(eventId_1);
//                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
//                deliveryConfirmationSMsg.setTargetMessageId(webSocketHandler.messageId);
//                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.user_join_event);
//                deliveryConfirmationSMsg.setUserId(userId);
//
////                messageHandler.messageId = "";
//
//
//                webSocketHandler.sendMessage(json.serialize(deliveryConfirmationSMsg));
//
//
//                //waiting to event start
//
////                EventStartMessageHandlerImpl eventStartMessageHandler = new EventStartMessageHandlerImpl();
//
////                clientEndPoint.addMessageHandler(eventStartMessageHandler);
//
////                System.out.println("event_start send " +  userId);
//                while (!webSocketHandler.route.equals("event_start")){
//                    Thread.sleep(10);
////                    System.out.println("wait");
//
//                }
//                System.out.println("confirmedEventStart: " + confirmedEventStart.incrementAndGet());
//
////                System.out.println("event_start received " +  userId);
//
//                // confirm event start
//
//                deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
//                deliveryConfirmationSMsg.setEventId(eventId_1);
//                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
//                deliveryConfirmationSMsg.setTargetMessageId(webSocketHandler.messageId);
//                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.event_start);
//                deliveryConfirmationSMsg.setUserId(userId);
//
////                messageHandler.messageId = "";
//
//                webSocketHandler.sendMessage(json.serialize(deliveryConfirmationSMsg));
//
//
//
//                //join playlist
//
////                UserJoinPlaylistMessageHandlerImpl userJoinPlaylistMessageHandler = new UserJoinPlaylistMessageHandlerImpl();
//
//                ClientJoinPlaylistSMsg clientJoinPlaylistSMsg = new ClientJoinPlaylistSMsg();
//                clientJoinPlaylistSMsg.setEventId(eventId_1);
//                clientJoinPlaylistSMsg.setNeedConfirmation(false);
//                clientJoinPlaylistSMsg.setPlaylistId(playlistId);
//                clientJoinPlaylistSMsg.setRoute(SocketRoute.user_join_playlist);
//                clientJoinPlaylistSMsg.setUserId(userId);
//
////                clientEndPoint.addMessageHandler(userJoinPlaylistMessageHandler);
//
//                webSocketHandler.sendMessage(json.serialize(clientJoinPlaylistSMsg));
//
                while (!webSocketHandler.isReadyToStart){
                    Thread.sleep(10);

                }
////
////                // confirm join playlist
//                deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
//                deliveryConfirmationSMsg.setEventId(eventId_1);
//                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
//                deliveryConfirmationSMsg.setTargetMessageId(webSocketHandler.messageId);
//                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.user_join_playlist);
//                deliveryConfirmationSMsg.setUserId(userId);
//
////                messageHandler.messageId = "";
//
//                webSocketHandler.sendMessage(json.serialize(deliveryConfirmationSMsg));
//
//
//
                ClientPlaylistStateSMsg clientPlaylistStateSMsg = new ClientPlaylistStateSMsg();
                clientPlaylistStateSMsg.setEventId(eventId_1);
                clientPlaylistStateSMsg.setNeedConfirmation(false);
                clientPlaylistStateSMsg.setPlaylistId(playlistId);
                clientPlaylistStateSMsg.setRoute(SocketRoute.playlist_state);
                clientPlaylistStateSMsg.setUserId(userId);
//
                long startTime = System.currentTimeMillis();
                while ((System.currentTimeMillis() - startTime) < listenTime ){
                    try {

                        webSocketHandler.sendMessage(json.serialize(clientPlaylistStateSMsg));

                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                webSocketHandler.connectionClose();
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeuser/%s", userId), Object.class );
//                System.out.println("listen finished");
////                clientEndPoint.sessionClose();

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            completableFuture.complete("listen finished listenEvent");
            return null;

        });

        System.out.println("return listenEvent");
        return completableFuture;
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void removeAllUsers(){

        long start = System.currentTimeMillis();

        executorService = Executors.newFixedThreadPool(usersCount);

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
