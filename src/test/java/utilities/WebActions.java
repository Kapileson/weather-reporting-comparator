package utilities;

import exceptions.ElementNotClickedException;
import org.openqa.selenium.*;

public class WebActions extends SeleniumDriver{

    public static WebElement findElement(By by){
        WebElement wEle = null;
        try{
            wEle = driver.findElement(by);
        }catch(StaleElementReferenceException e){
            wEle = driver.findElement(by);
        }catch(NoSuchElementException e){
            System.out.println("Exception occurred while finding element "+ by);
            e.printStackTrace();
        }
        return wEle;
    }

    public static void typeValue(By by, String value){
        findElement(by).sendKeys(value);
    }

    public static void pressEnter(WebElement wEle){
        wEle.sendKeys(Keys.ENTER);
    }

    public static void typeValueAndPressEnter(By by, String value){
        WebElement wEle = findElement(by);
        wEle.sendKeys(value);
        wEle.sendKeys(Keys.ENTER);
    }

    public static void click(By elementToClick, By visibleElementAfterClick){
        findElement(elementToClick).click();
        try{
            driver.findElement(visibleElementAfterClick);
        }catch (NoSuchElementException e){
            throw new ElementNotClickedException("Element ["+ elementToClick+"] is failed to click!");
        }
    }

    public static String getValue(By by){
        return findElement(by).getText();
    }

}
