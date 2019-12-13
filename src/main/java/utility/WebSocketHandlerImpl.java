package utility;

import messages.webSocket.SocketRoute;
import messages.webSocket.client.ClientDeliveryConfirmationSMsg;
import messages.webSocket.client.ClientJoinPlaylistSMsg;
import messages.webSocket.client.ClientPlaylistStateSMsg;
import org.eclipse.jetty.util.thread.Scheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class WebSocketHandlerImpl implements WebSocketHandler {

    private WebSocketSession socketSession;
    public String messageId = "";
    public String route = "";
    public boolean isSucess = false;
    public String eventId ;
    public String userId;
    public String playlistId;
    private JSON json;
//    private final long listenTime = 3 * 60 * 1000;
    private boolean isUserJoinPlaylist = false;
    public boolean isReadyToStart = false;
    public boolean isError = false;
    private MessageHttpSending messageHttpSending;

    public WebSocketHandlerImpl(String eventId, String userId, String playlistId){
        this.eventId = eventId;
        this.userId = userId;
        this.playlistId = playlistId;
        this.messageHttpSending = new MessageHttpSending();

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("Connection was established");
        this.json = new JSON();
        this.socketSession = webSocketSession;
        this.isSucess = true;

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

//        System.out.println("message from server\n" + webSocketMessage.getPayload());

//        ServerUserJoinEventSMsg serverUserJoinEventSMsg = json.deSerialize(message, ServerUserJoinEventSMsg.class);
        List<String> list = Arrays.asList(webSocketMessage.getPayload().toString().split("\""));
        String targetMessageId = "";
        int index = list.indexOf("message_id");
        if (index != -1){
            this.messageId = list.get(index + 2);
            targetMessageId = list.get(index + 2);
        }

        int routeIndex = list.indexOf("route");
        if (routeIndex != -1){
            this.route = list.get(routeIndex + 2);
            if (this.route.equals("playlist_state")){
//                System.out.println("message from server\n" + webSocketMessage.getPayload());
//                System.out.println("thread " + ClientServer.count.getAndIncrement());
            }
        }

        ClientDeliveryConfirmationSMsg deliveryConfirmationSMsg = null;

        switch (this.route){

            case "user_join_event":
                deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
                deliveryConfirmationSMsg.setEventId(this.eventId);
                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
                deliveryConfirmationSMsg.setTargetMessageId(targetMessageId);
                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.user_join_event);
                deliveryConfirmationSMsg.setUserId(userId);
                sendMessage(json.serialize(deliveryConfirmationSMsg));

                break;
            case  "event_start":
                deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
                deliveryConfirmationSMsg.setEventId(this.eventId);
                deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
                deliveryConfirmationSMsg.setTargetMessageId(targetMessageId);
                deliveryConfirmationSMsg.setTargetRoute(SocketRoute.event_start);
                deliveryConfirmationSMsg.setUserId(userId);
                sendMessage(json.serialize(deliveryConfirmationSMsg));

                System.out.println(" event_start " + ClientServer.confirmedEventStart.incrementAndGet());

                ClientJoinPlaylistSMsg clientJoinPlaylistSMsg = new ClientJoinPlaylistSMsg();
                clientJoinPlaylistSMsg.setEventId(this.eventId);
                clientJoinPlaylistSMsg.setNeedConfirmation(false);
                clientJoinPlaylistSMsg.setPlaylistId(playlistId);
                clientJoinPlaylistSMsg.setRoute(SocketRoute.user_join_playlist);
                clientJoinPlaylistSMsg.setUserId(userId);
                sendMessage(json.serialize(clientJoinPlaylistSMsg));

            break;
            case "user_join_playlist":

//                if (!isUserJoinPlaylist){
//                    System.out.println(" confirmedJoinPlaylist " + ClientServer.confirmedJoinPlaylist.incrementAndGet());
                isUserJoinPlaylist = true;
                    deliveryConfirmationSMsg = new ClientDeliveryConfirmationSMsg();
                    deliveryConfirmationSMsg.setEventId(this.eventId);
                    deliveryConfirmationSMsg.setRoute(SocketRoute.delivery_confirmation);
                    deliveryConfirmationSMsg.setTargetMessageId(targetMessageId);
                    deliveryConfirmationSMsg.setTargetRoute(SocketRoute.user_join_playlist);
                    deliveryConfirmationSMsg.setUserId(userId);
                    sendMessage(json.serialize(deliveryConfirmationSMsg));

                    isReadyToStart = true;
                    break;
//                }
        }

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("Handle transport message" + throwable.getMessage());
//        isError = true;

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("Closed connection");
//        isError = true;
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void connectionClose(){
        try {
            socketSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            socketSession.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            System.out.println("Error send message");
            e.printStackTrace();
        }
    }
}
