import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeTest  {

    WebDriver driver;

    @BeforeEach
    public void init() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
//        options.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(50));
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
    public void loginTest(){
        HoyoTest.loginTest(driver);
    }

    @Test
    public void registrationTest() throws InterruptedException{
        HoyoTest.registrationTest(driver, "redredredmirise@mail.ru");
//        HoyoTest.deleteAccount(driver);
    }

    @Test
    public void labLoginTest() throws InterruptedException{
        HoyoTest.labLogin(driver);
    }

//    @Test
//    public void labRegistrationTest() throws InterruptedException{
//        HoyoTest.labReg(driver, "redmimimire");
//    }

    @Test
    public void labTest() throws InterruptedException{
        HoyoTest.toLab(driver);
    }
}

