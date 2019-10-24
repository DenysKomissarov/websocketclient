package handlers.impls;

import handlers.TestMessageHandler;
import messages.webSocket.server.ServerUserJoinEventSMsg;
import utility.JSON;

import java.util.Arrays;
import java.util.List;

public class EventStartMessageHandlerImpl implements TestMessageHandler {

    private ServerUserJoinEventSMsg responseMessage;
    public String messageId = "";
    public String route = "";

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

//        ServerUserJoinEventSMsg serverUserJoinEventSMsg = json.deSerialize(message, ServerUserJoinEventSMsg.class);
        List<String> list = Arrays.asList(message.split("\""));
        int index = list.indexOf("message_id");
        if (index != -1){
            this.messageId = list.get(index + 2);
        }

        int routeIndex = list.indexOf("route");
        if (routeIndex != -1){
            this.route = list.get(routeIndex + 2);
        }



//        ResponseMessage responseMessage2 = json.deSerialize(message, ResponseMessage.class);
//        Object responseMessage2 = json.deSerialize(message, Object.class);

    }
}