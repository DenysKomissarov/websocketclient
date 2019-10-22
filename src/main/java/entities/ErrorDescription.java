package entities;

public enum ErrorDescription {

    NOT_AUTHORIZED("not authorized"),
    EVENT_ALREADY_FINISHED_OR_YOU_CAN_NOT_ACCESS_TO_IT("event already finished or you can't access to it"),
    SERVER_ERROR("SERVER ERROR"),
    USER_NOT_A_LEADER_OR_EVENT_NOT_STARTED("user not a leader or event not started"),
    USER_IS_NOT_IN_THE_EVENT("user is not in the event"),
    USER_IS_NOT_IN_THE_PLAYLIST("user is not in the playlist"),
    STREAM_CAN_NOT_BE_PAUSED ("stream can't be paused"),
    STREAM_CAN_NOT_BE_REWIND_TO_TRACK("stream can't be rewind to track"),
    INVALID_DATA("invalid data"),
    EVENT_IS_NOT_STARTED("event is not started"),
    PLAYER_CAN_NOT_START("player can't start"),
    ALL_PLAYLISTS_ALREADY_FINISHED("all playlists already finished"),
    EVENT_CAN_NOT_BE_STARTED("event can't be started"),
    EVENT_CAN_NOT_BE_CLOSED("event can't be closed");


    ErrorDescription(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
