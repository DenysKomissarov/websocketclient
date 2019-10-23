package handlers.impls;

import handlers.TestMessageHandler;
import messages.webSocket.server.ServerUserJoinEventSMsg;
import utility.JSON;

public class  UserJoinMessageHandlerImpl implements TestMessageHandler {

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

        ServerUserJoinEventSMsg serverUserJoinEventSMsg = json.deSerialize(message, ServerUserJoinEventSMsg.class);
        this.messageId = serverUserJoinEventSMsg.getMessageId();
//        ResponseMessage responseMessage2 = json.deSerialize(message, ResponseMessage.class);
//        Object responseMessage2 = json.deSerialize(message, Object.class);

    }
}
