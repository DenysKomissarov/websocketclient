package messages.webSocket.client;


import messages.webSocket.SocketRoute;

public class ClientEndEventSMsg extends ClientBaseNotificationSMsg {

    private String route = SocketRoute.event_end.name();
}
