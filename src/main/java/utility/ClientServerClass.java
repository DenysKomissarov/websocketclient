package utility;

import messages.http.CreateUserDto;
import messages.http.GetEventDto;
import messages.http.GetNewUserIdDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class ClientServerClass {

    private MessageHttpSending messageHttpSending = new MessageHttpSending();
    private MessageWebSocketSending messageWebSocketSending = new MessageWebSocketSending();
    private String eventId = "";

    private final LinkedList<String> usersList = new LinkedList();

    private final String mediaId = "";


    private final JSON json = new JSON() ;



    public void saveUsersToDB(){

        long start = System.currentTimeMillis();
        for (int i = 0; i <= 1; i++ ){

            CreateUserDto userDto = new CreateUserDto(i);

            try {
                String sJson = json.serialize(userDto);
                GetNewUserIdDto userWitId = (GetNewUserIdDto)messageHttpSending.SendPostMessageToAnotherServer(sJson, "/auth/registration", GetNewUserIdDto.class);
                usersList.add(userWitId.userId);

            } catch (IOException e) {
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }
        }
        long time = System.currentTimeMillis() - start;

    }

    public void getEvent(){

        long start = System.currentTimeMillis();
        for (int i = 0; i <= 1; i++ ){

            String userId = usersList.get(i);
//            SetEventDto setEventDto = new SetEventDto(eventId, userId);

            try {
//                String sJson = json.serialize(user);
                GetEventDto setEventDto = (GetEventDto)messageHttpSending.SendGetMessageToAnotherServer(String.format("/eventcrud/getevent/%s/%s", userId, eventId), GetEventDto.class );
                System.out.println(setEventDto.eventId);

            } catch (IOException e) {
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }
        }
        long time = System.currentTimeMillis() - start;

    }

    public void getPlaylists(){
        long start = System.currentTimeMillis();
        for (int i = 0; i <= 1; i++ ){

            String userId = usersList.get(i);

            try {
                GetEventDto setEventDto = (GetEventDto)messageHttpSending.SendGetMessageToAnotherServer(String.format("/media/playlists/%s", mediaId), GetEventDto.class );
                System.out.println(setEventDto.eventId);

            } catch (IOException e) {
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }
        }
        long time = System.currentTimeMillis() - start;
    }
    public void playEvent(){

//        try {
////
//            // open websocket
//            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8080/echo"));
//
//            TestMessageHandler messageHandler = new MessageHandlerImpl();
//
//            // add listener
//            clientEndPoint.addMessageHandler(messageHandler);
//
//            // send message to websocket
//            clientEndPoint.sendMessage("{\"route\":\"clientAuth\", \"userId\":\"bba937ca-df26-4eff-8763-fad1cc6048c5\" , \"eventId\":\"6b53ea97-4d67-4aea-89e5-393c79756e33\"}");
//
//
//
//            // wait 5 seconds for messages from websocket
//            Thread.sleep(5000);
//            clientEndPoint.sendMessage(WebSocketMessages.startMessage);
//
//        } catch (InterruptedException ex) {
//            System.err.println("InterruptedException exception: " + ex.getMessage());
//        } catch (URISyntaxException ex) {
//            System.err.println("URISyntaxException exception: " + ex.getMessage());
//        }

    }
}
