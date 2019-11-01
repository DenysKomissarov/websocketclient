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

    private final LinkedList<String> usersList = new LinkedList();
    private ExecutorService executorService;


    private String eventId_1;
    private String mediaId;
    private String playlistId;
    private JSON json;
    private PropertiesLoader propertiesLoader;
    private final long listenTime = 3 * 60 * 1000;
    public static AtomicInteger count = new AtomicInteger();
    public static AtomicInteger confirmedEventStart = new AtomicInteger();

    public ClientServer() {
        this.propertiesLoader = new PropertiesLoader();
        this.json = new JSON();
        this.mediaId = propertiesLoader.getProperty("mediaId");
        this.eventId_1 = propertiesLoader.getProperty("eventId_1");
        this.playlistId = propertiesLoader.getProperty("playlistId");

    }

    public void saveUsersToDB(){

        long start = System.currentTimeMillis();
        for (int i = 200; i < 401; i++ ){

            CreateUserDto userDto = new CreateUserDto(i);

            try {
                String sJson = json.serialize(userDto);
                GetNewUserIdDto userWitId = (GetNewUserIdDto)messageHttpSending.SendPostMessageToAnotherServer(sJson, "/auth/registration", GetNewUserIdDto.class);
                if (userWitId != null){
                    usersList.add(userWitId.userId);
                }

            } catch (IOException e) {
                System.out.printf(e.getMessage());
//                e.printStackTrace();
            }
        }
        long time = System.currentTimeMillis() - start;

    }

    public void getEvent(){

        long start = System.currentTimeMillis();
        for (String userId : usersList){

            try {
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/eventcrud/getevent/%s/%s", userId, eventId_1), GetEventDto.class );

            } catch (IOException e) {
                System.out.printf(e.getMessage());
//                e.printStackTrace();
            }
        }
        long time = System.currentTimeMillis() - start;

    }

    public void bookEvent(String userId){

//        long start = System.currentTimeMillis();
//        for (String userId : usersList){

            UserIdEventId userIdEventId = new UserIdEventId(userId, eventId_1);
            try {

                String sJson = json.serialize(userIdEventId);
                ResultStatus setEventDto = (ResultStatus)messageHttpSending.SendPostMessageToAnotherServer(sJson,"/eventcrud/bookevent", ResultStatus.class );


            } catch (IOException e) {
                System.out.printf(e.getMessage());
//                e.printStackTrace();
            }
//        }
//        long time = System.currentTimeMillis() - start;

    }

    public void getPlaylists(){
        long start = System.currentTimeMillis();
        for (String userId : usersList){

            try {
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/media/playlists/%s", mediaId), ResultStatus.class );

            } catch (IOException e) {
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }
        }
        long time = System.currentTimeMillis() - start;
    }

    public void removeUsers(){

        long start = System.currentTimeMillis();
        for (String userId : usersList){

            try {
                messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeuser/%s", userId), Object.class );

            } catch (IOException e) {
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }
        }
        long time = System.currentTimeMillis() - start;

    }

    private Future<String> listenEvent(String userId){

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();


        executorService.submit(()->{

            URI uri = null;
            try {
                bookEvent(userId);
//                System.out.println("thread " + count.getAndIncrement());
                uri = new URI("ws://localhost:8080/echo");

                WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(uri);


//                UserJoinEventMessageHandlerImpl messageHandler = new UserJoinEventMessageHandlerImpl();
                EventStartMessageHandlerImpl messageHandler = new EventStartMessageHandlerImpl();
//
                // add listener
                clientEndPoint.addMessageHandler(messageHandler);

                //user join event
                ClientJoinEventSMsg clientJoinEventSMsg = new ClientJoinEventSMsg();
                clientJoinEventSMsg.setEventId(eventId_1);
                clientJoinEventSMsg.setUserId(userId);
                clientJoinEventSMsg.setNeedConfirmation(false);
                clientJoinEventSMsg.setRoute(SocketRoute.user_join_event);
//                clientEndPoint.sendMessage("{\"route\":\"user_join_event\", \"user_id\":\"" + userId + "\", \"is_need_confirmation\":1, \"event_id\":\"" + eventId_1 +"\"}");
                clientEndPoint.sendMessage(json.serialize(clientJoinEventSMsg));

//                System.out.println("user join event send " +  userId);
                while (!messageHandler.route.equals("user_join_event")){
                    Thread.sleep(100);
//                    System.out.println("wait");

                }

//                System.out.println("user join event received " +  userId);


                //confirmation user join event


                ClientDeliveryConfirmationSMsg deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
                deliveryConfirmationSMsg.setEventId(eventId_1);
                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
                deliveryConfirmationSMsg.setTargetMessageId(messageHandler.messageId);
                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.user_join_event);
                deliveryConfirmationSMsg.setUserId(userId);

//                messageHandler.messageId = "";


                clientEndPoint.sendMessage(json.serialize(deliveryConfirmationSMsg));


                //waiting to event start

//                EventStartMessageHandlerImpl eventStartMessageHandler = new EventStartMessageHandlerImpl();

//                clientEndPoint.addMessageHandler(eventStartMessageHandler);

//                System.out.println("event_start send " +  userId);
                while (!messageHandler.route.equals("event_start")){
                    Thread.sleep(100);
//                    System.out.println("wait");

                }
                System.out.println("confirmedEventStart: " + confirmedEventStart.incrementAndGet());

//                System.out.println("event_start received " +  userId);

                // confirm event start

                deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
                deliveryConfirmationSMsg.setEventId(eventId_1);
                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
                deliveryConfirmationSMsg.setTargetMessageId(messageHandler.messageId);
                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.event_start);
                deliveryConfirmationSMsg.setUserId(userId);

//                messageHandler.messageId = "";

                clientEndPoint.sendMessage(json.serialize(deliveryConfirmationSMsg));



                //join playlist

//                UserJoinPlaylistMessageHandlerImpl userJoinPlaylistMessageHandler = new UserJoinPlaylistMessageHandlerImpl();

                ClientJoinPlaylistSMsg clientJoinPlaylistSMsg = new ClientJoinPlaylistSMsg();
                clientJoinPlaylistSMsg.setEventId(eventId_1);
                clientJoinPlaylistSMsg.setNeedConfirmation(false);
                clientJoinPlaylistSMsg.setPlaylistId(playlistId);
                clientJoinPlaylistSMsg.setRoute(SocketRoute.user_join_playlist);
                clientJoinPlaylistSMsg.setUserId(userId);

//                clientEndPoint.addMessageHandler(userJoinPlaylistMessageHandler);

                clientEndPoint.sendMessage(json.serialize(clientJoinPlaylistSMsg));

                while (!messageHandler.route.equals("user_join_playlist")){
                    Thread.sleep(100);

                }
//
//                // confirm join playlist
                deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
                deliveryConfirmationSMsg.setEventId(eventId_1);
                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
                deliveryConfirmationSMsg.setTargetMessageId(messageHandler.messageId);
                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.user_join_playlist);
                deliveryConfirmationSMsg.setUserId(userId);

//                messageHandler.messageId = "";

                clientEndPoint.sendMessage(json.serialize(deliveryConfirmationSMsg));



                ClientPlaylistStateSMsg clientPlaylistStateSMsg = new ClientPlaylistStateSMsg();
                clientPlaylistStateSMsg.setEventId(eventId_1);
                clientPlaylistStateSMsg.setNeedConfirmation(false);
                clientPlaylistStateSMsg.setPlaylistId(playlistId);
                clientPlaylistStateSMsg.setRoute(SocketRoute.playlist_state);
                clientPlaylistStateSMsg.setUserId(userId);

                long startTime = System.currentTimeMillis();
                while ((System.currentTimeMillis() - startTime) < listenTime ){
                    try {

                        clientEndPoint.sendMessage(json.serialize(clientPlaylistStateSMsg));

                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("listen finished");
                clientEndPoint.sessionClose();

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            completableFuture.complete("listen finished");
            return null;

        });

        System.out.println("return");
        return completableFuture;
    }

    public void joinEvent(){

        if (usersList.size() > 0){
            executorService = Executors.newFixedThreadPool(usersList.size());

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
}
