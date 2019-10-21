package messages.http;


public class UserIdEventId {

    public String userId;

    public String eventId;

    public UserIdEventId() {
    }

    public UserIdEventId(String userId, String eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
}
