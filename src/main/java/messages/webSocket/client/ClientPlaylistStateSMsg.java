package messages.webSocket.client;

import messages.webSocket.SocketRoute;
import org.codehaus.jackson.annotate.JsonProperty;

public class ClientPlaylistStateSMsg extends ClientBaseNotificationSMsg {


    private String route = SocketRoute.playlist_state.name();

    @JsonProperty("playlist_id")
    private String playlistId;

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    @Override
    public String toString() {
        return "ClientPlaylistStateSMsg{" +
                "route='" + route + '\'' +
                ", playlistId='" + playlistId + '\'' +
                '}';
    }
}
