package utility;

import messages.webSocket.SocketRoute;
import messages.webSocket.client.ClientDeliveryConfirmationSMsg;
import messages.webSocket.client.ClientJoinPlaylistSMsg;
import messages.webSocket.client.ClientPlaylistStateSMsg;
import org.eclipse.jetty.util.thread.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class WebSocketHandlerImpl implements WebSocketHandler {


    public boolean isSucess = false;
    public String eventId ;
    public String playlistId;
    private JSON json;
    public boolean playlistState = true;
//    private final long listenTime = 3 * 60 * 1000;
    private boolean isUserJoinPlaylist = false;
//    public boolean isReadyToStart = false;
    public boolean isError = false;
    private MessageHttpSending messageHttpSending;
    private String playlistStateMessage;
    public Map<WebSocketSession, String > socketSessionMap = new ConcurrentHashMap<>();

    private WebSocketService mainWebSocketService;

    private ExecutorService executorService;

    public WebSocketHandlerImpl(String eventId, String playlistId, ExecutorService executorService){
        this.eventId = eventId;
        this.playlistId = playlistId;
        this.messageHttpSending = new MessageHttpSending();
        this.executorService = executorService;

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("Connection was established");
        this.json = new JSON();
        this.isSucess = true;

//

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {

        mainWebSocketService = new WebSocketService();
        executorService.submit(() -> {
//
            mainWebSocketService.handleTextMessage(webSocketSession, webSocketMessage, json,  this,
                    eventId, playlistId, socketSessionMap);
        });
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

    public void connectionClose(WebSocketSession webSocketSession){
        try {
            webSocketSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(WebSocketSession webSocketSession, String message) {
        try {
            webSocketSession.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            System.out.println("Error send message");
            e.printStackTrace();
        }
    }
}
