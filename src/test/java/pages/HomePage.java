package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.file.WatchEvent;
import java.util.Set;

public class HomePage extends Page {

    private final By loginButton = By.xpath("//div[contains(text(), ' Войти')]");
    private final By accountInfo = By.xpath("//div[@class='user-info-wrap']//div[@class='has-logoin']");
    private final By accountPage = By.xpath("//span[@class='login=page']");

    private final By anotherButton = By.xpath("//div[@class='drop-menu']");
    private final By hoyolabButton = By.xpath("//div[@class='menu-list']//div//a[div[contains(text(),'HoYoLAB')]]");
//    private final By hoyolabButton = By.xpath("//div[@class='menu-list']//div[a[div[contains(text(),'HoYoLAB')]]]");

    public HomePage(WebDriver driver) {
        super(driver);

        WebElement element = this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public static HomePage init(WebDriver driver){
        driver.get("https://hsr.hoyoverse.com/ru-ru/home");
        return new HomePage(driver);
    }

    public String getTitle(){
        return driver.getTitle();
    }


    public LoginPage goToLoginPage(){
//        WebElement element = this.wait.until(
//                ExpectedConditions.visibilityOfElementLocated(loginButton));
        WebElement element = driver.findElement(loginButton);
        element.click();
        return new LoginPage(driver);
    }

    public Boolean isLogin(){
        return !driver.findElements(accountInfo).isEmpty() && driver.findElements(loginButton).isEmpty();
    }


    public AccountPage goToAccountPage(){
        driver.findElement(accountInfo).click();
        String currentWindow = driver.getWindowHandle();
        driver.findElement(accountPage).click();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        return new AccountPage(driver);
    }


    public LabPage goToLabPage(){
//        WebElement anotherButtonElement = driver.findElement(anotherButton);
//        Actions actions = new Actions(driver);
//        actions.moveToElement(anotherButtonElement).perform();

        WebElement element = driver.findElement(hoyolabButton);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(hoyolabButton));
//        WebElement element = this.wait.until(
//                ExpectedConditions.elementToBeClickable(hoyolabButton));
//        element.click();
        String link = element.getAttribute("href");
//        System.out.println(link);
//        driver.get(link);
        driver.navigate().to(link);
//        driver.get("https://www.hoyolab.com/circles/6/39/official?page_type=official&page_sort=news&utm_source=officialweb&utm_medium=toptoolbar&utm_id=6");
        return new LabPage(driver);
    }
}
