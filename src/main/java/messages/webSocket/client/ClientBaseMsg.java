package messages.webSocket.client;

import messages.webSocket.SocketRoute;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.UUID;

public class ClientBaseMsg {

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("user_id")
    private String userId;

//    @JsonProperty("route")
    private SocketRoute route;

    @JsonProperty("user_time_stamp")
    private long userTimeStamp;

    @JsonProperty("message_id")
    private String messageId;

    public ClientBaseMsg(String eventId) {
        this.eventId = eventId;
        this.userTimeStamp = System.currentTimeMillis();
        this.messageId = UUID.randomUUID().toString();
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SocketRoute getRoute() {
        return route;
    }

    public void setRoute(SocketRoute route) {
        this.route = route;
    }

    public long getUserTimeStamp() {
        return userTimeStamp;
    }

    public void setUserTimeStamp(long userTimeStamp) {
        this.userTimeStamp = userTimeStamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

}
