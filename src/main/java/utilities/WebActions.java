package utilities;

import exceptions.ElementNotClickedException;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class WebActions extends SeleniumDriver{

    public static WebElement findElement(By by){
        WebElement wEle;
        try{
            wEle = driver.findElement(by);
        }catch(StaleElementReferenceException e){
            wEle = driver.findElement(by);
        }
        return wEle;
    }

    public static void typeValueAndPressEnter(By by, String value){
        WebElement wEle = findElement(by);
        wEle.sendKeys(value);
        wEle.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public static boolean isAdPopupHandled(By elementToClick){
        findElement(elementToClick).click();
        driver.switchTo().frame(findElement(By.xpath("(//iframe[@title='3rd party ad content'])[6]")));
        if(findElement(By.id("ad_position_box"))!=null){
            refresh();
            return true;
        }
        return false;
    }

    public static void click(By elementToClick, By visibleElementAfterClick){
        findElement(elementToClick).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try{
            driver.findElement(visibleElementAfterClick);
        }catch (NoSuchElementException e){
            throw new ElementNotClickedException("Element ["+ elementToClick+"] is failed to click!");
        }
    }

    public static String getValue(By by){
        return findElement(by).getText().trim();
    }

    public static void refresh(){
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

}
