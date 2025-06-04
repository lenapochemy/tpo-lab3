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
    private final By articleTitle = By.xpath("//div[@class='mhy-article-page__title']//h1");
    private final By followButton = By.xpath("//div[@class='mhy-user-card__follow']//div[span[contains(text(), 'Подписаться')]]");
    private final By unFollowButton = By.xpath("//div[@class='mhy-user-card__follow']//div[span[contains(text(), 'Подписаны')]]");
    private final By reaction = By.xpath("//div[@class='multi-like-bar']/div[1]");
    private final By reactionCount = By.xpath("//div[@class='multi-like-bar']/div[1]//span");

    //    private final By avatarDialog = By.xpath("//div[@class='mhy-init-user-dialog-header']//img");
//    private final By commentInput = By.xpath("//div[@class='ql-editor']//p");
    private final By commentInput = By.xpath("//div[@data-placeholder='Поспешите оставить комментарий!']//p");
    private final By commentSendButton = By.xpath("//div[@class='mhy-reply-box__footer']//div//button");
    private final By firstComment = By.xpath("//div[@data-index=0]//pre//p");
    private final By threeDots = By.xpath("//div[@data-index=0]//div[@class='mhy-action']//div[@role='button']");
    private final By deleteButton = By.xpath("//ul//li[span[contains(text(), 'Удалить')]]");
    private final By confirmDeleteButton = By.xpath("//footer//button[span[contains(text(), 'Удалить')]]");
//    private final By authorName = By.xpath("//div[@class='mhy-page-header-mask']//a//span[@class='mhy-account-title__name']");
//    private final By authorPageButton = By.xpath("//div[@class='mhy-page-header-mask']//a[span[@class='mhy-account-title__name']]");

    private final By postCreateButton = By.xpath("//div[@class='header-item header-post']");
    private final By textPost = By.xpath("//button[@title='Опубликовать пост']");
    private final By postTitleInput = By.xpath("//div[@class='mhy-input title-text']//div//input");
    private final By postInput = By.xpath("//div[@class='ql-container ql-snow']//p");
    private final By groupAndCategorySelectList = By.xpath("//div[contains(@class,'mhy-select mhy-select-outlined')]");
    private final By genshinGroupSelectButton = By.xpath("//div[@title='Genshin Impact' and contains(text(), 'Genshin Impact')]");
    private final By groupAndCategory = By.xpath("//div[contains(@class,'mhy-select mhy-select-outlined')]//div//span//span[@class='mhy-select__val']//span");
    private final By discussionCategoryButton = By.xpath("//div[@title='Обсуждения']");
//    private final By copyright = By.xpath("//div[@class='mhy-switch']");
    private final By originalWorkOff = By.xpath("//div[@class='copyright-settings-original__original-settings']//div[@class='mhy-switch']");
    private final By originalWorkOn = By.xpath("//div[@class='copyright-settings-original__original-settings']//div[@class='mhy-switch mhy-switch--active']");
    private final By repostOff = By.xpath("//div[@class='copyright-settings-original__repost-settings']//div[@class='mhy-switch']");
    private final By repostOn = By.xpath("//div[@class='copyright-settings-original__repost-settings']//div[@class='mhy-switch mhy-switch--active']");
    private final By postSendButton = By.xpath("//button[span[contains(text(), 'Отправить')]]");
    private final By myPostTitle = By.xpath("//div[@class='mhy-article-page__title']//h1");
    private final By myPost = By.xpath("//div[@class='mhy-article-page__content']//p");
    private final By myPostThreeDots = By.xpath("//div[@class='mhy-action']");
    private final By myPostsCount = By.xpath("//span[following-sibling::span[contains(text(), 'Посты')]]");

    private final By searchInput = By.xpath("//input");
    private final By searchButton = By.xpath("//div[@class='mhy-search-bar__input']//i[@role='button']");
    private final By usersSearchCategory = By.xpath("//a[span[contains(text(), 'Пользователи')]]");
    private final By firstResultName = By.xpath("//div[@data-index=0]//span[@class='highlight']");
    private final By firstResult = By.xpath("//div[@data-index=0]");


    private final By avatar = By.xpath("//div[@class='header-avatar']");
    private final By logout = By.xpath("//ul//li//div[span[contains(text(), 'Выйти')]]");
    private final By confirmLogout = By.xpath("//footer//button[span[contains(text(), 'Выйти')]]");




    public LabPage(WebDriver driver) {
        super(driver);
//        driver.get(link);
        WebElement element = this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(checkDiv));

