package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewArticlePage extends Page{

    private final By postTitleInput = By.xpath("//div[@class='mhy-input title-text']//div//input");
    private final By postInput = By.xpath("//div[@class='ql-container ql-snow']//p");
    private final By groupAndCategorySelectList = By.xpath("//div[contains(@class,'mhy-select mhy-select-outlined')]");
    private final By genshinGroupSelectButton = By.xpath("//div[@title='Genshin Impact' and contains(text(), 'Genshin Impact')]");
    private final By groupAndCategory = By.xpath("//div[contains(@class,'mhy-select mhy-select-outlined')]//div//span//span[@class='mhy-select__val']//span");
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
        WebElement element = this.wait.until(
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

}
