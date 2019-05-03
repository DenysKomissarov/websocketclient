package handlers.impls;

import entities.ResponseMessage;
import handlers.TestMessageHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import utility.JSON;

public class MessageHandlerImpl implements TestMessageHandler {

    private ResponseMessage responseMessage;

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    private final JSON json = new JSON();

    @Override
    public void handleMessage(String message) {
        System.out.println(message);

        ResponseMessage responseMessage2 = json.deSerialize(message, ResponseMessage.class);

    }
}
