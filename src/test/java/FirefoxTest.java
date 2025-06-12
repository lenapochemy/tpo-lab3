import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.HomePage;

import java.time.Duration;

public class FirefoxTest {

    static WebDriver driver;
    static HomePage homePage;

    @BeforeAll
    public static void init() {
        FirefoxOptions options = new FirefoxOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(1000));
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1000));
        homePage = HomePage.init(driver);
    }

    @AfterAll
    public static void destroy() {
        driver.quit();
    }

    @Test
    public void titleTest() {
        HoyoTest.titleTest(homePage);
    }

    @Test
    public void loginTest() {
        HoyoTest.loginTest(driver, homePage, Config.EMAIL, Config.PASSWORD);
    }

    @Test
    public void registrationTest() {
        HoyoTest.registrationTest(driver, homePage, Config.EMAIL, Config.PASSWORD);
    }

    @Test
    public void labTest()  {
        HoyoTest.labTest(driver, homePage, Config.EMAIL, Config.USERNAME, Config.SECOND_EMAIL, Config.SECOND_USERNAME,
                Config.SECOND_ID, Config.PASSWORD);
    }

}
