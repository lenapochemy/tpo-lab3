import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    public void loginTest(){
        HoyoTest.loginTest(driver);
    }

    @Test
    public void registrationTest() throws InterruptedException{
        HoyoTest.registrationTest(driver, "redmimimirise@mail.ru");
//        HoyoTest.deleteAccount(driver);
    }

    @Test
    public void labLoginTest() throws InterruptedException{
        HoyoTest.labLogin(driver);
    }

    @Test
    public void labRegistrationTest() throws InterruptedException{
        HoyoTest.labReg(driver, "redmimimire");
    }

    @Test
    public void labTest() throws InterruptedException{
        HoyoTest.toLab(driver);
    }


}
