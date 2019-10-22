package entities;

public class TrackStatus {

    private String trackId;
    private Long trackState;
    private PlayerStatus playerStatus = PlayerStatus.pause;

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public Long getTrackState() {
        return trackState;
    }

    public void setTrackState(Long trackState) {
        this.trackState = trackState;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }
}
