

public class ServerEndPlaylistSMsg /*extends BaseServerNotificationSMsg*/ {

    private String route = SocketRoute.PLAYLIST_END.getName();

    private String playlistId;

//    @Override
    public String getRoute() {
        return route;
    }

    public ServerEndPlaylistSMsg(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

}
