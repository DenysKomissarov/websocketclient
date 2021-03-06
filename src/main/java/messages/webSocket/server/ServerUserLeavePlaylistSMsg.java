package messages.webSocket.server;

import entities.SocketUserModel;
import messages.webSocket.BaseServerNotificationSMsg;
import messages.webSocket.SocketRoute;
import org.codehaus.jackson.annotate.JsonProperty;

public class ServerUserLeavePlaylistSMsg extends BaseServerNotificationSMsg {

    private String route = SocketRoute.user_leave_playlist.name();

    private SocketUserModel user;

    @JsonProperty("playlist_id")
    private String playlistId;

    public ServerUserLeavePlaylistSMsg(String eventId, SocketUserModel user, String playlistId) {
        super(eventId);
        this.user = user;
        this.playlistId = playlistId;
    }

    @Override
    public String getRoute() {
        return route;
    }
    public SocketUserModel getUser() {
        return user;
    }

    public void setUser(SocketUserModel user) {
        this.user = user;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    @Override
    public String toString() {
        return "ServerUserLeavePlaylistSMsg{" +
                "route='" + route + '\'' +
                ", user=" + user +
                ", playlistId='" + playlistId + '\'' +
                '}';
    }
}
