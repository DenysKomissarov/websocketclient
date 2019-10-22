package messages.webSocket.server;

import messages.webSocket.BaseServerSocketMsg;
import messages.webSocket.SocketRoute;
import org.codehaus.jackson.annotate.JsonProperty;

public class ServerConfirmSMsg extends BaseServerSocketMsg {

    private String route = SocketRoute.delivery_confirmation.name();

    @JsonProperty("target_route")
    private SocketRoute targetRoute;

    @JsonProperty("target_message_id")
    private String targetMessageId;

    public ServerConfirmSMsg(String eventId, SocketRoute targetRoute, String targetMessageId) {
        super(eventId);
        this.targetRoute = targetRoute;
        this.targetMessageId = targetMessageId;
    }

    @Override
    public String getRoute() {
        return route;
    }

    public SocketRoute getTargetRoute() {
        return targetRoute;
    }

    public void setTargetRoute(SocketRoute targetRoute) {
        this.targetRoute = targetRoute;
    }

    public String getTargetMessageId() {
        return targetMessageId;
    }

    public void setTargetMessageId(String targetMessageId) {
        this.targetMessageId = targetMessageId;
    }

}
