package messages.webSocket.server;


import entities.SocketUserModel;
import messages.webSocket.BaseServerNotificationSMsg;
import messages.webSocket.SocketRoute;

public class ServerUserJoinEventSMsg extends BaseServerNotificationSMsg {

    private String route = SocketRoute.user_join_event.name();

    private SocketUserModel user;

    public ServerUserJoinEventSMsg(String eventId, SocketUserModel user) {
        super(eventId);
        this.user = user;
    }

    @Override
    public String getRoute() {
        return route;
    }

    @Override
    public void setRoute(String route) {
        this.route = route;
    }

    public SocketUserModel getUser() {
        return user;
    }

    public void setUser(SocketUserModel user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ServerUserJoinEventSMsg{" +
                "route='" + route + '\'' +
                ", user=" + user +
                '}';
    }
}
