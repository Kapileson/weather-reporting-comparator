import exceptions.WeatherMatcherException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import utilities.ApiHandler;
import utilities.Config;
import utilities.SeleniumDriver;
import utilities.WebActions;

import java.util.HashMap;
import java.util.Map;


public class WeatherReportTest {

    @BeforeAll
    public static void init(){
        Config.load();
        SeleniumDriver.initDriver();
        SeleniumDriver.openUrl(Config.getAppUrl());
    }

    @AfterAll
    public static void tearDown(){
        SeleniumDriver.tearDown();
    }

    @Test
    public void testWeatherReport(){
        Weather actualValueFromUI = getValueFromUI();
        if(!actualValueFromUI.equals(getValueFromAPI()))
            throw new WeatherMatcherException("Matcher exception occurred! Actual fetched values from UI are -> " + actualValueFromUI);
    }

    private Weather getValueFromUI(){
        By humidity = By.xpath("//div[text()='Humidity']/following-sibling::div");
        WebActions.typeValueAndPressEnter(By.name("query"), Config.getCity());
        boolean adFlag = WebActions.isAdPopupHandled(By.xpath("//span[@class='text']"));
        if(adFlag)
            WebActions.click(By.xpath("//span[@class='text']"), humidity);

        Weather ui = new Weather();
        ui.setTemperature(WebActions.getValue(By.className("display-temp")).replace("°C", ""));
        ui.setHumidity(WebActions.getValue(humidity).replace("%", ""));
        ui.setPressure(WebActions.getValue(By.xpath("//div[text()='Pressure']/following-sibling::div"))
                .substring(1)
                .replace("mbar", "").trim());
        return ui;
    }

    private Weather getValueFromAPI(){
        ApiHandler apiHandler = new ApiHandler(Config.getBaseUri());
        Map<String, String> params = new HashMap<>();
        params.put("q", Config.getCity());
        params.put("appid", Config.getApiKey());
        Map<String, Float> apiValue = apiHandler.getRequest(params, "main");
        Weather api = new Weather();
        api.setTemperature(api.convertKelvinToCelsius(apiValue.get("temp")));
        api.setHumidity(String.valueOf(apiValue.get("humidity")));
        api.setPressure(String.valueOf(apiValue.get("pressure")));
        return api;
    }

}
