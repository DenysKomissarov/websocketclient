package utility;

import messages.http.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class ClientServerClass {

    private MessageHttpSending messageHttpSending = new MessageHttpSending();
    private MessageWebSocketSending messageWebSocketSending = new MessageWebSocketSending();

    private final LinkedList<String> usersList = new LinkedList();


    private final JSON json = new JSON() ;



    public void saveUsersToDB(){

        long start = System.currentTimeMillis();
        for (int i = 0; i <= 1; i++ ){

            CreateUserDto user = new CreateUserDto(i);

            try {
                String sJson = json.serialize(user);
                String userId = messageHttpSending.SendMessageToAnotherServer(sJson, "");
                if (userId != null){
                       usersList.add(userId);
                }
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
