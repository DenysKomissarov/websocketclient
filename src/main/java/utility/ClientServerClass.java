package utility;

import config.PropertiesLoader;
import handlers.TestMessageHandler;
import handlers.impls.EventStateMessageHandlerImpl;
import handlers.impls.ServerStartEventHandlerImpl;
import handlers.impls.UserJoinMessageHandlerImpl;
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
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientServerClass {

    private MessageHttpSending messageHttpSending = new MessageHttpSending();
    private MessageWebSocketSending messageWebSocketSending = new MessageWebSocketSending();

    private final LinkedList<String> usersList = new LinkedList();

    private String eventId_1;
    private String mediaId;
    private String playlistId;
    private JSON json;
    private PropertiesLoader propertiesLoader;

    public ClientServerClass() {
        this.propertiesLoader = new PropertiesLoader();
        this.json = new JSON();
        this.mediaId = propertiesLoader.getProperty("mediaId");
        this.eventId_1 = propertiesLoader.getProperty("eventId_1");
        this.eventId_1 = propertiesLoader.getProperty("playlistId");

    }

    public void saveUsersToDB(){

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++ ){

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

    public void bookEvent(){

        long start = System.currentTimeMillis();
        for (String userId : usersList){

            UserIdEventId userIdEventId = new UserIdEventId(userId, eventId_1);
            try {

                String sJson = json.serialize(userIdEventId);
                ResultStatus setEventDto = (ResultStatus)messageHttpSending.SendPostMessageToAnotherServer(sJson,"/eventcrud/bookevent", ResultStatus.class );


            } catch (IOException e) {
                System.out.printf(e.getMessage());
//                e.printStackTrace();
            }
        }
        long time = System.currentTimeMillis() - start;

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

    public void joinEvent(){

        if (usersList.size() > 0){

            ExecutorService executorService = Executors.newFixedThreadPool(usersList.size());
        }
        for (String userId : usersList){

            URI uri = null;
            try {
                uri = new URI("ws://localhost:8080/echo");

                WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(uri);


                UserJoinMessageHandlerImpl messageHandler = new UserJoinMessageHandlerImpl();
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

                while (messageHandler.messageId.equals("")){
//                    System.out.println("wait");

                }


                ClientDeliveryConfirmationSMsg deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
                deliveryConfirmationSMsg.setEventId(eventId_1);
                deliveryConfirmationSMsg.setMessageId(messageHandler.messageId);
                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
                deliveryConfirmationSMsg.setTargetMessageId(messageHandler.messageId);
                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.user_join_event);
                deliveryConfirmationSMsg.setUserId(userId);

                clientEndPoint.sendMessage(json.serialize(deliveryConfirmationSMsg));
                messageHandler.messageId = "";

//                ServerStartEventHandlerImpl serverStartEventHandler = new ServerStartEventHandlerImpl();
//                clientEndPoint.addMessageHandler(serverStartEventHandler);


                //eventState





                //get media and go to player
//                String playListId = "";
//                try {
//                    DtoMedia dtoMedia =  (DtoMedia)messageHttpSending.SendGetMessageToAnotherServer(String.format("/media/get?userId=%s&mediaId%s", userId, mediaId), DtoMedia.class );
//                    if (dtoMedia != null){
//                        playListId = dtoMedia.get
//                    }
//
//                } catch (IOException e) {
//                    System.out.printf(e.getMessage());
//                e.printStackTrace();
//                }



                //join playlist

                ClientJoinPlaylistSMsg clientJoinPlaylistSMsg = new ClientJoinPlaylistSMsg();
                clientJoinPlaylistSMsg.setEventId(eventId_1);
                clientJoinPlaylistSMsg.setNeedConfirmation(false);
                clientJoinPlaylistSMsg.setPlaylistId(playlistId);
                clientJoinPlaylistSMsg.setRoute(SocketRoute.user_join_playlist);
                clientJoinPlaylistSMsg.setUserId(userId);

                clientEndPoint.sendMessage(json.serialize(clientJoinPlaylistSMsg));

                while (messageHandler.messageId.equals("")){
//                    System.out.println("wait");

                }

                // confirm join playlist

                deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
                deliveryConfirmationSMsg.setEventId(eventId_1);
                deliveryConfirmationSMsg.setMessageId(messageHandler.messageId);
                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
                deliveryConfirmationSMsg.setTargetMessageId(messageHandler.messageId);
                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.user_join_playlist);
                deliveryConfirmationSMsg.setUserId(userId);

                clientEndPoint.sendMessage(json.serialize(deliveryConfirmationSMsg));



                ClientPlaylistStateSMsg clientPlaylistStateSMsg = new ClientPlaylistStateSMsg();
                clientPlaylistStateSMsg.setEventId(eventId_1);
                clientPlaylistStateSMsg.setNeedConfirmation(false);
                clientPlaylistStateSMsg.setPlaylistId(playlistId);
                clientPlaylistStateSMsg.setRoute(SocketRoute.playlist_state);
                clientPlaylistStateSMsg.setUserId(userId);

                for (int i = 0; i <= 100; i++){
                    try {

                        clientEndPoint.sendMessage(json.serialize(clientPlaylistStateSMsg));

                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


//                EventStateMessageHandlerImpl eventStateMessageHandler = new EventStateMessageHandlerImpl();







//                //user join event confirm
//                clientEndPoint.sendMessage("{\"route\":\"delivery_confirmation\", \"userId\":\"" + userId + "\" , \"eventId\":\"" + eventId_1 +"\", \"target_route\":\"user_join_event\"}");
//
//                //event_state
//                clientEndPoint.sendMessage("{\"route\":\"event_state\", \"userId\":\"" + userId + "\" , \"eventId\":\"" + eventId_1 +"\"}");
//                //user join event confirm
//                clientEndPoint.sendMessage("{\"route\":\"delivery_confirmation\", \"userId\":\"" + userId + "\" , \"eventId\":\"" + eventId_1 +"\", \"target_route\":\"event_state\"}");



//                for (int i = 0; i <= 300; i++){
//                    try {
//
//                        clientEndPoint.sendMessage("{\"route\":\"playlist_state\", \"userId\":\"" + userId +"\" , \"eventId\":\"" + eventId_1 + "\"}");
//
//                        Thread.sleep(1000);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
//            executorService.submit(()->{
//
//
//            });
        }

    }

}
