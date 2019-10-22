package entities;

public enum SocketRoute {

    //Notifications
    //not need confirm
//    @JsonProperty("error")
    error,
//    @JsonProperty("delivery_confirmation")
    delivery_confirmation,
//    @JsonProperty("event_state")
    event_state,
//    @JsonProperty("playlist_state")
    playlist_state,

    //not need confirm on them usually if broadcasted
    //need confirm on them if individual
//    @JsonProperty("user_join_event")
    user_join_event,
//    @JsonProperty("user_leave_event")
    user_leave_event,
//    @JsonProperty("user_join_playlist")
    user_join_playlist,
//    @JsonProperty("user_leave_playlist")
    user_leave_playlist,

    //Commands
//    @JsonProperty("event_start")
    event_start,
//    @JsonProperty("event_end")
    event_end,
//    @JsonProperty("playlist_command")
    playlist_command,
//    @JsonProperty("playlist_end")
//    playlist_end;


//    SocketRoute(String name) {
//        this.name = name;
//    }
//
//    private final String name;
//
//    public String getName() {
//        return name;
//    }
}
