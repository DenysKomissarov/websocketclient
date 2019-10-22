package messages.webSocket.server;

import entities.SocketEventStatus;
import entities.SocketPlaylistModel;
import entities.SocketUserModel;
import messages.webSocket.BaseServerNotificationSMsg;
import messages.webSocket.SocketRoute;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.Set;

public class ServerEventStateSMsg extends BaseServerNotificationSMsg {

    private String route = SocketRoute.event_state.name();

    private SocketUserModel leader;

    private String name;

    @JsonProperty("event_status")
    private SocketEventStatus eventStatus;

    private Set<SocketPlaylistModel> playlists;

    @JsonProperty("users_in_room")
    private List<SocketUserModel> usersInRooms;

    public ServerEventStateSMsg(String eventId, SocketUserModel leader, SocketEventStatus eventStatus, Set<SocketPlaylistModel> playlists, List<SocketUserModel> usersInRooms, String eventName) {
        super(eventId);
        this.leader = leader;
        this.eventStatus = eventStatus;
        this.playlists = playlists;
        this.usersInRooms = usersInRooms;
        this.name = eventName;
    }

    @Override
    public String getRoute() {
        return route;
    }
    public SocketUserModel getLeader() {
        return leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeader(SocketUserModel leader) {
        this.leader = leader;
    }

    public SocketEventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(SocketEventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Set<SocketPlaylistModel> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<SocketPlaylistModel> playlists) {
        this.playlists = playlists;
    }

    public List<SocketUserModel> getUsersInRooms() {
        return usersInRooms;
    }

    public void setUsersInRooms(List<SocketUserModel> usersInRooms) {
        this.usersInRooms = usersInRooms;
    }
}
