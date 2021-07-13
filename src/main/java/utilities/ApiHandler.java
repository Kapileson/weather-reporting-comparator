package utilities;

import exceptions.ApiException;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiHandler {

    String baseURI;

    public ApiHandler(String baseURI){
        this.baseURI = baseURI;
    }

    private String UriBuilder(Map<String, String> params){
        StringBuilder uri = new StringBuilder(baseURI + "?");
        for(Map.Entry param : params.entrySet()){
            uri.append(param.getKey()).append("=").append(param.getValue()).append("&");
        }
        if(uri.charAt(uri.length() - 1) == '&')
            uri.deleteCharAt(uri.length() - 1);
        return uri.toString();
    }

    public Map<String, Float> getRequest(Map<String, String> params, String path){
        Response response = RestAssured.get(UriBuilder(params));
        checkIsValidCode(response.statusCode());
        return response.jsonPath().getMap(path);
    }

    public void checkIsValidCode(int statusCode){
        if(statusCode != 200){
            throw new ApiException("Invalid status code! Expected 200 & Actual "+statusCode);
        }
    }

}
