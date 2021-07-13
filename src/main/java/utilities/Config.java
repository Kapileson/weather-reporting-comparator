package utilities;

import exceptions.NullEnvironmentVariableException;

public class Config {

    private static Config config;
    private final String CITY;
    private final String ALLOWED_VARIANCE_TEMP;
    private final String ALLOWED_VARIANCE_HUMID;
    private final String ALLOWED_VARIANCE_PRESSURE;
    private final String API_KEY;
    private final String BASE_URI = "https://api.openweathermap.org/data/2.5/weather";
    private final String APP_URL = "https://www.accuweather.com";

    private Config(){
        CITY = getEnvironmentVariable("CITY");
        ALLOWED_VARIANCE_TEMP = getEnvironmentVariable("ALLOWED_VARIANCE_TEMP");
        ALLOWED_VARIANCE_HUMID = getEnvironmentVariable("ALLOWED_VARIANCE_HUMID");
        ALLOWED_VARIANCE_PRESSURE = getEnvironmentVariable("ALLOWED_VARIANCE_PRESSURE");
        API_KEY = getEnvironmentVariable("API_KEY");
    }

    public static void load(){
        if (config == null){
            config = new Config();
        }
    }

    public static String getCity(){
        return config.CITY;
    }

    public static String getAllowedVarianceTemp(){ return config.ALLOWED_VARIANCE_TEMP; }

    public static String getAllowedVarianceHumid(){
        return config.ALLOWED_VARIANCE_HUMID;
    }

    public static String getAllowedVariancePressure(){
        return config.ALLOWED_VARIANCE_PRESSURE;
    }

    public static String getApiKey(){
        return config.API_KEY;
    }

    public static String getBaseUri(){
        return config.BASE_URI;
    }

    public static String getAppUrl(){ return config.APP_URL; }



    private static String getEnvironmentVariable(String variableName){
        String value = System.getenv(variableName);
        if(value==null){
            throw new NullEnvironmentVariableException("Environment variable ["+variableName+"] is null");
        }
        return value;
    }
}
