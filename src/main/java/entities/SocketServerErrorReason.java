package entities;

public enum SocketServerErrorReason {

    undefined,
    event_finished,
    event_not_started,
    playlist_finished,
    playlist_locked,
    user_not_authorized,
    user_not_leader,
    user_not_in_event,
    user_not_in_playlist,
    invalid_data;


//    undefined ("undefined"),
//    eventFinished ("event_finished"),
//    eventNotStarted("event_not_started"),
//    playlistFinished("playlist_finished"),
//    playlistLocked ("playlist_locked"),
//    userNotAuthorized ("user_not_authorized"),
//    userNotLeader("user_not_leader"),
//    userNotInEvent ("user_not_in_event"),
//    userNotInPlaylist("user_not_in_playlist"),
//    invalidData ("invalid_data");


//    SocketServerErrorReason(String name) {
//        this.name = name;
//    }
//
//    private final String name;
//
//    public String getName() {
//        return name;
//    }

}
