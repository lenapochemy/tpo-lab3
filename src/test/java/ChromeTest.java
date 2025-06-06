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
    public void loginTest() throws InterruptedException{
        HoyoTest.loginTest(driver, "pochemylena@mail.ru");
    }

    @Test
    public void registrationTest() throws InterruptedException{
        HoyoTest.registrationTest(driver, "pochpochlena@mail.ru");
    }

    @Test
    public void labTest() throws InterruptedException{
        HoyoTest.toLab(driver, "367519@niuitmo.ru", "user_57c90fub0c",
                "367519@edu.itmo.ru", "red367", "463043807");
    }
}

