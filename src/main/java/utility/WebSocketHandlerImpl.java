package utility;

import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WebSocketHandlerImpl implements WebSocketHandler {

    private WebSocketSession socketSession;
    public String messageId = "";
    public String route = "";
    public boolean isSucess = false;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("Connection was established");
        this.socketSession = webSocketSession;
        this.isSucess = true;
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

        System.out.println("message from server\n" + webSocketMessage.getPayload());

//        ServerUserJoinEventSMsg serverUserJoinEventSMsg = json.deSerialize(message, ServerUserJoinEventSMsg.class);
        List<String> list = Arrays.asList(webSocketMessage.getPayload().toString().split("\""));
        int index = list.indexOf("message_id");
        if (index != -1){
            this.messageId = list.get(index + 2);
        }

        int routeIndex = list.indexOf("route");
        if (routeIndex != -1){
            this.route = list.get(routeIndex + 2);
            if (this.route.equals("event_start")){
                System.out.println("thread " + ClientServer.count.getAndIncrement());
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("Handle transport message" + throwable.getStackTrace());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("Closed connection");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
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
