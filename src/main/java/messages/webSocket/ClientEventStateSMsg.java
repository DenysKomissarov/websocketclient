package messages.webSocket;

public class ClientEventStateSMsg extends ClientBaseMsg {

    private String route = SocketRoute.EVENT_STATE.getName();

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ClientEventStateSMsg{" +
                "route='" + route + '\'' +
                ", num=" + num +
                '}';
    }
}
