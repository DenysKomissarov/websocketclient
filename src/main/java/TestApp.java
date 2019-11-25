//import com.neovisionaries.ws.client.WebSocket;
//import com.neovisionaries.ws.client.WebSocketAdapter;
//import com.neovisionaries.ws.client.WebSocketException;
//import com.neovisionaries.ws.client.WebSocketFactory;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;


import utility.ClientServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TestApp {


    public static void main(String[] args) {

//        printMessage(message);




        ClientServer clientServer = new ClientServer();
//        clientServer.saveUsersToDB();
//        clientServer.getEvents();
//        clientServer.bookEvents();
//        clientServer.joinEvent();
//        System.out.println("after join event");


//        clientServer.removeUsers();

        clientServer.removeAllUsers();


    }

}
