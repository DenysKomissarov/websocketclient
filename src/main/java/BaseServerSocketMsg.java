
public class BaseServerSocketMsg {

    private String eventId;

    private String route;

    private long serverTimeStamp;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
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
