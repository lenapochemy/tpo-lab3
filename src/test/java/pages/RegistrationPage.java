package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends Page {

    private final By emailInput = By.xpath("//div[@maxlength=50 and @autocomplete='off']//div//input");
    private final By codeInput = By.xpath("//div[@maxlength=6 and @autocomplete='off']//div//input");
    private final By codeSendButton = By.xpath("//div[@class='el-input-group__append']//a[@class]");
    private final By passwordInput = By.xpath("//div[@class='hyv-cmn-input']//div//input[contains(@name, 'password')]");
    private final By passwordAgainInput = By.xpath("//div[@class='hyv-cmn-input']//div//input[contains(@name, 'confirmPassword')]");
    private final By emailError = By.xpath("//div[@class='hyv-cmn-input' and @maxlength=50]//div[@class='err-text hyv-font-caption1 text-left mt-p4']");
    private final By passwordError = By.xpath("//div[@class='hyv-cmn-input' and @maxlength=30]//label[contains(text(), 'Введите пароль')]/following-sibling::div[@class='err-text hyv-font-caption1 text-left mt-p4']");
    private final By passwordAgainError = By.xpath("//div[@class='hyv-cmn-input' and @maxlength=30]//label[contains(text(), 'введите пароль ещё раз')]/following-sibling::div[@class='err-text hyv-font-caption1 text-left mt-p4']");
    private final By userAgreement = By.xpath("//label[@class='el-checkbox el-checkbox--large cmn-checkbox text-left mr-p12 mb-p8 agreement_privacyAndUser']");
    private final By checkedUserAgreement = By.xpath("//label[@class='el-checkbox el-checkbox--large is-checked cmn-checkbox text-left mr-p12 mb-p8 agreement_privacyAndUser']");
    private final By marketingAgreement = By.xpath("//label[@class='el-checkbox el-checkbox--large cmn-checkbox text-left mr-p12 mb-p8 agreement_marketing']");
    private final By checkedMarketingAgreement = By.xpath("//label[@class='el-checkbox el-checkbox--large is-checked cmn-checkbox text-left mr-p12 mb-p8 agreement_marketing']");
    private final By disabledRegistrationSubmit = By.xpath("//button[@type='submit' and @disabled]");
    private final By notDisabledRegistrationSubmit = By.xpath("//button[@type='submit' and not (@disabled)]");

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailInput));
    }


    public Boolean isSubmitDisabled() {
        return !immediateFindElements(disabledRegistrationSubmit).isEmpty();
    }

    public void clickSubmit() {
        WebElement element = driver.findElement(notDisabledRegistrationSubmit);
        element.click();
    }

    public void clickEmailInout() {
        WebElement element = driver.findElement(emailInput);
        element.click();
    }

    public void clickCodeInput() {
        WebElement element = driver.findElement(codeInput);
        element.click();
    }

    public void clickPassword() {
        WebElement element = driver.findElement(passwordInput);
        element.click();
    }

    public void clickPasswordAgain() {
        WebElement element = driver.findElement(passwordAgainInput);
        element.click();
    }

    public void clickCodeSendButton() {
        WebElement element = driver.findElement(codeSendButton);
        element.click();
    }

    public Boolean isCheckedUserAgreement() {
        return immediateFindElements(userAgreement, 3).isEmpty();
    }

    public Boolean isCheckedMarketingAgreement() {
        return immediateFindElements(marketingAgreement, 3).isEmpty();
    }

    public void clickUserAgreement() {
        WebElement element;
        if (isCheckedUserAgreement()) {
            element = driver.findElement(checkedUserAgreement);
        } else {
            element = driver.findElement(userAgreement);
        }
        element.click();
    }

    public void clickMarketingAgreement() {
        WebElement element;
        if (isCheckedMarketingAgreement()) {
            element = driver.findElement(checkedMarketingAgreement);
        } else {
            element = driver.findElement(marketingAgreement);
        }
        element.click();
    }


    public Boolean isEmailError() {
        return findNotEmptyText(emailError, 3);
    }

    public Boolean isPasswordError() {
        return findNotEmptyText(passwordError, 3);
    }

    public Boolean isPasswordAgainError() {
        return findNotEmptyText(passwordAgainError, 3);
    }

    public boolean isEmailErrorEquals(String error) {
        try {
            customWait(3).until(ExpectedConditions.textToBePresentInElementLocated(emailError, error));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isPasswordErrorEquals(String error) {
        try {
            customWait(3).until(ExpectedConditions.textToBePresentInElementLocated(passwordError, error));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isPasswordAgainErrorEquals(String error) {
        try {
            customWait(3).until(ExpectedConditions.textToBePresentInElementLocated(passwordAgainError, error));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void writeEmail(String email) {
        WebElement element = driver.findElement(emailInput);
        element.clear();
        element.sendKeys(email);
    }

    public void writePassword(String password) {
        WebElement element = driver.findElement(passwordInput);
        element.clear();
        element.sendKeys(password);
    }

    public void writePasswordAgain(String password) {
        WebElement element = driver.findElement(passwordAgainInput);
        element.clear();
        element.sendKeys(password);
    }

    public void waitForCode() {
        customWait(30).until(driver1 ->
                driver.findElement(codeInput).getAttribute("value").length() == 6);
    }
}
