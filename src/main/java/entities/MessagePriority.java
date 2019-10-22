package entities;

public enum MessagePriority {

    event_state,
    playlist_state,
    user_join_event,
    user_leave_event,
    user_join_playlist,
    user_leave_playlist,
    event_start,
    event_end,
    playlist_command,
    playlist_end;

//    MessagePriority(String name) {
//        this.name = name;
//    }
//
//    private final String name;
//
//    public String getName() {
//        return name;
//    }
}
