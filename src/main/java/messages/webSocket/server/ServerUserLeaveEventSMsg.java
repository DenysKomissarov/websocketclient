package messages.webSocket.server;


import entities.SocketUserModel;
import messages.webSocket.BaseServerNotificationSMsg;
import messages.webSocket.SocketRoute;

public class ServerUserLeaveEventSMsg extends BaseServerNotificationSMsg {

    private String route = SocketRoute.user_leave_event.name();

    private SocketUserModel user;

    public ServerUserLeaveEventSMsg(String eventId, SocketUserModel user) {
        super(eventId);
        this.user = user;
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

    @Override
    public String toString() {
        return "ServerUserLeaveEventSMsg{" +
                "route='" + route + '\'' +
                ", user=" + user +
                '}';
    }
}
