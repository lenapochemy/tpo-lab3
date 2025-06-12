import org.openqa.selenium.WebDriver;
import pages.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class HoyoTest {


    public static void titleTest(WebDriver driver) {
        HomePage homePage = HomePage.init(driver);
        assertEquals("Главная | Официальный сайт Honkai: Star Rail | Пусть это путешествие приведёт нас к звёздам", homePage.getTitle());
    }

    public static void loginTest(WebDriver driver, String email){
        HomePage homePage = HomePage.init(driver);
        assertFalse(homePage.isLogin());
        LoginPage loginPage = homePage.goToLoginPage();

        login(loginPage, email);
        driver.switchTo().defaultContent();
        assertTrue(homePage.isLogin());
    }

    private static void login(LoginPage loginPage, String userEmail) {
        assertTrue(loginPage.isDisabledSubmitButton());
        loginPage.clickLogin();
        loginPage.clickPassword();
        assertTrue(loginPage.isLoginError());
        loginPage.printLogin("");
        loginPage.clickPassword();
        assertTrue(loginPage.isLoginError());
        assertTrue(loginPage.isDisabledSubmitButton());

        loginPage.printLogin(userEmail);
        loginPage.clickPassword();
        assertTrue(loginPage.notLoginError());
        assertTrue(loginPage.isPasswordError());
        assertTrue(loginPage.isDisabledSubmitButton());

        loginPage.clickPassword();
        loginPage.clickLogin();
        assertTrue(loginPage.isPasswordError());
        loginPage.printPassword("");
        loginPage.clickLogin();
        assertTrue(loginPage.isPasswordError());
        assertTrue(loginPage.isDisabledSubmitButton());

        loginPage.printPassword("qwerty1234");
        loginPage.clickLogin();
        assertTrue(loginPage.notPasswordError());
        assertFalse(loginPage.isDisabledSubmitButton());

        loginPage.clickSubmitButton();
    }


    private static void justLogin(LoginPage loginPage, String email) {
        loginPage.printLogin(email);
        loginPage.printPassword("qwerty1234");
        assertFalse(loginPage.isDisabledSubmitButton());

        loginPage.clickSubmitButton();
    }


    public static void registrationTest(WebDriver driver, String email)  {
        HomePage homePage = HomePage.init(driver);
        assertFalse(homePage.isLogin());
        LoginPage loginPage = homePage.goToLoginPage();
        RegistrationPage registrationPage = loginPage.goToRegistrationPage();

        registration(email, registrationPage);
        driver.switchTo().defaultContent();
        assertTrue(homePage.isLogin());
    }

    private static void registration(String email, RegistrationPage registrationPage) {
        assertTrue(registrationPage.isSubmitDisabled());

        registrationPage.clickEmailInout();
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertTrue(registrationPage.isEmailError());
        assertTrue(registrationPage.isEmailErrorEquals("Необходимо заполнить поле: Электронная почта"));

        registrationPage.writeEmail("");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertTrue(registrationPage.isEmailError());
        assertTrue(registrationPage.isEmailErrorEquals("Необходимо заполнить поле: Электронная почта"));

        registrationPage.writeEmail("sssss");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertTrue(registrationPage.isEmailError());
        assertTrue(registrationPage.isEmailErrorEquals("Неверный формат электронной почты"));

        registrationPage.writeEmail(email);
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertFalse(registrationPage.isEmailError());

        registrationPage.clickCodeSendButton();
        registrationPage.waitForCode();

        registrationPage.clickPassword();
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertTrue(registrationPage.isPasswordError());
        assertTrue(registrationPage.isPasswordErrorEquals("Необходимо заполнить поле: Пароль"));

        registrationPage.writePassword("");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertTrue(registrationPage.isPasswordError());
        assertTrue(registrationPage.isPasswordErrorEquals("Необходимо заполнить поле: Пароль"));

        registrationPage.writePassword("ddddd");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertTrue(registrationPage.isPasswordError());
        assertTrue(registrationPage.isPasswordErrorEquals("Пароль должен включать от 8 до 30 цифр, букв или символов"));

        registrationPage.writePassword("dddddddd");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertTrue(registrationPage.isPasswordError());
        assertTrue(registrationPage.isPasswordErrorEquals("Не менее 2 типов символов"));

        registrationPage.writePassword("qwerty1234");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertFalse(registrationPage.isPasswordError());


        registrationPage.clickPasswordAgain();
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertTrue(registrationPage.isPasswordAgainError());
        assertTrue(registrationPage.isPasswordAgainErrorEquals("Необходимо заполнить поле: Подтвердите пароль"));

        registrationPage.writePasswordAgain("");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertTrue(registrationPage.isPasswordAgainError());
        assertTrue(registrationPage.isPasswordAgainErrorEquals("Необходимо заполнить поле: Подтвердите пароль"));

        registrationPage.writePasswordAgain("ddddd");
        registrationPage.clickCodeInput();
        assertTrue(registrationPage.isSubmitDisabled());
        assertTrue(registrationPage.isPasswordAgainError());
        assertTrue(registrationPage.isPasswordAgainErrorEquals("Пароли должны совпадать"));

        registrationPage.writePasswordAgain("qwerty1234");
        registrationPage.clickCodeInput();
        assertFalse(registrationPage.isSubmitDisabled());
        assertFalse(registrationPage.isPasswordAgainError());

        assertFalse(registrationPage.isCheckedUserAgreement());
        registrationPage.clickUserAgreement();
        assertTrue(registrationPage.isCheckedUserAgreement());
        registrationPage.clickUserAgreement();
        assertFalse(registrationPage.isCheckedUserAgreement());
        registrationPage.clickUserAgreement();

        assertTrue(registrationPage.isCheckedMarketingAgreement());
        registrationPage.clickMarketingAgreement();
        assertFalse(registrationPage.isCheckedMarketingAgreement());
        registrationPage.clickMarketingAgreement();
        assertTrue(registrationPage.isCheckedMarketingAgreement());
        registrationPage.clickMarketingAgreement();
        assertFalse(registrationPage.isCheckedMarketingAgreement());

        registrationPage.clickSubmit();
    }


    private static void postTest(LabPage labPage, String username) {
        String articleTitle = labPage.getFirstNewTitle();
        ArticlePage articlePage = labPage.readNew();
        assertEquals(articleTitle, articlePage.getArticleTitle());

        assertFalse(articlePage.isFollow());

        articlePage.clickFollowButton();
        assertTrue(articlePage.isFollow());
        articlePage.clickUnFollow();
        assertFalse(articlePage.isFollow());


        assertFalse(articlePage.isExistClickedReaction());
        articlePage.clickReaction();
        assertTrue(articlePage.isExistClickedReaction());
        articlePage.clickReaction();
        assertFalse(articlePage.isExistClickedReaction());

        String comment = "Классно!";

        assertFalse(articlePage.isFirstCommentAuthor(username));

        articlePage.writeComment(comment);
        articlePage.clickSendComment();

        assertTrue(articlePage.isFirstCommentAuthor(username));

        articlePage.clickDeleteComment();

        assertFalse(articlePage.isFirstCommentAuthor(username));

    }

    public static void labTest(WebDriver driver, String userEmail, String username, String secondUserEmail,
                               String secondUserName, String secondUserId){
        HomePage homePage = HomePage.init(driver);
        LabPage labPage = homePage.goToLabPage();

        assertEquals("HoYoLAB - Игровое сообщество", labPage.getTitle());
        assertFalse(labPage.isLogin());
        System.out.println("login is false");

        LoginPage loginPage = labPage.goToLoginPage();

        login(loginPage, userEmail);

        Duration timeout = loginPage.implicitTo0();
        driver.switchTo().defaultContent();
        loginPage.implicitToTimeout(timeout);

        assertTrue(labPage.isLogin());

        labPage.skipInterestDialog();

        //подписка отписка реакции комментарии
        postTest(labPage, username);

        //новый пост и удаление
        newPostTest(labPage);

        // подписка на другого пользователя и запрос новинок

        UserPage userPage = labPage.toUserPage(secondUserId);
        assertEquals(secondUserName, userPage.getUserNickname());

        assertFalse(userPage.isFollow());
        userPage.clickFollowButton();
        assertTrue(userPage.isFollow());

        userPage.clickAskNewsButton();
        assertTrue(userPage.isAskingNews());

        // выход
        assertTrue(labPage.isLogin());
        logout(labPage);
        assertFalse(labPage.isLogin());

        notificationTest(driver, labPage, secondUserEmail, secondUserName, userEmail, username);

    }

    private static void newPostTest(LabPage labPage){
        NewArticlePage articlePage = labPage.writeNewPost();

        articlePage.writePostTitle("Классно");
        articlePage.writePost("Очень классно");

        assertTrue(articlePage.getGroupName("Группа"));
        articlePage.chooseGroup();
        assertTrue(articlePage.getGroupName("Genshin Impact"));

        assertTrue(articlePage.getCategoryName("С правильной категорией получите больше просмотров!"));
        articlePage.chooseCategory();
        assertTrue(articlePage.getCategoryName("Обсуждения"));

        assertFalse(articlePage.isOriginalWork());
        articlePage.clickOriginalWork();
        assertTrue(articlePage.isOriginalWork());
        articlePage.clickOriginalWork();
        assertFalse(articlePage.isOriginalWork());
        articlePage.clickOriginalWork();
        assertTrue(articlePage.isOriginalWork());

        assertTrue(articlePage.isRepostRight());
        articlePage.clickReportRight();
        assertFalse(articlePage.isRepostRight());
        articlePage.clickReportRight();
        assertTrue(articlePage.isRepostRight());
        articlePage.clickReportRight();
        assertFalse(articlePage.isRepostRight());

        articlePage.clickPostSend();

        assertEquals("Классно", articlePage.getMyPostTitle());

        assertEquals("Очень классно", articlePage.getMyPost());

        articlePage.clickDeletePost();

        assertFalse(labPage.isExistsJustNowPost());

    }

    private static void logout(LabPage labPage) {
        labPage.logout();
    }

    private static void notificationTest(WebDriver driver, LabPage labPage, String secondUserEmail,
                                         String secondUsername, String firstUserEmail, String firstUserName) {
        assertFalse(labPage.isLogin());
        LoginPage loginPage = labPage.goToLoginPage();
        justLogin(loginPage, secondUserEmail);

        driver.switchTo().defaultContent();
        assertTrue(labPage.isLogin());

        labPage.clickFollowNotifications();
        assertTrue(labPage.checkFollowNotification(firstUserName));

        writePost(labPage);

        assertTrue(labPage.isLogin());
        logout(labPage);
        assertFalse(labPage.isLogin());

        LoginPage secondLoginPage = labPage.goToLoginPage();
        justLogin(secondLoginPage, firstUserEmail);
        driver.switchTo().defaultContent();
        assertTrue(labPage.isLogin());

        labPage.clickSystemNotifications();
        assertTrue(labPage.checkNewPostNotification(secondUsername));

    }


    private static void writePost(LabPage labPage){
        NewArticlePage articlePage = labPage.writeNewPost();

        articlePage.writePostTitle("Классно");
        articlePage.writePost("Очень классно");

        articlePage.chooseGroup();
        articlePage.chooseCategory();

        articlePage.clickOriginalWork();
        articlePage.clickReportRight();

        articlePage.clickPostSend();

        assertEquals("Классно", articlePage.getMyPostTitle());
        assertEquals("Очень классно", articlePage.getMyPost());
    }

}
