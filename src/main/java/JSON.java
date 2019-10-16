
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JSON {


    private final ObjectMapper objectMapper;

    @Autowired
    public JSON(ObjectMapper objectMapper) {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        this.objectMapper = objectMapper;
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
