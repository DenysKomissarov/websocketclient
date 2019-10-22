package messages.webSocket.server;


import messages.webSocket.BaseServerNotificationSMsg;
import messages.webSocket.SocketRoute;

public class ServerEndEventSMsg extends BaseServerNotificationSMsg {

    private String route = SocketRoute.event_end.name();

    public ServerEndEventSMsg(String eventId) {
        super(eventId);
    }

    @Override
    public String getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "ServerEndEventSMsg{" +
                "route='" + route + '\'' +
                '}';
    }
}
