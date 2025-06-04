package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public abstract class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
    }


    protected void moveToNewTab(String originalTab) {
        Set<String> allTabs = driver.getWindowHandles();

        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.close();
                driver.switchTo().window(tab);
                break;
            }
        }

    }

    protected boolean newTabOpened(String originalTab) {
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                return true;
            }
        }
        return false;
    }


    public void refresh() {
        driver.navigate().refresh();
    }
}