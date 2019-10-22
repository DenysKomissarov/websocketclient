package entities;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String itemId;
    private TrackStatus trackState;
    private List<String> listeners;

    public Playlist() {
        listeners = new ArrayList<>();
    }

    public String getItemId() {
        return itemId;
    }

    public TrackStatus getTrackState() {
        return trackState;
    }

    public List<String> getListeners() {
        return listeners;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setTrackState(TrackStatus trackState) {
        this.trackState = trackState;
    }

    public void setListeners(List<String> listeners) {
        this.listeners = listeners;
    }
}
