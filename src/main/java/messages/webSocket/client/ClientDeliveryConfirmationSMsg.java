package messages.webSocket.client;

import messages.webSocket.SocketRoute;
import org.codehaus.jackson.annotate.JsonProperty;

public class ClientDeliveryConfirmationSMsg extends ClientBaseMsg {

    @JsonProperty("target_message_id")
    private String targetMessageId;

    @JsonProperty("target_route")
    private SocketRoute targetRoute;

    private String route = SocketRoute.delivery_confirmation.name();

    public String getTargetMessageId() {
        return targetMessageId;
    }

    public void setTargetMessageId(String targetMessageId) {
        this.targetMessageId = targetMessageId;
    }

    public SocketRoute getTargetRoute() {
        return targetRoute;
    }

    public void setTargetRoute(SocketRoute targetRoute) {
        this.targetRoute = targetRoute;
    }

    @Override
    public String toString() {
        return "ClientDeliveryConfirmationSMsg{" +
                "targetMessageId='" + targetMessageId + '\'' +
                ", targetRoute=" + targetRoute +
                ", route='" + route + '\'' +
                '}';
    }
}
