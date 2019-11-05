package utility;

import config.PropertiesLoader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class MessageHttpSending {


//    private String url = "http://localhost:8080/UIS";

    private PropertiesLoader propertiesLoader;
    private JSON json;
    private String url;

    public MessageHttpSending() {
        this.propertiesLoader = new PropertiesLoader();
        this.json = new JSON();
        this.url = propertiesLoader.getProperty("url");
    }

    public Object SendPostMessageToAnotherServer(String json, String postfix, Class myClass)
            throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        try {

            HttpPost httpPost = new HttpPost(url + postfix);
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpPost);

            return jsonToObject(response, myClass);


        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            client.close();
        }

        return null;

    }

    public Object SendGetMessageToAnotherServer( String postfix, Class myClass)
            throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url + postfix);

            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpGet);

//            HttpEntity httpEntity = response.getEntity();
//            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
//            System.out.println(responseString);

            return jsonToObject(response, myClass);

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            client.close();
        }

        return null;
    }


    private Object jsonToObject(HttpResponse response, Class myClass){


        int status = response.getStatusLine().getStatusCode();


        if (status == 200){
            HttpEntity httpEntity = response.getEntity();
            String responseString = null;
            try {
                responseString = EntityUtils.toString(httpEntity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(responseString);

            return json.deSerialize(responseString, myClass);
        }
        else{
            return null;
        }

    }

}
