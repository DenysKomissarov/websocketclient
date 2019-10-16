public class ServerErrorSMsg extends BaseServerSocketMsg {

    private String route = SocketRoute.ERROR.getName();

    private String errorDescription;

    private SocketRoute targetRoute;

    private String targetMessageId;

    public ServerErrorSMsg() {
    }

    public ServerErrorSMsg(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public String getRoute() {
        return route;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public SocketRoute getTargetRoute() {
        return targetRoute;
    }

    public void setTargetRoute(SocketRoute targetRoute) {
        this.targetRoute = targetRoute;
    }

    public String getTargetMessageId() {
        return targetMessageId;
    }

    public void setTargetMessageId(String targetMessageId) {
        this.targetMessageId = targetMessageId;
    }


}
