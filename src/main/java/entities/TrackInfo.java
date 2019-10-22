package entities;

import com.uidance.entity.PlaylistTrackCatalog;
import com.uidance.utility.Parser;

import java.sql.Time;

public class TrackInfo {

    public TrackInfo() {
        //for jackson
    }

    public TrackInfo(PlaylistTrackCatalog playlistTrackCatalog) {
        trackId = playlistTrackCatalog.getEventTrack().getId();
        trackName = playlistTrackCatalog.getEventTrack().getTrackName();
        Long preDuration = Parser.parseTime(playlistTrackCatalog.getEventTrack().getDuration());
        duration = preDuration == null ? Time.valueOf("00:00:00").getTime() : preDuration;
    }

    private String trackId;
    private String trackName;
    private long duration;

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}