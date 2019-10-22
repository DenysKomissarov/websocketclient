package entities;

import java.util.List;
import java.util.Set;

public class SocketPlaylistModel {

    private String event_id;

    private String playlist_id;

    private SocketPlaylistStatus playlist_status;

    private String name;

    private Set<SocketUserModel> listeners;

    private List<SocketTrackModel> tracks;

    public SocketPlaylistModel() {
    }

    public SocketPlaylistModel(String event_id, String playlist_id, SocketPlaylistStatus playlist_status,
                               String name, Set<SocketUserModel> listeners, List<SocketTrackModel> tracks) {
        this.event_id = event_id;
        this.playlist_id = playlist_id;
        this.playlist_status = playlist_status;
        this.name = name;
        this.listeners = listeners;
        this.tracks = tracks;
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

    public SocketPlaylistStatus getPlaylist_status() {
        return playlist_status;
    }

    public void setPlaylist_status(SocketPlaylistStatus playlist_status) {
        this.playlist_status = playlist_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SocketUserModel> getListeners() {
        return listeners;
    }

    public void setListeners(Set<SocketUserModel> listeners) {
        this.listeners = listeners;
    }

    public List<SocketTrackModel> getTracks() {
        return tracks;
    }

    public void setTracks(List<SocketTrackModel> tracks) {
        this.tracks = tracks;
    }


}
