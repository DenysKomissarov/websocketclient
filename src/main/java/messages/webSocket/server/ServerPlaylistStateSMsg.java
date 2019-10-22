package messages.webSocket.server;


import entities.SocketPlaylistStatus;
import messages.webSocket.BaseServerPlaylistStateSMsg;
import messages.webSocket.SocketRoute;

public class ServerPlaylistStateSMsg extends BaseServerPlaylistStateSMsg {

    private String route = SocketRoute.playlist_state.name();

    public ServerPlaylistStateSMsg(String eventId, String playlistID, SocketPlaylistStatus playlistStatus, String trackID, long trackPosition) {
        super(eventId, playlistID, playlistStatus, trackID, trackPosition);
    }

    @Override
    public String getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "ServerPlaylistStateSMsg{" +
                "route='" + route + '\'' +
                '}';
    }
}
