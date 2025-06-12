package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserPage extends Page {

    private final By userNickname = By.xpath("//span[@class='user-basic-nickname']");
    private final By followButton = By.xpath("//div[@class='mhy-follow-btn']//div[@role='button' and span]");
    private final By follow = By.xpath("//div[@class='mhy-follow-btn']//div[@role='button']//span");
    private final By askNews = By.xpath("//button[span]");
    private final By newsInfo = By.xpath("//button//span");

    public UserPage(WebDriver driver) {
        super(driver);
        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='account-center-user-wrap']")));
    }


    public String getUserNickname() {
        return driver.findElement(userNickname).getText();
    }

    public void clickFollowButton() {
        driver.findElement(followButton).click();
    }

    public boolean isFollow() {
        try {
            customWait(3).until(ExpectedConditions.textToBePresentInElementLocated(follow, "Подписаны"));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void clickAskNewsButton() {
        driver.findElement(askNews).click();
    }

    public boolean isAskingNews() {
        try {
            customWait(3).until(ExpectedConditions.textToBePresentInElementLocated(newsInfo, "Новинки запрошены"));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

}

