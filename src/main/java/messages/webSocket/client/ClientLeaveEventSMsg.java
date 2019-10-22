package messages.webSocket.client;

import messages.webSocket.SocketRoute;

public class ClientLeaveEventSMsg extends ClientBaseNotificationSMsg {

    private String route = SocketRoute.user_leave_event.name();

    @Override
    public String toString() {
        return "ClientLeaveEventSMsg{" +
                "route='" + route + '\'' +
                '}';
    }
}
