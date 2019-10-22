package messages.webSocket.client;


import messages.webSocket.SocketRoute;

public class ClientStartEventSMsg extends ClientBaseNotificationSMsg{

    private String route = SocketRoute.event_start.name();
}
