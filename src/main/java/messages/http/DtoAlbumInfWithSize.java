package messages.http;



import java.util.List;

public class DtoAlbumInfWithSize {

    private String itemId;

    private String name;

    private List<EventTrackFormWithSize> trackList;

    public DtoAlbumInfWithSize() {
    }

    public DtoAlbumInfWithSize(String itemId, String name, List<EventTrackFormWithSize> trackList) {
        this.itemId = itemId;
        this.name = name;
        this.trackList = trackList;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventTrackFormWithSize> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<EventTrackFormWithSize> trackList) {
        this.trackList = trackList;
    }
}
