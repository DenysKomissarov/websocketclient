package messages.webSocket;


public class ClientBaseMsg {

    private String eventId;

    private String userId;

    private SocketRoute route;

    private long userTimeStamp;

    private String messageId;

    private boolean isNeedConfirmation;

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

    public boolean isNeedConfirmation() {
        return isNeedConfirmation;
    }

    public void setNeedConfirmation(boolean needConfirmation) {
        isNeedConfirmation = needConfirmation;
    }

    @Override
    public String toString() {
        return "ClientBaseMsg{" +
                "eventId='" + eventId + '\'' +
                ", userId='" + userId + '\'' +
                ", route=" + route +
                ", userTimeStamp=" + userTimeStamp +
                ", messageId='" + messageId + '\'' +
                ", isNeedConfirmation=" + isNeedConfirmation +
                '}';
    }
}
