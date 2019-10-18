package messages.http;


public class EventTrackFormWithSize {

    private String trackId;

    private String trackName;

    private String duration;

    private String songFile;

    private Integer serialNumber;

    private long size;

    public EventTrackFormWithSize(String trackId, String trackName, String duration, String songFile, Integer serialNumber, long size) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.duration = duration;
        this.songFile = songFile;
        this.serialNumber = serialNumber;
        this.size = size;
    }

    public EventTrackFormWithSize() {
    }

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSongFile() {
        return songFile;
    }

    public void setSongFile(String songFile) {
        this.songFile = songFile;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
