package messages.webSocket;

import org.codehaus.jackson.annotate.JsonProperty;

public class BaseServerNotificationSMsg extends BaseServerSocketMsg {

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("is_need_confirmation")
    private boolean needDeliveryСonfirmation;




    public BaseServerNotificationSMsg(String eventId) {
        super(eventId);

    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }



//    @JsonProperty("is_need_delivery_confirmation")
    public boolean isNeedDeliveryСonfirmation() {
        return needDeliveryСonfirmation;
    }

    public void setNeedDeliveryСonfirmation(boolean needDeliveryСonfirmation) {
        this.needDeliveryСonfirmation = needDeliveryСonfirmation;
    }

}
