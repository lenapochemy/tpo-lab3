package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public abstract class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public WebDriverWait customWait(int seconds){
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public boolean findNotEmptyText(By xpath, int seconds){
        try {
            customWait(seconds).until(driver1 -> !driver1.findElement(xpath).getText().isEmpty());
        } catch (TimeoutException e){
            return false;
        }
        return true;
    }

    public Duration implicitTo0() {
        var timeout = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        return timeout;
    }

    public void implicitToTimeout(Duration timeout) {
        driver.manage().timeouts().implicitlyWait(timeout);
    }

    public List<WebElement> immediateFindElements(By xpath) {
        var timeout = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        List<WebElement> result = driver.findElements(xpath);
        driver.manage().timeouts().implicitlyWait(timeout);
        return result;
    }

    public List<WebElement> immediateFindElements(By xpath, int seconds) {
        var timeout = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        List<WebElement> result = driver.findElements(xpath);
        driver.manage().timeouts().implicitlyWait(timeout);
        return result;
    }
}