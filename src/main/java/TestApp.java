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
import messages.WebSocketMessages;

import java.net.URI;
import java.net.URISyntaxException;

public class TestApp {
    private final static String denisId = "bed819ac-efe7-4cee-93a4-2752b7c6687f";
    private final static String eventId = "03c9ef1e-9e65-4dd3-8cdf-8e465992ebd4";

    public static void main(String[] args) {

        try {
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8080/echo"));

            TestMessageHandler messageHandler = new MessageHandlerImpl();

            // add listener
            clientEndPoint.addMessageHandler(messageHandler);

            // send message to websocket
            clientEndPoint.sendMessage("{\"route\":\"clientAuth\", \"userId\":\"bed819ac-efe7-4cee-93a4-2752b7c6687f\" , \"eventId\":\"03c9ef1e-9e65-4dd3-8cdf-8e465992ebd4\"}");

            messageHandler.


            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }

}
