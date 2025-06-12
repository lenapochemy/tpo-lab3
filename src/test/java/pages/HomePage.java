package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends Page {

    private final By loginButton = By.xpath("//div[contains(text(), ' Войти')]");
    private final By accountInfo = By.xpath("//div[@class='user-info-wrap']//div[@class='has-logoin']");
    private final By hoyolabButton = By.xpath("//div[@class='menu-list']//div//a[div[contains(text(),'HoYoLAB')]]");

    public HomePage(WebDriver driver) {
        super(driver);
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public static HomePage init(WebDriver driver) {
        driver.get("https://hsr.hoyoverse.com/ru-ru/home");
        return new HomePage(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }


    public LoginPage goToLoginPage() {
        WebElement element = driver.findElement(loginButton);
        element.click();
        return new LoginPage(driver);
    }

    public Boolean isLogin() {
        return !immediateFindElements(accountInfo, 2).isEmpty() && immediateFindElements(loginButton, 2).isEmpty();
    }


    public LabPage goToLabPage() {
        WebElement element = driver.findElement(hoyolabButton);
        String link = element.getAttribute("href");
        driver.get(link);
//        driver.navigate().to(link);
        return new LabPage(driver);
    }
}
