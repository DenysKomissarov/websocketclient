package entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class SocketTrackModel {

    private String event_id;

    private String playlist_id;

    private String track_id;

    private int track_order_position;

    private String name;

    private long duration;

    @JsonProperty("file_url")
    private String fileUrl;

    public SocketTrackModel(String playlist_id, String track_id, int track_order_position, String name, long duration, String fileUrl, String eventId) {
        this.playlist_id = playlist_id;
        this.track_id = track_id;
        this.track_order_position = track_order_position;
        this.name = name;
        this.duration = duration;
        this.fileUrl = fileUrl;
        this.event_id = eventId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(String playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public int getTrack_order_position() {
        return track_order_position;
    }

    public void setTrack_order_position(int track_order_position) {
        this.track_order_position = track_order_position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "SocketTrackModel{" +
                "event_id='" + event_id + '\'' +
                ", playlist_id='" + playlist_id + '\'' +
                ", track_id='" + track_id + '\'' +
                ", track_order_position=" + track_order_position +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}
