package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Page{

    By loginInput = By.xpath("//input[@name='username']");
    By passwordInput = By.xpath("//input[@name='password']");
    By loginError = By.xpath("//div[@name='username']//div[@class='err-text hyv-font-caption1 text-left mt-p4' and string-length(normalize-space(text())) > 0]");
    By passwordError = By.xpath("//div[@name='password']//div[@class='err-text hyv-font-caption1 text-left mt-p4' and string-length(normalize-space(text())) > 0]");
    By notDisabledSubmitButton = By.xpath("//form//button[@type='submit' and not (@disabled)]");
    By disabledSubmitButton = By.xpath("//form//button[@type='submit' and @disabled]");

    By registrationLink = By.xpath("//a[@class='el-link el-link--primary cmn-link mt-p16 hyv-text-main']");
    public LoginPage(WebDriver driver) {
        super(driver);
        driver.switchTo().frame("hyv-account-frame");
        WebElement element = this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginInput));
    }


    public boolean isLoginError(){
        return !driver.findElements(loginError).isEmpty();
    }

    public boolean isPasswordError(){
        return !driver.findElements(passwordError).isEmpty();
    }

    public void printLogin( String login){
        WebElement loginInputElement = driver.findElement(loginInput);
        loginInputElement.sendKeys(login);
    }

    public void printPassword(String password){
        WebElement passwordInputElement = driver.findElement(passwordInput);
        passwordInputElement.sendKeys(password);
    }

    public Boolean isDisabledSubmitButton(){
        return !driver.findElements(disabledSubmitButton).isEmpty();
    }
    public void clickLogin(){
        driver.findElement(loginInput).click();
    }

    public void clickPassword(){
        driver.findElement(passwordInput).click();
    }

    public void clickSubmitButton(){
        driver.findElement(notDisabledSubmitButton).click();
    }

    public RegistrationPage goToRegistrationPage(){
        WebElement element = driver.findElement(registrationLink);
        element.click();
        return new RegistrationPage(driver);
    }
}
