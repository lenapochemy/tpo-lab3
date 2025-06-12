import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeTest {

    WebDriver driver;

    @BeforeEach
    public void init() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(40));
        driver = new ChromeDriver(chromeOptions);
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

    @Test
    public void registrationTest() {
        HoyoTest.registrationTest(driver, Config.EMAIL);
    }

    @Test
    public void labTest()  {
        HoyoTest.labTest(driver, Config.EMAIL, Config.USERNAME, Config.SECOND_EMAIL, Config.SECOND_USERNAME, Config.SECOND_ID);
    }
}

