package handlers.impls;

import handlers.TestMessageHandler;
import messages.webSocket.server.ServerUserJoinEventSMsg;
import utility.JSON;

import java.util.Arrays;
import java.util.List;

public class PlayListStateMessageHandlerImpl implements TestMessageHandler {

    private ServerUserJoinEventSMsg responseMessage;
    public String messageId = "";

    public ServerUserJoinEventSMsg getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ServerUserJoinEventSMsg responseMessage) {
        this.responseMessage = responseMessage;
    }

    private final JSON json = new JSON();

    @Override
    public void handleMessage(String message) {
        System.out.println("message from server\n" + message);

    }
}