package entities;

public class SocketInf {

    private String eventId;
    private String userId;
    private Long startTime;

    public SocketInf(String userId, String eventId, Long startTime) {
        this.userId = userId;
        this.eventId = eventId;
        this.startTime = startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getEventId() {
        return eventId;
    }

    public String getUserId() {
        return userId;
    }

    public Long getStartTime() {
        return startTime;
    }
}
