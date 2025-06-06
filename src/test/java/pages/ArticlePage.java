package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ArticlePage extends Page{
    private final By articleTitle = By.xpath("//div[@class='mhy-article-page__title']//h1");
    private final By followButton = By.xpath("//div[@class='mhy-user-card__follow']//div[span[contains(text(), 'Подписаться')]]");
    private final By unFollowButton = By.xpath("//div[@class='mhy-user-card__follow']//div[span[contains(text(), 'Подписаны')]]");
    private final By reaction = By.xpath("//div[@class='multi-like-bar']/div[1]");
    private final By reactionCount = By.xpath("//div[@class='multi-like-bar']/div[1]//span");
    private final By commentInput = By.xpath("//div[@data-placeholder='Поспешите оставить комментарий!']//p");
    private final By commentSendButton = By.xpath("//div[@class='mhy-reply-box__footer']//div//button");
    private final By firstComment = By.xpath("//div[@data-index=0]//pre//p");
    private final By threeDots = By.xpath("//div[@data-index=0]//div[@class='mhy-action']//div[@role='button']");
    private final By deleteButton = By.xpath("//ul//li[span[contains(text(), 'Удалить')]]");
    private final By confirmDeleteButton = By.xpath("//footer//button[span[contains(text(), 'Удалить')]]");


    public ArticlePage(WebDriver driver) {
        super(driver);
        WebElement element = this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(articleTitle));

    }


    public String getArticleTitle() {
        return driver.findElement(articleTitle).getText();
    }

    public void clickFollowButton() {
        driver.findElement(followButton).click();
    }

    public Boolean isFollow() {
        return driver.findElements(followButton).isEmpty();
    }

    public void clickUnFollow() {
        driver.findElement(unFollowButton).click();
    }

    public void clickReaction() {
        driver.findElement(reaction).click();
    }

    public Integer getReactionCount() {
        return Integer.parseInt(driver.findElement(reactionCount).getText());
    }

    public void writeComment(String comment) {
        WebElement element = driver.findElement(commentInput);
        element.clear();
        element.sendKeys(comment);
    }

    public void clickSendComment() {
        driver.findElement(commentSendButton).click();
    }

    public Boolean isFirstCommentHasText() {
        if (driver.findElements(firstComment).isEmpty()) {
            return false;
        } else {
            return !driver.findElement(firstComment).getText().isEmpty();
        }
    }

    public String getFirstCommentText() {
        return driver.findElement(firstComment).getText();
    }


    public void clickDeleteComment() {
        driver.findElement(threeDots).click();
        WebElement element = this.wait.until(
                ExpectedConditions.elementToBeClickable(deleteButton));
        element.click();

        WebElement confirmElement = this.wait.until(
                ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmElement.click();
    }
}
