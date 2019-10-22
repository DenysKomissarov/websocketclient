//import com.neovisionaries.ws.client.WebSocket;
//import com.neovisionaries.ws.client.WebSocketAdapter;
//import com.neovisionaries.ws.client.WebSocketException;
//import com.neovisionaries.ws.client.WebSocketFactory;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;


import messages.webSocket.client.ClientEventStateSMsg;
import utility.ClientServerClass;

import java.net.URISyntaxException;

public class TestApp {


    public static void main(String[] args) {

//        printMessage(message);



        ClientServerClass clientServer = new ClientServerClass();
        clientServer.saveUsersToDB();
        clientServer.getEvent();
        clientServer.bookEvent();
        clientServer.getPlaylists();

        clientServer.joinEvent();


        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clientServer.removeUsers();






//        try {
//
////            // open websocket
//            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8080/echo"));
////
////            TestMessageHandler messageHandler = new MessageHandlerImpl();
////
////            // add listener
////            clientEndPoint.addMessageHandler(messageHandler);
////
////            // send message to websocket
////            clientEndPoint.sendMessage("{\"route\":\"clientAuth\", \"userId\":\"bba937ca-df26-4eff-8763-fad1cc6048c5\" , \"eventId\":\"6b53ea97-4d67-4aea-89e5-393c79756e33\"}");
////
////
////
////            // wait 5 seconds for messages from websocket
////            Thread.sleep(5000);
////            clientEndPoint.sendMessage(WebSocketMessages.startMessage);
//
//        } catch (InterruptedException ex) {
//            System.err.println("InterruptedException exception: " + ex.getMessage());
//        } catch (URISyntaxException ex) {
//            System.err.println("URISyntaxException exception: " + ex.getMessage());
//        }


    }

}
