package utility;

import config.PropertiesLoader;
import handlers.TestMessageHandler;
import handlers.impls.MessageHandlerImpl;
import handlers.impls.UserJoinMessageHandlerImpl;
import messages.http.*;
import messages.webSocket.SocketRoute;
import messages.webSocket.WebSocketMessages;
import messages.webSocket.client.ClientJoinEventSMsg;

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
    private JSON json;
    private PropertiesLoader propertiesLoader;

    public ClientServerClass() {
        this.propertiesLoader = new PropertiesLoader();
        this.json = new JSON();
        this.mediaId = propertiesLoader.getProperty("mediaId");
        this.eventId_1 = propertiesLoader.getProperty("eventId_1");

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


                TestMessageHandler messageHandler = new UserJoinMessageHandlerImpl();
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

                while (messageHandler.equals("")){

                }

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




    public void playEvent(){

        try {
//
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8080/echo"));

            TestMessageHandler messageHandler = new MessageHandlerImpl();

            // add listener
            clientEndPoint.addMessageHandler(messageHandler);

            // send message to websocket
            clientEndPoint.sendMessage("{\"route\":\"clientAuth\", \"userId\":\"bba937ca-df26-4eff-8763-fad1cc6048c5\" , \"eventId\":\"6b53ea97-4d67-4aea-89e5-393c79756e33\"}");



            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);
            clientEndPoint.sendMessage(WebSocketMessages.startMessage);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }

    }
}
