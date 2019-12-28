//import com.neovisionaries.ws.client.WebSocket;
//import com.neovisionaries.ws.client.WebSocketAdapter;
//import com.neovisionaries.ws.client.WebSocketException;
//import com.neovisionaries.ws.client.WebSocketFactory;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;


import com.sun.management.UnixOperatingSystemMXBean;
import utility.ClientServer;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TestApp {

    public static void main(String[] args) {


        ClientServer clientServer;
//        if (args.length >= 3 && args[0] != null && args[1] != null && args[2] != null) {
//
//            clientServer = new ClientServer(args[0], args[1],  args[2]);
//
//            try {
//                clientServer.saveUsersToDB();
////                Thread.sleep(30000);
////
////                clientServer.getEvents();
//
//                Thread.sleep(10000);
//
//                clientServer.bookEvents();
//
//                Thread.sleep(10000);
//
//                clientServer.joinEvent();
//
//                clientServer.startAllUsersToGetPlaylistState();
//
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////
//
//
//
//
//
////            while(true){
////                try {
////                    OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
////
////                    UnixOperatingSystemMXBean unixOs = (UnixOperatingSystemMXBean) os;
////                    long max = unixOs.getMaxFileDescriptorCount();
////                    long open = unixOs.getOpenFileDescriptorCount();
////                    System.out.println("max: " + max + " open: " + open);
////                    Thread.sleep(5000);
////                } catch (Throwable th) {
////                }
////
////            }
////        System.out.println("after join event");
//
////        clientServer.removeUsers();
//
//        } else if (args.length >= 1 && args[0] != null) {
//            clientServer = new ClientServer(args[0], "0", "");
//            clientServer.removeAllUsers();
//        } else {
//            clientServer = new ClientServer("1000", "0", "");
//            clientServer.removeAllUsers();
//        }


        clientServer = new ClientServer("10", "0",  "44c82713-1ced-4f74-896a-76c368b8542d");
        clientServer.saveUsersToDB();
//        clientServer.getEvents();
        clientServer.bookEvents();
        clientServer.joinEvent();
        clientServer.startAllUsersToGetPlaylistState();



    }

}
