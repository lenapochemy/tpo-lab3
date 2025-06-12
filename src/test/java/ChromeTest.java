import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;

import java.time.Duration;

public class ChromeTest {

    static WebDriver driver;
    static HomePage homePage;

    @BeforeAll
    public static void init() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(40));
        driver = new ChromeDriver(chromeOptions);
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

