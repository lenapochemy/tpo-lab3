package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends Page {

    private final By manageLink = By.xpath("//div[@class='hyv-passport-info-tile__title-container']//div[contains(text(),'Личные данные')]/following-sibling::a");
    private final By deleteLink = By.xpath("//div[@class='hyv-passport-info-tile__title-container']//div[contains(text(),'Удалить учётную запись')]/following-sibling::a");

    private final By sendCodeButton = By.xpath("//div[@class='el-input-group__append']//a");
    private final By codeInput = By.xpath("//div[@class='hyv-cmn-input']//div//input");
    private final By disabledSubmit = By.xpath("//button[@type='submit' and @disabled]");
    private final By notDisabledSubmit = By.xpath("//button[@type='submit' and not (@disabled)]");

    private final By confirmButton = By.xpath("//form//button[@type='submit']");


    public AccountPage(WebDriver driver) {
        super(driver);
//        driver.switchTo().frame("hyv-account-frame");
        WebElement element = this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(manageLink));

    }

    public void deleteAccount() throws InterruptedException {
        clickManageButton();
        clickDeleteButton();

        driver.switchTo().frame("hyv-account-frame");
        clickSendCodeButton();
        Thread.sleep(30000);

        clickSubmit();

    }


    public void clickManageButton() {
        WebElement element = driver.findElement(manageLink);
        element.click();
    }

    public void clickDeleteButton() {
        WebElement deleteElement = this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(deleteLink));
        deleteElement.click();
    }

    public void clickSendCodeButton() {
        driver.findElement(sendCodeButton).click();
    }

    public void clickSubmit() {
        WebElement submitElement = this.wait.until(driver ->
                driver.findElement(notDisabledSubmit)
        );

        WebElement button = driver.findElement(notDisabledSubmit);
        button.click();

    }


}
