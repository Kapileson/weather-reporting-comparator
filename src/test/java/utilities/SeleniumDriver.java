package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDriver {

    static WebDriver driver = null;

    public static void initDriver(){
        if(driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    public static void openUrl(String url){
        driver.get(url);
    }

    public static void tearDown(){
        driver.quit();
    }


}
