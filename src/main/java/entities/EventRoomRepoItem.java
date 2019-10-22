package entities;

import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class EventRoomRepoItem {

    private final Logger LOG = Logger.getLogger(this.getClass());

    private final String eventId;
    private final String leaderId;
    private final String leaderName;
    private String eventStatus;
    private Set<User> people;
    private Set<PlayListItem> playlists;

    public EventRoomRepoItem(String eventId, String eventStatus, String leaderId, String leaderName) {
        this.eventId = eventId;
        this.eventStatus = eventStatus;
        this.leaderId = leaderId;
        this.leaderName = leaderName;
        this.people = new HashSet<>();
        this.playlists = new HashSet<>();
    }

    public void updatePlaylists(Set<PlayListItem> playListItemSet) {
        //add new
        for (PlayListItem playListItem : playListItemSet) {
            if (!playlists.contains(playListItem)) {
                playlists.add(playListItem);
                LOG.debug("EventRoomRepoItem updatePlaylists add eventId:" + eventId + " itemId:" + playListItem.getItemId());
            } else {
                playlists.stream()
                        .filter(x -> x.equals(playListItem))
                        .findAny().ifPresent(existPlaylistItem -> existPlaylistItem.setItemName(playListItem.getItemName()));
            }
        }
        //remove old
        playlists.removeIf(playListItem -> {
            boolean answer = !playListItemSet.contains(playListItem);
            if (answer) {
                LOG.debug("EventRoomRepoItem updatePlaylists delete eventId:" + eventId + " itemId:" + playListItem.getItemId());
            }
            return answer;
        });
    }

    public String getEventId() {
        return eventId;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public Set<User> getPeople() {
        return people;
    }

    public Set<PlayListItem> getPlaylists() {
        return playlists;
    }

    public void setPeople(Set<User> people) {
        this.people = people;
    }

    public void setPlaylists(Set<PlayListItem> playlists) {
        this.playlists = playlists;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public EventRoomRepoItem cloneItem() {
        EventRoomRepoItem newObj = new EventRoomRepoItem(eventId, eventStatus, leaderId, leaderName);
        newObj.setPeople(new HashSet<>(people));
        newObj.setPlaylists(new HashSet<>(playlists));
        return newObj;
    }
}
