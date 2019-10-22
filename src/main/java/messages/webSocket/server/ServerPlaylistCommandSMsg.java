package messages.webSocket.server;


import entities.SocketPlaylistStatus;
import messages.webSocket.BaseServerPlaylistStateSMsg;
import messages.webSocket.SocketRoute;

public class ServerPlaylistCommandSMsg extends BaseServerPlaylistStateSMsg {

    private String route = SocketRoute.playlist_command.name();

    private long delay;

    public ServerPlaylistCommandSMsg(String eventId, String playlistID, SocketPlaylistStatus playlistStatus, String trackID, long trackPosition, long delay) {
        super(eventId, playlistID, playlistStatus, trackID, trackPosition);
        this.delay = delay;
    }



    @Override
    public String getRoute() {
        return route;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
}
