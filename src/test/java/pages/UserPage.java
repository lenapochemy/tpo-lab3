package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserPage extends Page{

    private final By userNickname = By.xpath("//span[@class='user-basic-nickname']");
    private final By followButton = By.xpath("//div[@class='mhy-follow-btn']//div[@role='button' and span]");
    private final By follow = By.xpath("//div[@class='mhy-follow-btn']//div[@role='button']//span");
    private final By askNews = By.xpath("//button[span]");
    private final By newsInfo = By.xpath("//button//span");
//    private final By avatar = By.xpath("//div[@class='header-avatar']");
//    private final By logout = By.xpath("//ul//li//div[span[contains(text(), 'Выйти')]]");
//    private final By confirmLogout = By.xpath("//footer//button[span[contains(text(), 'Выйти')]]");
//






    public UserPage(WebDriver driver) {
        super(driver);
        WebElement element = this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='account-center-user-wrap']")));

    }


    public String getUserNickname(){
        return driver.findElement(userNickname).getText();
    }

    public void clickFollowButton(){
        driver.findElement(followButton).click();
    }

    public boolean isFollow(){
        return driver.findElement(follow).getText().equals("Подписаны");
    }

    public void clickAskNewsButton(){
        driver.findElement(askNews).click();
    }

    public boolean isAskingNews(){
        return driver.findElement(newsInfo).getText().equals("Новинки запрошены");
    }

    public void backToLabPage(){
        String currentWindow = driver.getWindowHandle();
//        driver.findElement(firstResult).click();
        this.moveToNewTab(currentWindow);
//        return new LabPage(driver);
    }


}

