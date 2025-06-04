import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class HoyoTest {


    public static void titleTest(WebDriver driver) {
        HomePage homePage = HomePage.init(driver);
        assertEquals("Главная | Официальный сайт Honkai: Star Rail | Пусть это путешествие приведёт нас к звёздам", homePage.getTitle());
    }

    public static void loginTest(WebDriver driver) {
        HomePage homePage = HomePage.init(driver);
        assertFalse(homePage.isLogin());
        LoginPage loginPage = homePage.goToLoginPage();

        login(loginPage);
        driver.switchTo().defaultContent();
        assertTrue(homePage.isLogin());
    }

    private static void login(LoginPage loginPage){
        assertTrue(loginPage.isDisabledSubmitButton());


        loginPage.clickLogin();
        loginPage.clickPassword();
        assertTrue(loginPage.isLoginError());
        loginPage.printLogin("");
        loginPage.clickPassword();
        assertTrue(loginPage.isLoginError());
        assertTrue(loginPage.isDisabledSubmitButton());

        loginPage.printLogin("redmimimirise@mail.ru");
        loginPage.clickPassword();
//        assertFalse(loginPage.isLoginError(driver));
        assertTrue(loginPage.isPasswordError());
        assertTrue(loginPage.isDisabledSubmitButton());
//        assertFalse(loginPage.isLoginError(driver));

        loginPage.clickPassword();
        loginPage.clickLogin();
        assertTrue(loginPage.isPasswordError());
        loginPage.printPassword("");
        loginPage.clickLogin();
        assertTrue(loginPage.isPasswordError());
        assertTrue(loginPage.isDisabledSubmitButton());

        loginPage.printPassword("qwerty1234");
        loginPage.clickLogin();
//        assertFalse(loginPage.isPasswordError(driver));
        assertFalse(loginPage.isDisabledSubmitButton());

        loginPage.clickSubmitButton();

    }


    private static void justLogin(LoginPage loginPage, String email){
        loginPage.printLogin(email);
        loginPage.printPassword("qwerty1234");
        assertFalse(loginPage.isDisabledSubmitButton());

        loginPage.clickSubmitButton();
    }


    public static void registrationTest(WebDriver driver, String email) throws InterruptedException{
        HomePage homePage = HomePage.init(driver);
        assertFalse(homePage.isLogin());
        LoginPage loginPage = homePage.goToLoginPage();
        RegistrationPage registrationPage = loginPage.goToRegistrationPage();

        registration(email, registrationPage);
        driver.switchTo().defaultContent();
        assertTrue(homePage.isLogin());
    }

    private static void registration(String email, RegistrationPage registrationPage) throws InterruptedException {
        assertTrue(registrationPage.isSubmitDisabled());

        registrationPage.clickEmailInout();
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertTrue(registrationPage.isEmailError());

        registrationPage.writeEmail("");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertTrue(registrationPage.isEmailError());
        assertEquals("Необходимо заполнить поле: Электронная почта", registrationPage.getEmailError());

        registrationPage.writeEmail("sssss");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertTrue(registrationPage.isEmailError());
        assertEquals("Неверный формат электронной почты", registrationPage.getEmailError());


//        assertFalse(registrationPage.isEmailForCodeError());
//        registrationPage.clickCodeSendButton();
//        assertTrue(registrationPage.isEmailForCodeError());
////wait
//        assertFalse(registrationPage.isEmailForCodeError());

        registrationPage.writeEmail(email);
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertFalse(registrationPage.isEmailError());

        registrationPage.clickCodeSendButton();
        Thread.sleep(30000);
        registrationPage.clickPassword();
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertTrue(registrationPage.isPasswordError());
        assertEquals("Необходимо заполнить поле: Пароль", registrationPage.getPasswordError());

        registrationPage.writePassword("");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertTrue(registrationPage.isPasswordError());
        assertEquals("Необходимо заполнить поле: Пароль", registrationPage.getPasswordError());

        registrationPage.writePassword("ddddd");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertTrue(registrationPage.isPasswordError());
        assertEquals("Пароль должен включать от 8 до 30 цифр, букв или символов", registrationPage.getPasswordError());

        registrationPage.writePassword("dddddddd");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertTrue(registrationPage.isPasswordError());
        assertEquals("Не менее 2 типов символов", registrationPage.getPasswordError());

        registrationPage.writePassword("qwerty1234");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertFalse(registrationPage.isPasswordError());


        registrationPage.clickPasswordAgain();
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertTrue(registrationPage.isPasswordAgainError());
        assertEquals("Необходимо заполнить поле: Подтвердите пароль", registrationPage.getPasswordAgainError());

        registrationPage.writePasswordAgain("");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertTrue(registrationPage.isPasswordAgainError());
        assertEquals("Необходимо заполнить поле: Подтвердите пароль", registrationPage.getPasswordAgainError());

        registrationPage.writePasswordAgain("ddddd");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertTrue(registrationPage.isPasswordAgainError());
        assertEquals("Пароли должны совпадать", registrationPage.getPasswordAgainError());

        registrationPage.writePasswordAgain("qwerty1234");
        registrationPage.clickCodeInput();
        assertFalse(registrationPage.isSubmitDisabled());
        Thread.sleep(1000);
        assertFalse(registrationPage.isPasswordAgainError());

        assertFalse(registrationPage.isCheckedUserAgreement());
        registrationPage.clickUserAgreement();
        assertTrue(registrationPage.isCheckedUserAgreement());
        registrationPage.clickUserAgreement();
        assertFalse(registrationPage.isCheckedUserAgreement());
        registrationPage.clickUserAgreement();

        assertFalse(registrationPage.isCheckedMarketingAgreement());
        registrationPage.clickMarketingAgreement();
        assertTrue(registrationPage.isCheckedMarketingAgreement());
        registrationPage.clickMarketingAgreement();
        assertFalse(registrationPage.isCheckedMarketingAgreement());

        registrationPage.waitForCode();
        registrationPage.clickSubmit();


    }

    public static void toLab(WebDriver driver) throws InterruptedException{
//        HomePage homePage = HomePage.init(driver);
//        LabPage labPage = homePage.goToLabPage();
//        LoginPage loginPage = labPage.goToLoginPage();
//
//        login(loginPage);

        LabPage labPage = LabPage.init(driver);

        assertEquals("HoYoLAB - Игровое сообщество", labPage.getTitle());
        assertFalse(labPage.isLogin());

        LoginPage loginPage = labPage.goToLoginPage();

        login(loginPage);

        driver.switchTo().defaultContent();
        Thread.sleep(3000);
        assertEquals("HoYoLAB - Игровое сообщество", labPage.getTitle());


        assertTrue(labPage.isLogin());

        labPage.skipInterestDialog();
        assertTrue(labPage.isExistInterestDialog());

        //follow unfollow

        String articleTitle = labPage.getFirstNewTitle();
        labPage.readNew();
        Thread.sleep(3000);
        assertEquals(articleTitle, labPage.getArticleTitle());

        assertFalse(labPage.isFollow());

        labPage.clickFollowButton();
//        labPage.skipAvatarDialog();
        Thread.sleep(1000);
        assertTrue(labPage.isFollow());
        labPage.clickUnFollow();
        Thread.sleep(1000);
        assertFalse(labPage.isFollow());


        Integer reactionCount = labPage.getReactionCount();

        labPage.clickReaction();
        Thread.sleep(1000);
        assertEquals(reactionCount + 1, labPage.getReactionCount());
        labPage.clickReaction();
        Thread.sleep(1000);
        assertEquals(reactionCount, labPage.getReactionCount());

        String comment = "Классно!";
        if(labPage.isFirstCommentHasText()){
            assertNotEquals(comment, labPage.getFirstCommentText());
        }
        labPage.writeComment(comment);
        labPage.clickSendComment();
        Thread.sleep(3000);
        assertEquals(comment, labPage.getFirstCommentText());

//        labPage.clickThreeDots();
        labPage.clickDeleteComment();
        Thread.sleep(3000);
        if(labPage.isFirstCommentHasText()){
            assertNotEquals(comment, labPage.getFirstCommentText());
        }


        //post

        labPage.clickPostCreateButton();
        labPage.clickTextPostCreate();
        labPage.writePostTitle("Классно");
        labPage.writePost("Очень классно");

        assertEquals("Группа", labPage.getGroupName());
        labPage.chooseGroup();
        Thread.sleep(3000);
        assertEquals("Genshin Impact", labPage.getGroupName());
        
        assertEquals("С правильной категорией получите больше просмотров!", labPage.getCategoryName());
        labPage.chooseCategory();
        Thread.sleep(3000);
        assertEquals("Обсуждения", labPage.getCategoryName());


        assertFalse(labPage.isOriginalWork());
        labPage.clickOriginalWork();
        assertTrue(labPage.isOriginalWork());
        labPage.clickOriginalWork();
        assertFalse(labPage.isOriginalWork());
        labPage.clickOriginalWork();
        assertTrue(labPage.isOriginalWork());


        assertTrue(labPage.isRepostRight());
        labPage.clickReportRight();
        assertFalse(labPage.isRepostRight());
        labPage.clickReportRight();
        assertTrue(labPage.isRepostRight());
        labPage.clickReportRight();
        assertFalse(labPage.isRepostRight());

        labPage.clickPostSend();

        Thread.sleep(3000);

        assertEquals("Классно", labPage.getMyPostTitle());

        assertEquals("Очень классно", labPage.getMyPost());

        labPage.clickDeletePost();

        Thread.sleep(5000);

        assertEquals(0, labPage.getMyPostsCount());


        String name = "redmimimi";
        labPage.search(name);
        assertEquals(name, labPage.getFirstResultName());

        UserPage userPage = labPage.toFirstResultPage();
        assertEquals(name, userPage.getUserNickname());


        assertFalse(userPage.isFollow());
        userPage.clickFollowButton();
        assertTrue(userPage.isFollow());

        assertFalse(userPage.isAskingNews());
        userPage.clickAskNewsButton();
        assertTrue(userPage.isAskingNews());

        userPage.backToLabPage();

        assertTrue(labPage.isLogin());
        labPage.logout();
        Thread.sleep(3000);
        assertFalse(labPage.isLogin());

        LoginPage secondLoginPage = labPage.goToLoginPage();
        justLogin(secondLoginPage, "redmimimirise@mail.ru");




    }

    public static void labLogin(WebDriver driver) throws InterruptedException{
//        HomePage homePage = HomePage.init(driver);
//        assertFalse(homePage.isLogin());
//        LabPage labPage = homePage.goToLabPage();
        LabPage labPage = LabPage.init(driver);

        assertEquals("HoYoLAB - Игровое сообщество", labPage.getTitle());
        assertFalse(labPage.isLogin());

        LoginPage loginPage = labPage.goToLoginPage();

        login(loginPage);

        driver.switchTo().defaultContent();
        Thread.sleep(3000);
        assertEquals("HoYoLAB - Игровое сообщество", labPage.getTitle());
        assertTrue(labPage.isLogin());

        labPage.skipInterestDialog();
        assertTrue(labPage.isExistInterestDialog());

//        assertTrue(homePage.isLogin());
    }

    public static void labReg(WebDriver driver, String email) throws InterruptedException{
        HomePage homePage = HomePage.init(driver);
        LabPage labPage = homePage.goToLabPage();

        assertEquals("HoYoLAB - Игровое сообщество", labPage.getTitle());
        assertFalse(labPage.isLogin());

        RegistrationPage registrationPage = labPage.goToRegPage();

        registration(email, registrationPage);

        driver.switchTo().defaultContent();
        assertEquals("HoYoLAB - Игровое сообщество", labPage.getTitle());
        assertTrue(labPage.isLogin());
    }




    public static void deleteAccount(WebDriver driver) throws InterruptedException{
        HomePage homePage = HomePage.init(driver);
        AccountPage accountPage = homePage.goToAccountPage();
        accountPage.deleteAccount();
    }

//    public static void deleteAccount(WebDriver driver) throws InterruptedException{
//        HomePage homePage = HomePage.init(driver);
//        AccountPage accountPage = homePage.goToAccountPage();
//
//        accountPage.clickManageButton();
//        accountPage.clickDeleteButton();
//
//        driver.switchTo().frame("hyv-account-frame");
//
//        accountPage.clickSendCodeButton();
//
//        Thread.sleep(30000);
//        accountPage.clickSubmit();
//
//    }


}
