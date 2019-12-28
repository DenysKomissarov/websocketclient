package utility;

import messages.webSocket.SocketRoute;
import messages.webSocket.client.ClientDeliveryConfirmationSMsg;
import messages.webSocket.client.ClientJoinPlaylistSMsg;
import messages.webSocket.client.ClientPlaylistStateSMsg;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class WebSocketService {



    public void handleTextMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage, JSON json, WebSocketHandlerImpl mainWebSocketHandler,
                                  String eventId, String playlistId, Map<WebSocketSession, String > socketSessionMap) {

        String messageId = "";
        String route = "";
        System.out.println("message from server\n" + webSocketMessage.getPayload());

//        ServerUserJoinEventSMsg serverUserJoinEventSMsg = json.deSerialize(message, ServerUserJoinEventSMsg.class);
        List<String> list = Arrays.asList(webSocketMessage.getPayload().toString().split("\""));
        String targetMessageId = "";
        int index = list.indexOf("message_id");
        if (index != -1){
            messageId = list.get(index + 2);
            targetMessageId = list.get(index + 2);
        }

        int routeIndex = list.indexOf("route");
        if (routeIndex != -1){
            route = list.get(routeIndex + 2);
            if (route.equals("playlist_state")){
//                System.out.println("message from server\n" + webSocketMessage.getPayload());
//                System.out.println("thread " + ClientServer.count.getAndIncrement());
            }
        }

        ClientDeliveryConfirmationSMsg deliveryConfirmationSMsg = null;

        ClientPlaylistStateSMsg clientPlaylistStateSMsg = new ClientPlaylistStateSMsg();
        clientPlaylistStateSMsg.setEventId(eventId);
        clientPlaylistStateSMsg.setNeedConfirmation(false);
        clientPlaylistStateSMsg.setPlaylistId(playlistId);
        clientPlaylistStateSMsg.setRoute(SocketRoute.playlist_state);
        clientPlaylistStateSMsg.setUserId(socketSessionMap.get(webSocketSession));

        switch (route){

            case "user_join_event":
                deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
                deliveryConfirmationSMsg.setEventId(eventId);
                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
                deliveryConfirmationSMsg.setTargetMessageId(targetMessageId);
                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.user_join_event);
                deliveryConfirmationSMsg.setUserId(socketSessionMap.get(webSocketSession));
                mainWebSocketHandler.sendMessage(webSocketSession, json.serialize(deliveryConfirmationSMsg));

                break;
            case  "event_start":
//                deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
//                deliveryConfirmationSMsg.setEventId(this.eventId);
//                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
//                deliveryConfirmationSMsg.setTargetMessageId(targetMessageId);
//                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.event_start);
//                deliveryConfirmationSMsg.setUserId(userId);
//                sendMessage(json.serialize(deliveryConfirmationSMsg));

//                System.out.println(" event_start " + ClientServer.confirmedEventStart.incrementAndGet());

                if (ClientServer.startTime.longValue() == 0){
                    ClientServer.startTime = new AtomicLong(System.currentTimeMillis());
                }

                ClientJoinPlaylistSMsg clientJoinPlaylistSMsg = new ClientJoinPlaylistSMsg();
                clientJoinPlaylistSMsg.setEventId(eventId);
                clientJoinPlaylistSMsg.setNeedConfirmation(false);
                clientJoinPlaylistSMsg.setPlaylistId(playlistId);
                clientJoinPlaylistSMsg.setRoute(SocketRoute.user_join_playlist);
                clientJoinPlaylistSMsg.setUserId(socketSessionMap.get(webSocketSession));
                mainWebSocketHandler.sendMessage(webSocketSession, json.serialize(clientJoinPlaylistSMsg));

                break;
            case "user_join_playlist":

//                if (!isUserJoinPlaylist){
//                    System.out.println(" confirmedJoinPlaylist " + ClientServer.confirmedJoinPlaylist.incrementAndGet());
//                isUserJoinPlaylist = true;

                deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
                deliveryConfirmationSMsg.setEventId(eventId);
                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
                deliveryConfirmationSMsg.setTargetMessageId(targetMessageId);
                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.user_join_playlist);
                deliveryConfirmationSMsg.setUserId(socketSessionMap.get(webSocketSession));
                mainWebSocketHandler.sendMessage(webSocketSession, json.serialize(deliveryConfirmationSMsg));

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainWebSocketHandler.sendMessage(webSocketSession, json.serialize(clientPlaylistStateSMsg));
//                    ClientServer.isReadyToStart = true;
//                    if (ClientServer.timer.get() == 0){
//                        ClientServer.timer = new AtomicLong(System.currentTimeMillis());
//                        System.out.println("timer = " + ClientServer.timer.get());
//                    }
                break;
//                }
            case "playlist_state":

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainWebSocketHandler.sendMessage(webSocketSession, json.serialize(clientPlaylistStateSMsg));

                break;
        }

    }
}
