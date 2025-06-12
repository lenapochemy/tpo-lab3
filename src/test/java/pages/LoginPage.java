package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage extends Page {

    By loginInput = By.xpath("//input[@name='username']");
    By passwordInput = By.xpath("//input[@name='password']");
    By loginError = By.xpath("//div[@name='username']//div[@class='err-text hyv-font-caption1 text-left mt-p4' and string-length(normalize-space(text())) > 0]");
    By notLoginError = By.xpath("//div[@name='username']//div[@class='err-text hyv-font-caption1 text-left mt-p4' and string-length(normalize-space(text())) = 0]");
    By passwordError = By.xpath("//div[@name='password']//div[@class='err-text hyv-font-caption1 text-left mt-p4' and string-length(normalize-space(text())) > 0]");
    By notPasswordError = By.xpath("//div[@name='password']//div[@class='err-text hyv-font-caption1 text-left mt-p4' and string-length(normalize-space(text())) = 0]");

    By notDisabledSubmitButton = By.xpath("//form//button[@type='submit' and not (@disabled)]");
    By disabledSubmitButton = By.xpath("//form//button[@type='submit' and @disabled]");

    By registrationLink = By.xpath("//a[@class='el-link el-link--primary cmn-link mt-p16 hyv-text-main']");
    By loading = By.xpath("//div[contains(@class, 'loading')]");

    public LoginPage(WebDriver driver) {
        super(driver);
        Duration timeout = implicitTo0();
        driver.switchTo().frame("hyv-account-frame");
        implicitToTimeout(timeout);
        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginInput));
    }


    public boolean isLoginError() {
        return !immediateFindElements(loginError, 2).isEmpty();
    }

    public boolean notLoginError() {
        return !immediateFindElements(notLoginError, 2).isEmpty();
    }

    public boolean isPasswordError() {
        return !immediateFindElements(passwordError, 2).isEmpty();
    }

    public boolean notPasswordError() {
        return !immediateFindElements(notPasswordError, 2).isEmpty();
    }

    public void printLogin(String login) {
        WebElement loginInputElement = driver.findElement(loginInput);
        loginInputElement.sendKeys(login);
    }

    public void printPassword(String password) {
        WebElement passwordInputElement = driver.findElement(passwordInput);
        passwordInputElement.sendKeys(password);
    }

    public Boolean isDisabledSubmitButton() {
        return !immediateFindElements(disabledSubmitButton).isEmpty();
    }

    public void clickLogin() {
        customWait(5).until(ExpectedConditions.invisibilityOfElementLocated(loading));
        driver.findElement(loginInput).click();
    }

    public void clickPassword() {
        driver.findElement(passwordInput).click();
    }

    public void clickSubmitButton() {
        driver.findElement(notDisabledSubmitButton).click();
    }

    public RegistrationPage goToRegistrationPage() {
        WebElement element = driver.findElement(registrationLink);
        element.click();
        return new RegistrationPage(driver);
    }
}
