package messages.webSocket.client;

import messages.webSocket.SocketRoute;
import org.codehaus.jackson.annotate.JsonProperty;

public class ClientJoinPlaylistSMsg extends ClientBaseNotificationSMsg {

    private String route = SocketRoute.user_join_playlist.name();

    @JsonProperty("playlist_id")
    private String playlistId;

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
