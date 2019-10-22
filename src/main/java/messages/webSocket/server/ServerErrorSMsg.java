package messages.webSocket.server;

import entities.SocketServerErrorReason;
import messages.webSocket.BaseServerSocketMsg;
import messages.webSocket.SocketRoute;
import org.codehaus.jackson.annotate.JsonProperty;

public class ServerErrorSMsg extends BaseServerSocketMsg {

    private String route = SocketRoute.error.name();

    @JsonProperty("error_description")
    private String errorDescription;

    @JsonProperty("target_route")
    private SocketRoute targetRoute;

    @JsonProperty("target_message_id")
    private String targetMessageId;

    @JsonProperty("error_reason")
    private SocketServerErrorReason errorReason;

    public ServerErrorSMsg(String eventId, String errorDescription, SocketServerErrorReason errorReason,  SocketRoute targetRoute, String targetMessageId) {
        super(eventId);
        this.errorDescription = errorDescription;
        this.targetRoute = targetRoute;
        this.targetMessageId = targetMessageId;
        this.errorReason = errorReason;
    }
    @Override
    public String getRoute() {
        return route;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
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

    public SocketServerErrorReason getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(SocketServerErrorReason errorReason) {
        this.errorReason = errorReason;
    }

    @Override
    public String toString() {
        return "ServerErrorSMsg{" +
                "route='" + route + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", targetRoute=" + targetRoute +
                ", targetMessageId='" + targetMessageId + '\'' +
                '}';
    }
}
