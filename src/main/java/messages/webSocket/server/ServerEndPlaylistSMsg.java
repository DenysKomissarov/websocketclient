package messages.webSocket.server;//package com.uidance.websocket.messages.server;
//
//import com.uidance.enumeration.SocketRoute;
//import com.uidance.websocket.messages.BaseServerNotificationSMsg;
//import org.codehaus.jackson.annotate.JsonProperty;
//
//public class ServerEndPlaylistSMsg extends BaseServerNotificationSMsg {
//
//    private String route = SocketRoute.playlist_end.name();
//
//    @JsonProperty("playlist_id")
//    private String playlistId;
//
//    public ServerEndPlaylistSMsg(String eventId, String playlistId) {
//        super(eventId);
//        this.playlistId = playlistId;
//    }
//
//    @Override
//    public String getRoute() {
//        return route;
//    }
//
//
//    public String getPlaylistId() {
//        return playlistId;
//    }
//
//    public void setPlaylistId(String playlistId) {
//        this.playlistId = playlistId;
//    }
//
//    @Override
//    public String toString() {
//        return "ServerEndPlaylistSMsg{" +
//                "route='" + route + '\'' +
//                ", playlistId='" + playlistId + '\'' +
//                '}';
//    }
//}
