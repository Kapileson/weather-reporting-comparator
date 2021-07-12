package utilities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

public class ApiHandler {

    String baseUri;

    public ApiHandler(String baseUri){
        this.baseUri = baseUri;
    }

    private URI UriBuilder(HashMap<String, String> params){
        StringBuilder uri = new StringBuilder(baseUri + "?");
        for(Map.Entry param : params.entrySet()){
            uri.append(param.getKey()).append("=").append(param.getValue()).append("&");
        }
        if(uri.charAt(uri.length() - 1) == '&')
            uri.deleteCharAt(uri.length() - 1);
        return URI.create(uri.toString());
    }

    public void get(HashMap<String, String> params){
        HttpClient client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(UriBuilder(params))
                .header("accept", "application/json")
                .build();
        try {
            var response = client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error occurred while running GET http request!");
            e.printStackTrace();
        }
    }

}
