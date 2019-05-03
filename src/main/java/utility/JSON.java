package utility;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class JSON {


    private final ObjectMapper objectMapper;


    public JSON() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }

    public <T> String serialize(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public <T> T deSerialize(String str, Class<T> obj) {
        try {
            return this.objectMapper.readValue(str, obj);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
