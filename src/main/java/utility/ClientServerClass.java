package utility;

import config.PropertiesLoader;
import handlers.TestMessageHandler;
import handlers.impls.MessageHandlerImpl;
import messages.http.*;
import messages.webSocket.WebSocketMessages;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
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
        for (int i = 0; i <= 1; i++ ){

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
                ResultStatus setEventDto = (ResultStatus)messageHttpSending.SendGetMessageToAnotherServer(String.format("/media/playlists/%s", mediaId), ResultStatus.class );
                System.out.println(setEventDto.eventId);

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
                Object setEventDto = (Object)messageHttpSending.SendGetMessageToAnotherServer(String.format("/auth/removeuser/%s", userId), Object.class );

            } catch (IOException e) {
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }
        }
        long time = System.currentTimeMillis() - start;

    }

    public void joinEvent(){

        ExecutorService executorService = Executors.newFixedThreadPool(usersList.size());
        for (String userId : usersList){
            executorService.submit(()->{
                URI uri = null;
                try {
                    uri = new URI("ws://localhost:8080/echo");

                    WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(uri);


                    TestMessageHandler messageHandler = new MessageHandlerImpl();
//
                    // add listener
                    clientEndPoint.addMessageHandler(messageHandler);

                    clientEndPoint.sendMessage("{\"route\":\"clientAuth\", \"userId\":\"bba937ca-df26-4eff-8763-fad1cc6048c5\" , \"eventId\":\"6b53ea97-4d67-4aea-89e5-393c79756e33\"}");

                    for (int i = 0; i <= 300; i++){
                        try {

                            clientEndPoint.sendMessage("{\"route\":\"clientAuth\", \"userId\":\"bba937ca-df26-4eff-8763-fad1cc6048c5\" , \"eventId\":\"6b53ea97-4d67-4aea-89e5-393c79756e33\"}");

                            Thread.sleep(1000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

            });
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
