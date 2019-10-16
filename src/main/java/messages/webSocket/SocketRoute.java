package messages.webSocket;

public enum SocketRoute {

    //Notifications
    //not need confirm
    ERROR("error"),
    DELIVERY_CONFIRMATION("delivery_confirmation"),
    EVENT_STATE("event_state"),
    PLAYLIST_STATE("playlist_state"),

    //not need confirm on them usually if broadcasted
    //need confirm on them if individual
    USER_JOIN_EVENT("user_join_event"),
    USER_LEAVE_EVENT("user_leave_event"),
    USER_JOIN_PLAYLIST("user_join_playlist"),
    USER_LEAVE_PLAYLIST("user_leave_playlist"),

    //Commands
    EVENT_START("event_start"),
    EVENT_END("event_end"),
    PLAYLIST_COMMAND("playlist_command"),
    PLAYLIST_END("playlist_end");


    SocketRoute(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
