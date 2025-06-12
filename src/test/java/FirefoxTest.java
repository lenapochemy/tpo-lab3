import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class FirefoxTest {

    WebDriver driver;

    @BeforeEach
    public void init() {
        FirefoxOptions options = new FirefoxOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(1000));
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1000));
    }

    @AfterEach
    public void destroy() {
        driver.quit();
    }

    @Test
    public void titleTest() {
        HoyoTest.titleTest(driver);
    }

    @Test
    public void loginTest() {
        HoyoTest.loginTest(driver, Config.EMAIL);
    }

    @Disabled
    @Test
    public void registrationTest(){
            HoyoTest.registrationTest(driver, Config.EMAIL);
    }

    @Disabled
    @Test
    public void labTest()  {
        HoyoTest.labTest(driver, Config.EMAIL, Config.USERNAME, Config.SECOND_EMAIL, Config.SECOND_USERNAME, Config.SECOND_ID);
    }


}
