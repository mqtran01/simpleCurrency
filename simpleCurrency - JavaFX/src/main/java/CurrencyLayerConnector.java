import java.io.IOException;
import java.net.URLConnection;
import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.*;

public class CurrencyLayerConnector {

    private static final String URL = "http://www.apilayer.net/api/live";
    private static final String ACCESS_KEY = "7ce86ab4c16f464ad90e91a858ae6977";

    // TODO consider BigDecimal
    // TODO update exception thrown
    public static HashMap<String, Double> enquireAPI() throws Exception {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(URL + "?access+key=" + ACCESS_KEY);
        try {

            CloseableHttpResponse response = null;

        } catch (Exception e) {

        }





        /* Pseudo-code
        Connect to server
        Send payload
        Receive response
        Convert to HashMap
        return HashMap
         */
        URLConnection conn;
        return  null;
    }
}
