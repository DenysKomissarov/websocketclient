package messages.webSocket.client;

import messages.webSocket.SocketRoute;

public class ClientEventStateSMsg extends ClientBaseNotificationSMsg {

    private String route = SocketRoute.event_state.name();

@Override
public String toString() {
    return "ClientEventStateSMsg{" +
            "route='" + route + '\'' +
            '}';
}
}
