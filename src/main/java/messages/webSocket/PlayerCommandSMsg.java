package messages.webSocket;

import com.uidance.enumeration.SocketPlaylistStatus;

public class PlayerCommandSMsg extends BaseServerPlaylistStateSMsg {

    private String route = SocketRoute.playlist_command.name();

    private int delay;

    public PlayerCommandSMsg(String eventId, String playlistID, SocketPlaylistStatus playlistStatus, String trackID, long trackPosition, int delay) {
        super(eventId, playlistID, playlistStatus, trackID, trackPosition);
        this.delay = delay;
    }

    @Override
    public String getRoute() {
        return route;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "PlayerCommandSMsg{" +
                "route='" + route + '\'' +
                ", delay=" + delay +
                '}';
    }
}
