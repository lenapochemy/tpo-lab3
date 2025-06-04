package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

public class RegistrationPage extends Page{

    private final By emailInput = By.xpath("//div[@maxlength=50 and @autocomplete='off']//div//input");
    private final By codeInput = By.xpath("//div[@maxlength=6 and @autocomplete='off']//div//input");
    private final By codeSendButton = By.xpath("//div[@class='el-input-group__append']//a[@class]");
    private final By passwordInput = By.xpath("//div[@class='hyv-cmn-input']//div//input[contains(@name, 'password')]");
    private final By passwordAgainInput = By.xpath("//div[@class='hyv-cmn-input']//div//input[contains(@name, 'confirmPassword')]");

    private final By emailError = By.xpath("//div[@class='hyv-cmn-input' and @maxlength=50]//div[@class='err-text hyv-font-caption1 text-left mt-p4']");
//    private final By codeError = By.xpath("//div[@class='hyv-cmn-input' and @maxlength=6]//div[@class='err-text hyv-font-caption1 text-left mt-p4']");
    private final By passwordError = By.xpath("//div[@class='hyv-cmn-input' and @maxlength=30]//label[contains(text(), 'Введите пароль')]/following-sibling::div[@class='err-text hyv-font-caption1 text-left mt-p4']");
    private final By passwordAgainError = By.xpath("//div[@class='hyv-cmn-input' and @maxlength=30]//label[contains(text(), 'введите пароль ещё раз')]/following-sibling::div[@class='err-text hyv-font-caption1 text-left mt-p4']");


    private final By userAgreement = By.xpath("//label[@class='el-checkbox el-checkbox--large cmn-checkbox text-left mr-p12 mb-p8 agreement_privacyAndUser']");
    private final By checkedUserAgreement = By.xpath("//label[@class='el-checkbox el-checkbox--large is-checked cmn-checkbox text-left mr-p12 mb-p8 agreement_privacyAndUser']");
    private final By marketingAgreement = By.xpath("//label[@class='el-checkbox el-checkbox--large cmn-checkbox text-left mr-p12 mb-p8 agreement_marketing']");
    private final By checkedMarketingAgreement = By.xpath("//label[@class='el-checkbox el-checkbox--large is-checked cmn-checkbox text-left mr-p12 mb-p8 agreement_marketing']");

    private final By disabledRegistrationSubmit = By.xpath("//button[@type='submit' and @disabled]");
    private final By notDisabledRegistrationSubmit = By.xpath("//button[@type='submit' and not (@disabled)]");

    private final By emailForCodeError = By.xpath("//div[@class='van-popup van-popup--center van-toast van-toast--middle van-toast--text']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
//        this.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("hyv-account-frame"));
//        driver.switchTo().frame("hyv-account-frame");
        WebElement element = this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailInput));

    }


    public Boolean isSubmitDisabled(){
        return !driver.findElements(disabledRegistrationSubmit).isEmpty();
    }

    public void clickSubmit(){
        WebElement element = driver.findElement(notDisabledRegistrationSubmit);
        element.click();
    }

    public void clickEmailInout(){
        WebElement element = driver.findElement(emailInput);
        element.click();
    }

    public void clickCodeInput(){
        WebElement element = driver.findElement(codeInput);
        element.click();
    }

    public void clickPassword(){
        WebElement element = driver.findElement(passwordInput);
        element.click();
    }

    public void clickPasswordAgain(){
        WebElement element = driver.findElement(passwordAgainInput);
        element.click();
    }

    public void clickCodeSendButton(){
        WebElement element = driver.findElement(codeSendButton);
        element.click();
    }

    public Boolean isCheckedUserAgreement(){
        return driver.findElements(userAgreement).isEmpty();
    }

    public Boolean isCheckedMarketingAgreement(){
        return driver.findElements(marketingAgreement).isEmpty();
    }

    public void clickUserAgreement(){
        WebElement element;
        if(isCheckedUserAgreement()){
            element = driver.findElement(checkedUserAgreement);
        } else {
            element = driver.findElement(userAgreement);
        }
        element.click();
    }

    public void clickMarketingAgreement(){
        WebElement element;
        if(isCheckedMarketingAgreement()){
            element = driver.findElement(checkedMarketingAgreement);
        } else {
            element = driver.findElement(marketingAgreement);
        }
        element.click();
    }


    public Boolean isEmailError(){
        WebElement element = driver.findElement(emailError);
        return !element.getText().isEmpty();
    }

    public Boolean isPasswordError(){
        WebElement element = driver.findElement(passwordError);
        return !element.getText().isEmpty();
    }

    public Boolean isPasswordAgainError(){
        WebElement element = driver.findElement(passwordAgainError);
        return !element.getText().isEmpty();
    }

    public String getEmailError(){
        return driver.findElement(emailError).getText();
    }

    public String getPasswordError(){
        return driver.findElement(passwordError).getText();
    }

    public String getPasswordAgainError(){
        return driver.findElement(passwordAgainError).getText();
    }

    public void writeEmail(String email){
        WebElement element = driver.findElement(emailInput);
        element.clear();
        element.sendKeys(email);
    }

//    public void writeCode(String code){
//        driver.findElement(codeInput).sendKeys(code);
//    }

    public void writePassword(String password){
        WebElement element = driver.findElement(passwordInput);
        element.clear();
        element.sendKeys(password);
    }

    public void writePasswordAgain(String password){
        WebElement element = driver.findElement(passwordAgainInput);
        element.clear();
        element.sendKeys(password);
    }

    public Boolean isEmailForCodeError(){
        WebElement element = driver.findElement(emailForCodeError);
        return Objects.requireNonNull(element.getAttribute("style")).contains("display: none;");
    }

    public void waitForCode(){
        WebElement element = this.wait.until(driver ->{
                    return driver.findElement(notDisabledRegistrationSubmit);
                }
                );
    }
}