//        skipInterestDialog();
    }

    public static LabPage init(WebDriver driver) {
        driver.get("https://www.hoyolab.com/circles/6/39/official?page_type=official&page_sort=news&utm_source=officialweb&utm_medium=toptoolbar&utm_id=6");
        return new LabPage(driver);
    }

    public void skipInterestDialog() {
        if (isExistInterestDialog()) {
            driver.findElement(skipButton).click();
        }
    }

    public Boolean isExistInterestDialog() {
        return !driver.findElements(interestDialog).isEmpty();
    }

    //    public Boolean isExistAvatarDialog() {
//        return !driver.findElements(avatarDialog).isEmpty();
//    }
//
//    public void skipAvatarDialog(){
//        if(isExistAvatarDialog()){
//            driver.findElement(avatarDialog).click();
//        }
//    }
    public String getTitle() {
        return driver.getTitle();
    }

    public Boolean isLogin() {
        return driver.findElements(loginBox).isEmpty();
    }

//    public void clickLoginButton(){
//        driver.findElement(loginButton).click();
//    }

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

    public void readNew() {
        WebElement firstNews = driver.findElement(firstNew);
        firstNews.click();
    }

    public String getFirstNewTitle() {
        WebElement element = driver.findElement(firstNewTitle);
        return element.getText();
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

//    public void clickThreeDots(){
//        driver.findElement(threeDots).click();
//    }

    public void clickDeleteComment() {
        driver.findElement(threeDots).click();
        WebElement element = this.wait.until(
                ExpectedConditions.elementToBeClickable(deleteButton));
        element.click();

        WebElement confirmElement = this.wait.until(
                ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmElement.click();
//        driver.findElement(deleteCommentButton).click();
    }


    public void clickPostCreateButton() {
        driver.findElement(postCreateButton).click();
    }

    public void clickTextPostCreate() {
        driver.findElement(textPost).click();
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

    public void chooseGroup(){
        driver.findElement(groupAndCategorySelectList).click();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(genshinGroupSelectButton));
        element.click();
    }

    public String getGroupName(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(groupAndCategory));
        return element.getText();
    }

    public void chooseCategory(){
        driver.findElements(groupAndCategorySelectList).get(1).click();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(discussionCategoryButton));
        element.click();
    }

    public String getCategoryName(){
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(groupAndCategory));
        WebElement element = driver.findElements(groupAndCategory).get(1);
        return element.getText();
    }

    public Boolean isOriginalWork(){
        return driver.findElements(originalWorkOff).isEmpty() && !driver.findElements(originalWorkOn).isEmpty();
    }

    public Boolean isRepostRight(){
        return driver.findElements(repostOff).isEmpty() && !driver.findElements(repostOn).isEmpty();
    }

    public void clickOriginalWork(){
        if(isOriginalWork()){
            driver.findElement(originalWorkOn).click();
        } else{
            driver.findElement(originalWorkOff).click();
        }
    }

    public void clickReportRight(){
        if(isRepostRight()){
            driver.findElement(repostOn).click();
        } else{
            driver.findElement(repostOff).click();
        }
    }

    public void clickPostSend(){
        driver.findElement(postSendButton).click();
    }

    public String getMyPostTitle(){
        return driver.findElement(myPostTitle).getText();
    }

    public String getMyPost(){
        return driver.findElement(myPost).getText();
    }

    public void clickDeletePost(){
        driver.findElement(myPostThreeDots).click();
        driver.findElement(deleteButton).click();
        WebElement confirmElement = this.wait.until(
                ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmElement.click();
    }

    public Integer getMyPostsCount(){
        return Integer.parseInt(driver.findElement(myPostsCount).getText());
    }


    public void search(String search){
        WebElement element = driver.findElement(searchInput);
        element.clear();
        element.sendKeys(search);

        driver.findElement(searchButton).click();
        WebElement usersCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(usersSearchCategory));
        usersCategory.click();
    }

    public String getFirstResultName(){
        return driver.findElement(firstResultName).getText();
    }

    public UserPage toFirstResultPage(){
        String currentWindow = driver.getWindowHandle();
        driver.findElement(firstResult).click();
        this.moveToNewTab(currentWindow);
        return new UserPage(driver);
    }


    public void logout(){
        driver.findElement(avatar).click();
        driver.findElement(logout).click();
        driver.findElement(confirmLogout).click();
    }

//    public String getAuthorName(){
//        return driver.findElement(authorName).getText();
//    }
//
//    public void clickAuthorPageButton() {
//        driver.findElement(authorPageButton).click();
//    }


}
