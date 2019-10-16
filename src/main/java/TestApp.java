//import com.neovisionaries.ws.client.WebSocket;
//import com.neovisionaries.ws.client.WebSocketAdapter;
//import com.neovisionaries.ws.client.WebSocketException;
//import com.neovisionaries.ws.client.WebSocketFactory;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;

import entities.RequestMessage;
import handlers.TestMessageHandler;
import handlers.impls.MessageHandlerImpl;
import messages.ClientBaseMsg;
import messages.ClientEventStateSMsg;
import messages.WebSocketMessages;

import java.net.URI;
import java.net.URISyntaxException;

public class TestApp {
    private final static String denisId = "bed819ac-efe7-4cee-93a4-2752b7c6687f";
    private final static String eventId = "03c9ef1e-9e65-4dd3-8cdf-8e465992ebd4";

    public static void main(String[] args) {

        ClientEventStateSMsg message = new ClientEventStateSMsg();

        message.setNum(5);
        printMessage(message);



        try {






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

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }


    }

    public static void printMessage(ClientBaseMsg message){
        System.out.println(message);
    }

}
