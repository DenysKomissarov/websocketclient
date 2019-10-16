package utility;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageSending {


    public void SendMessageToAnotherServer(String json, String postfix)
            throws ClientProtocolException, IOException {


        String url = "http://localhost:8080/UIS/auth/registration";

        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url + postfix);

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println("response: " + response);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            client.close();
        }


    }
}
