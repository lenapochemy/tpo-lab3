package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewArticlePage extends Page {

    private final By postTitleInput = By.xpath("//div[@class='mhy-input title-text']//div//input");
    private final By postInput = By.xpath("//div[@class='ql-container ql-snow']//p");
    private final By groupSelectList = By.xpath("//div[contains(@class,'mhy-select mhy-select-outlined')]");
    private final By categorySelectList = By.xpath("//div[@class='select-line']/following-sibling::div[contains(@class,'mhy-select mhy-select-outlined')]");
    private final By genshinGroupSelectButton = By.xpath("//div[@title='Genshin Impact' and contains(text(), 'Genshin Impact')]");
    private final By group = By.xpath("//div[contains(@class,'mhy-select mhy-select-outlined')]//div//span//span[@class='mhy-select__val']//span");
    private final By category = By.xpath("//div[@class='select-line']/following-sibling::div[contains(@class,'mhy-select mhy-select-outlined')]//div//span//span[@class='mhy-select__val']//span");
    private final By discussionCategoryButton = By.xpath("//div[@title='Обсуждения']");
    private final By originalWorkOff = By.xpath("//div[@class='copyright-settings-original__original-settings']//div[@class='mhy-switch']");
    private final By originalWorkOn = By.xpath("//div[@class='copyright-settings-original__original-settings']//div[@class='mhy-switch mhy-switch--active']");
    private final By repostOff = By.xpath("//div[@class='copyright-settings-original__repost-settings']//div[@class='mhy-switch']");
    private final By repostOn = By.xpath("//div[@class='copyright-settings-original__repost-settings']//div[@class='mhy-switch mhy-switch--active']");
    private final By postSendButton = By.xpath("//button[span[contains(text(), 'Отправить')]]");
    private final By myPostTitle = By.xpath("//div[@class='mhy-article-page__title']//h1");
    private final By myPost = By.xpath("//div[@class='mhy-article-page__content']//p");
    private final By myPostThreeDots = By.xpath("//div[@class='mhy-action']");
    private final By deleteButton = By.xpath("//ul//li[span[contains(text(), 'Удалить')]]");
    private final By confirmDeleteButton = By.xpath("//footer//button[span[contains(text(), 'Удалить')]]");

    public NewArticlePage(WebDriver driver) {
        super(driver);
        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(postTitleInput));

    }

    public void writePostTitle(String title) {
        WebElement element = driver.findElement(postTitleInput);
        element.clear();
        element.sendKeys(title);
    }

    public void writePost(String post) {
        WebElement element = driver.findElement(postInput);
        element.clear();
        element.sendKeys(post);
    }

    public void chooseGroup() {
        driver.findElement(groupSelectList).click();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(genshinGroupSelectButton));
        element.click();
    }

    public boolean getGroupName(String name) {
        try {
            customWait(5).until(ExpectedConditions.textToBePresentInElementLocated(group, name));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void chooseCategory() {
        driver.findElement(categorySelectList).click();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(discussionCategoryButton));
        element.click();
    }

    public boolean getCategoryName(String name) {
        try {
            customWait(5).until(ExpectedConditions.textToBePresentInElementLocated(category, name));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public Boolean isOriginalWork() {
        return immediateFindElements(originalWorkOff).isEmpty() && !immediateFindElements(originalWorkOn).isEmpty();
    }

    public Boolean isRepostRight() {
        return immediateFindElements(repostOff, 2).isEmpty() && !immediateFindElements(repostOn, 2).isEmpty();
    }

    public void clickOriginalWork() {
        if (isOriginalWork()) {
            driver.findElement(originalWorkOn).click();
        } else {
            driver.findElement(originalWorkOff).click();
        }
    }

    public void clickReportRight() {
        if (isRepostRight()) {
            driver.findElement(repostOn).click();
        } else {
            driver.findElement(repostOff).click();
        }
    }

    public void clickPostSend() {
        driver.findElement(postSendButton).click();
    }


    public String getMyPostTitle() {
        WebElement element = customWait(5).until(ExpectedConditions.visibilityOfElementLocated(myPostTitle));
        return element.getText();
    }

    public String getMyPost() {
        return driver.findElement(myPost).getText();
    }

    public void clickDeletePost() {
        driver.findElement(myPostThreeDots).click();
        driver.findElement(deleteButton).click();
        WebElement confirmElement = this.wait.until(
                ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmElement.click();
    }

}
