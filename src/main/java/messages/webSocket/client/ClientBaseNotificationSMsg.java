package messages.webSocket.client;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClientBaseNotificationSMsg extends ClientBaseMsg {

    @JsonProperty("is_need_confirmation")
    private boolean isNeedConfirmation;


    public boolean isNeedConfirmation() {
        return isNeedConfirmation;
    }


    public void setNeedConfirmation(boolean needConfirmation) {
        isNeedConfirmation = needConfirmation;
    }
}
