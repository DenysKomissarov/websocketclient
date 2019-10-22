package messages.webSocket;

import entities.SocketPlaylistStatus;
import org.codehaus.jackson.annotate.JsonProperty;

public class BaseServerPlaylistStateSMsg extends BaseServerNotificationSMsg {

    @JsonProperty("playlist_id")
    private String playlistID;

    @JsonProperty("playlist_status")
    private SocketPlaylistStatus playlistStatus;

    @JsonProperty("track_id")
    private String trackID;

    @JsonProperty("track_position")
    private long trackPosition;

    @JsonProperty("action_time")
    private long actionTime;

    public BaseServerPlaylistStateSMsg(String eventId, String playlistID, SocketPlaylistStatus playlistStatus, String trackID, long trackPosition) {
        super(eventId);
        this.playlistID = playlistID;
        this.playlistStatus = playlistStatus;
        this.trackID = trackID;
        this.trackPosition = trackPosition;
        this.actionTime = System.currentTimeMillis();
    }


    public long getActionTime() {
        return actionTime;
    }

    public void setActionTime(long actionTime) {
        this.actionTime = actionTime;
    }

    public String getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(String playlistID) {
        this.playlistID = playlistID;
    }

    public SocketPlaylistStatus getPlaylistStatus() {
        return playlistStatus;
    }

    public void setPlaylistStatus(SocketPlaylistStatus playlistStatus) {
        this.playlistStatus = playlistStatus;
    }

    public String getTrackID() {
        return trackID;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }

    public long getTrackPosition() {
        return trackPosition;
    }

    public void setTrackPosition(long trackPosition) {
        this.trackPosition = trackPosition;
    }
}
