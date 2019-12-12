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


        ClientServer clientServer;
        if (args.length >= 3 && args[0] != null && args[1] != null && args[2] != null) {

            clientServer = new ClientServer(args[0], args[1],  args[2]);
            clientServer.saveUsersToDB();
            clientServer.getEvents();
            clientServer.bookEvents();
            clientServer.joinEvent();
//        System.out.println("after join event");


//        clientServer.removeUsers();

        } else if (args.length >= 1 && args[0] != null) {
            clientServer = new ClientServer(args[0], "0", "");
            clientServer.removeAllUsers();
        } else {
            clientServer = new ClientServer("5000", "0", "");
            clientServer.removeAllUsers();
        }


    }

}
