/*package com.example.augur.APICoinmarket;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ApiManager {
    private static String apiKey = "839fd452-f13c-46ac-80b2-0ee569437ec9";

    public static ApiResponse<Double> getBitcoinPrice() {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("id", "1"));
        parameters.add(new BasicNameValuePair("convert", "EUR"));

        ApiResponse<Double> result = new ApiResponse<Double>(false, null);

        try {
            ApiResponse<String> response = makeAPICall(uri, parameters);
            boolean success = response.getSuccess();

            if (!success)
                return result;

            JSONObject responseContent = new JSONObject(response.getData());
            JSONObject bitcoinObject = responseContent.getJSONObject("data").getJSONObject("1");

            double price = bitcoinObject.getJSONObject("quote").getJSONObject("EUR").getDouble("price");

            result.setSuccess(success);
            result.setData(price);
        } catch (IOException | URISyntaxException | JSONException e) {
            return result;
        }

        return result;
    }

    private static ApiResponse<String> makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {

        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        // Check if HTTP call worked
        int statusCode = response.getStatusLine().getStatusCode();
        boolean goodResponse = Helper.checkHTTPStatus(statusCode);

        try {
            org.apache.http.HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return new ApiResponse<String>(goodResponse, response_content);
    }

}
*/