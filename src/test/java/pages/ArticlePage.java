package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ArticlePage extends Page {
    private final By articleContent = By.xpath("//div[@class='mhy-article-page__content']");
    private final By articleTitle = By.xpath("//div[@class='mhy-article-page__title']//h1");
    private final By followButton = By.xpath("//div[@class='mhy-user-card__follow']//div[span[contains(text(), 'Подписаться')]]");
    private final By unFollowButton = By.xpath("//div[@class='mhy-user-card__follow']//div[span[contains(text(), 'Подписаны')]]");
    private final By reaction = By.xpath("//div[@class='multi-like-bar']//div[@upvoteid=1]");
    private final By clickedReaction = By.xpath("//div[@class='multi-like-bar']//div[@upvoteid=1 and contains(@class, 'active')]");
    private final By notClickedReaction = By.xpath("//div[@class='multi-like-bar']//div[@upvoteid=1 and not (contains(@class, 'active'))]");
    private final By commentInput = By.xpath("//div[@data-placeholder='Поспешите оставить комментарий!']//p");
    private final By commentSendButton = By.xpath("//div[@class='mhy-reply-box__footer']//div//button");
//    private final By firstComment = By.xpath("//div[@data-index=0]//pre//p");
    private final By threeDots = By.xpath("//div[@data-index=0]//div[@class='mhy-action']//div[@role='button']");
    private final By deleteButton = By.xpath("//ul//li[span[contains(text(), 'Удалить')]]");
    private final By confirmDeleteButton = By.xpath("//footer//button[span[contains(text(), 'Удалить')]]");
    private final By toast = By.xpath("//div[@class='mhy-toast']");

    public ArticlePage(WebDriver driver) {
        super(driver);
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(articleTitle));
    }


    public String getArticleTitle() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(articleTitle));
        return element.getText();
    }

    public void clickFollowButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(followButton));
        element.click();
    }

    public Boolean isFollow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(articleContent));
        return immediateFindElements(unFollowButton, 5).size() == 1 && immediateFindElements(followButton, 5).isEmpty();
    }

    public void clickUnFollow() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(unFollowButton));
        element.click();
    }

    public void clickReaction() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(reaction));
        element.click();
    }

    public boolean isExistClickedReaction(){
        try {
            customWait(2).until(ExpectedConditions.visibilityOfElementLocated(clickedReaction));
            customWait(2).until(ExpectedConditions.invisibilityOfElementLocated(notClickedReaction));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void writeComment(String comment) {
        WebElement element = driver.findElement(commentInput);
        element.clear();
        element.sendKeys(comment);
    }

    public void clickSendComment() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(commentSendButton));
        element.click();
    }

    public boolean isFirstCommentAuthor(String nickname){
        String xpath = "//span[contains(text(), '%s')]";
        By commentAuthor = By.xpath(String.format(xpath, nickname));
        return !immediateFindElements(commentAuthor, 3).isEmpty();
    }

//    public Boolean isFirstCommentHasText() {
//        if (immediateFindElements(firstComment, 3).isEmpty()) {
//            return false;
//        } else {
//            return !driver.findElement(firstComment).getText().isEmpty();
//        }
//    }
////
//    public boolean isFirstCommentTextEquals(String comment) {
//
//        try {
//            customWait(5).until(ExpectedConditions.textToBePresentInElementLocated(firstComment, comment));
//        } catch (TimeoutException e) {
//            return false;
//        }
//        return true;
//    }


    public void clickDeleteComment() {
        driver.findElement(threeDots).click();
        WebElement element = this.wait.until(
                ExpectedConditions.elementToBeClickable(deleteButton));
        element.click();

        WebElement confirmElement = this.wait.until(
        ExpectedConditions.elementToBeClickable(confirmDeleteButton));

        wait.until(driver1 -> immediateFindElements(toast).isEmpty());

        confirmElement.click();
    }
}
