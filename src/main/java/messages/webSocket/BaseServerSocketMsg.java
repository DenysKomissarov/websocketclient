package messages.webSocket;

import org.codehaus.jackson.annotate.JsonProperty;

public class BaseServerSocketMsg {

    @JsonProperty("event_id")
    private String eventId;

    private String route;

    @JsonProperty("server_time_stamp")
    private long serverTimeStamp;

    public BaseServerSocketMsg(String eventId) {
        this.eventId = eventId;
        this.serverTimeStamp = System.currentTimeMillis();
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public long getServerTimeStamp() {
        return serverTimeStamp;
    }

    public void setServerTimeStamp(long serverTimeStamp) {
        this.serverTimeStamp = serverTimeStamp;
    }

    @Override
    public String toString() {
        return "BaseServerSocketMsg{" +
                "eventId='" + eventId + '\'' +
                ", route=" + route +
                ", serverTimeStamp=" + serverTimeStamp +
                '}';
    }
}
