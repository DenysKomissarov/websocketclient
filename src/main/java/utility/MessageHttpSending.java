package utility;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class MessageHttpSending {

    private final String url = "http://localhost:8080/UIS/auth/registration";


    public String SendMessageToAnotherServer(String json, String postfix)
            throws ClientProtocolException, IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url + postfix);

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();
            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println(responseString);

            String[] arr = responseString.split(",");
            String[] subArr = arr[1].split(":");
            if (subArr[0].equals("\"userId\"")){
                return subArr[1].replace("\"}", "");
            }
            else{
                return null;
            }


        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            client.close();
        }

        return null;

    }
}
