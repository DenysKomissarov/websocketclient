package messages.webSocket.client;


import messages.webSocket.SocketRoute;

public class ClientJoinEventSMsg extends ClientBaseNotificationSMsg {

    private String route = SocketRoute.user_join_event.name();

    @Override
    public String toString() {
        return "ClientJoinEventSMsg{" +
                "route='" + route + '\'' +
                '}';
    }
}
