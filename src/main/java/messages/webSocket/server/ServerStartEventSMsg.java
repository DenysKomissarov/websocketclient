package messages.webSocket.server;

import messages.webSocket.BaseServerNotificationSMsg;
import messages.webSocket.SocketRoute;

public class ServerStartEventSMsg extends BaseServerNotificationSMsg {

    private String route = SocketRoute.event_start.name();

    public ServerStartEventSMsg(String eventId) {
        super(eventId);
    }

    @Override
    public String getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "ServerStartEventSMsg{" +
                "route='" + route + '\'' +
                '}';
    }
}
