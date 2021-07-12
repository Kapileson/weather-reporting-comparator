import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utilities.Config;
import utilities.SeleniumDriver;

public class Initializer {

    @BeforeAll
    public void init(){
        Config.load();
        SeleniumDriver.initDriver();
        SeleniumDriver.openUrl(Config.getAppUrl());
    }

    @AfterAll
    public void tearDown(){
        SeleniumDriver.tearDown();
    }
}
