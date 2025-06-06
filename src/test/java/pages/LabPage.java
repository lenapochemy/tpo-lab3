package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LabPage extends Page {

    private final By checkDiv = By.xpath("//div[@class='mhy-switch-tab']//ul[count(li)=5]");
    private final By skipButton = By.xpath("//div[@class='mhy-dialog__body']//div//span/following-sibling::button");
    private final By interestDialog = By.xpath("//div[@class='mhy-dialog__body']");
    private final By loginButton = By.xpath("//div[@class='login-box-side_bottom']//div");
    private final By loginBox = By.xpath("//div[@data-impression-type='loginReminder']");
    private final By frame = By.xpath("//iframe");
    private final By firstNew = By.xpath("//div[@class='mhy-article-list']//div[@data-index=0]");
    private final By firstNewTitle = By.xpath("//div[@class='mhy-article-list']//div[@data-index=0]//a//div//div//h3//span");
   private final By postCreateButton = By.xpath("//div[@class='header-item header-post']");
    private final By textPost = By.xpath("//button[@title='Опубликовать пост']");
    private final By justNowPost = By.xpath("//p[@class='mhy-article-card__info' and contains(text(), 'Только что')]");
    private final By avatar = By.xpath("//div[@class='header-avatar']");
    private final By logout = By.xpath("//ul//li//div[span[contains(text(), 'Выйти')]]");
    private final By confirmLogout = By.xpath("//footer//button[span[contains(text(), 'Выйти')]]");
    private final By notificationButton = By.xpath("//div[@role='button' and @class='header-item__button' and div[i[contains(@class, 'notification')]]]");
    private final By moreButton = By.xpath("//div[@class='header-notification-dialog__footer']//a");
    private final By followNotificationButton = By.xpath("//div[@class='root-page-container']//ul//li//div[@aria-label='Подписки']");
    private final By systemNotificationButton = By.xpath("//div[@data-impression-collections and div[div[span[contains(text(), 'Система')]]]]");




    public LabPage(WebDriver driver) {
        super(driver);
        WebElement element = this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(checkDiv));
    }

    public void skipInterestDialog() {
        if (isExistInterestDialog()) {
            driver.findElement(skipButton).click();
        }
    }

    public Boolean isExistInterestDialog() {
        return !driver.findElements(interestDialog).isEmpty();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public Boolean isLogin() {
        return driver.findElements(loginBox).isEmpty();
    }


    public LoginPage goToLoginPage() {
        if (driver.findElements(frame).isEmpty()) {
            skipInterestDialog();
            WebElement element = driver.findElement(loginButton);
            element.click();
        }
        return new LoginPage(driver);
    }

    public RegistrationPage goToRegPage() {
        if (driver.findElements(frame).isEmpty()) {
            skipInterestDialog();
            WebElement element = driver.findElement(loginButton);
            element.click();
        }
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.goToRegistrationPage();
    }

    public ArticlePage readNew() {
        WebElement firstNews = driver.findElement(firstNew);
        firstNews.click();
        return new ArticlePage(driver);
    }

    public String getFirstNewTitle() {
        WebElement element = driver.findElement(firstNewTitle);
        return element.getText();
    }


    public NewArticlePage writeNewPost(){
        driver.findElement(postCreateButton).click();
        driver.findElement(textPost).click();
        return new NewArticlePage(driver);
    }

    public boolean isExistsJustNowPost(){
        return !driver.findElements(justNowPost).isEmpty();
    }

    public UserPage toUserPage(String userId){
        driver.get("https://www.hoyolab.com/accountCenter/postList?id=" + userId);
        return new UserPage(driver);
    }


    public void logout() throws InterruptedException{
        driver.findElement(avatar).click();
        Thread.sleep(3000);
        driver.findElement(logout).click();
        Thread.sleep(3000);
        driver.findElement(confirmLogout).click();
        Thread.sleep(3000);
    }


    public void clickFollowNotifications(){
        WebElement elementNotif = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationButton));
        elementNotif.click();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(moreButton));
        element.click();

        driver.findElement(followNotificationButton).click();
    }

    public Boolean checkFollowNotification(String username){
        String xpath = "//div[@data-impression-type='Message']//a//span[contains(text(), '%s')]";
        By followNotification = By.xpath(String.format(xpath, username));
        return !driver.findElements(followNotification).isEmpty();

    }

    public void clickSystemNotifications(){
        driver.findElement(notificationButton).click();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(moreButton));
        element.click();

        driver.findElement(systemNotificationButton).click();
    }

    public Boolean checkNewPostNotification(String authorUsername){
        String xpath = "//div[@data-impression-type='Message']//div[@class='mhy-noticeInfo-item__body__content' and contains(text(), 'Автор %s опубликовал новый пост «Классно»')]";
        By followNotification = By.xpath(String.format(xpath, authorUsername));
        return !driver.findElements(followNotification).isEmpty();

    }


}
