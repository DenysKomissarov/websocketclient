package messages.webSocket.client;

import entities.SocketPlaylistStatus;
import messages.webSocket.SocketRoute;
import org.codehaus.jackson.annotate.JsonProperty;

public class ClientPlaylistCommandSMsg extends ClientBaseNotificationSMsg {

    private String route = SocketRoute.playlist_command.name();

    @JsonProperty("playlist_id")
    private String playlistId;

    @JsonProperty("playlist_status")
    private SocketPlaylistStatus playlistStatus;

    @JsonProperty("track_id")
    private String trackId;

    @JsonProperty("track_position")
    private int trackPosition;

    @JsonProperty("action_time")
    private long actionTime;

    public long getActionTime() {
        return actionTime;
    }

    public void setActionTime(long actionTime) {
        this.actionTime = actionTime;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public SocketPlaylistStatus getPlaylistStatus() {
        return playlistStatus;
    }

    public void setPlaylistStatus(SocketPlaylistStatus playlistStatus) {
        this.playlistStatus = playlistStatus;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public int getTrackPosition() {
        return trackPosition;
    }

    public void setTrackPosition(int trackPosition) {
        this.trackPosition = trackPosition;
    }
}
