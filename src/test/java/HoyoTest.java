import org.openqa.selenium.WebDriver;
import pages.*;

import static org.junit.jupiter.api.Assertions.*;

public class HoyoTest {


    public static void titleTest(WebDriver driver) {
        HomePage homePage = HomePage.init(driver);
        assertEquals("Главная | Официальный сайт Honkai: Star Rail | Пусть это путешествие приведёт нас к звёздам", homePage.getTitle());
    }

    public static void loginTest(WebDriver driver, String email) throws InterruptedException {
        HomePage homePage = HomePage.init(driver);
        assertFalse(homePage.isLogin());
        LoginPage loginPage = homePage.goToLoginPage();

        login(loginPage, email);
        driver.switchTo().defaultContent();
        assertTrue(homePage.isLogin());
    }

    private static void login(LoginPage loginPage, String userEmail) throws InterruptedException {
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
        Thread.sleep(2000);
        assertFalse(loginPage.isLoginError());
        assertTrue(loginPage.isPasswordError());
        assertTrue(loginPage.isDisabledSubmitButton());
        Thread.sleep(2000);
        assertFalse(loginPage.isLoginError());

        loginPage.clickPassword();
        loginPage.clickLogin();
        assertTrue(loginPage.isPasswordError());
        loginPage.printPassword("");
        loginPage.clickLogin();
        assertTrue(loginPage.isPasswordError());
        assertTrue(loginPage.isDisabledSubmitButton());

        loginPage.printPassword("qwerty1234");
        loginPage.clickLogin();
        Thread.sleep(2000);
        assertFalse(loginPage.isPasswordError());
        assertFalse(loginPage.isDisabledSubmitButton());

        loginPage.clickSubmitButton();

    }


    private static void justLogin(LoginPage loginPage, String email) {
        loginPage.printLogin(email);
        loginPage.printPassword("qwerty1234");
        assertFalse(loginPage.isDisabledSubmitButton());

        loginPage.clickSubmitButton();
    }


    public static void registrationTest(WebDriver driver, String email) throws InterruptedException {
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
        Thread.sleep(1000);
        registrationPage.clickUserAgreement();
        Thread.sleep(3000);
        assertTrue(registrationPage.isCheckedUserAgreement());
        Thread.sleep(1000);
        registrationPage.clickUserAgreement();
        Thread.sleep(3000);
        assertFalse(registrationPage.isCheckedUserAgreement());
        Thread.sleep(1000);
        registrationPage.clickUserAgreement();

        assertFalse(registrationPage.isCheckedMarketingAgreement());
        registrationPage.clickMarketingAgreement();
        assertTrue(registrationPage.isCheckedMarketingAgreement());
        registrationPage.clickMarketingAgreement();
        assertFalse(registrationPage.isCheckedMarketingAgreement());

        registrationPage.waitForCode();
        registrationPage.clickSubmit();

    }


    private static void postTest(LabPage labPage) throws InterruptedException {
        String articleTitle = labPage.getFirstNewTitle();
        ArticlePage articlePage = labPage.readNew();
        Thread.sleep(3000);
        assertEquals(articleTitle, articlePage.getArticleTitle());

        assertFalse(articlePage.isFollow());

        articlePage.clickFollowButton();
        Thread.sleep(1000);
        assertTrue(articlePage.isFollow());
        articlePage.clickUnFollow();
        Thread.sleep(1000);
        assertFalse(articlePage.isFollow());


        Integer reactionCount = articlePage.getReactionCount();

        articlePage.clickReaction();
        Thread.sleep(1000);
        assertEquals(reactionCount + 1, articlePage.getReactionCount());
        articlePage.clickReaction();
        Thread.sleep(1000);
        assertEquals(reactionCount, articlePage.getReactionCount());

        String comment = "Классно!";
        if (articlePage.isFirstCommentHasText()) {
            assertNotEquals(comment, articlePage.getFirstCommentText());
        }
        articlePage.writeComment(comment);
        articlePage.clickSendComment();
        Thread.sleep(3000);
        assertEquals(comment, articlePage.getFirstCommentText());

        articlePage.clickDeleteComment();
        Thread.sleep(3000);
        if (articlePage.isFirstCommentHasText()) {
            assertNotEquals(comment, articlePage.getFirstCommentText());
        }

    }

    public static void toLab(WebDriver driver, String userEmail, String username, String secondUserEmail,
                             String secondUserName, String secondUserId) throws InterruptedException {
        HomePage homePage = HomePage.init(driver);
        LabPage labPage = homePage.goToLabPage();

        assertEquals("HoYoLAB - Игровое сообщество", labPage.getTitle());
        assertFalse(labPage.isLogin());

        LoginPage loginPage = labPage.goToLoginPage();

        login(loginPage, userEmail);

        driver.switchTo().defaultContent();
        Thread.sleep(3000);
        assertEquals("HoYoLAB - Игровое сообщество", labPage.getTitle());
        assertTrue(labPage.isLogin());

        labPage.skipInterestDialog();
        assertTrue(labPage.isExistInterestDialog());

        //подписка отписка реакции комментарии
        postTest(labPage);

        //новый пост и удаление
        newPostTest(labPage);


        // подписка на другого пользователя и запрос новинок

        UserPage userPage = labPage.toUserPage(secondUserId);
        assertEquals(secondUserName, userPage.getUserNickname());

        assertFalse(userPage.isFollow());
        userPage.clickFollowButton();
        Thread.sleep(2000);
        assertTrue(userPage.isFollow());

        userPage.clickAskNewsButton();
        Thread.sleep(2000);
        assertTrue(userPage.isAskingNews());

        // выход
        assertTrue(labPage.isLogin());
        logout(labPage);

        assertFalse(labPage.isLogin());

        notificationTest(driver, labPage, secondUserEmail, secondUserName, userEmail, username);

    }

    private static void newPostTest(LabPage labPage) throws InterruptedException {
        NewArticlePage articlePage = labPage.writeNewPost();

        articlePage.writePostTitle("Классно");
        articlePage.writePost("Очень классно");

        assertEquals("Группа", articlePage.getGroupName());
        articlePage.chooseGroup();
        Thread.sleep(3000);
        assertEquals("Genshin Impact", articlePage.getGroupName());

        assertEquals("С правильной категорией получите больше просмотров!", articlePage.getCategoryName());
        articlePage.chooseCategory();
        Thread.sleep(3000);
        assertEquals("Обсуждения", articlePage.getCategoryName());


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

        Thread.sleep(3000);

        assertEquals("Классно", articlePage.getMyPostTitle());

        assertEquals("Очень классно", articlePage.getMyPost());

        articlePage.clickDeletePost();

        Thread.sleep(5000);

        assertFalse(labPage.isExistsJustNowPost());

    }

    private static void logout(LabPage labPage) throws InterruptedException {
        labPage.logout();
        Thread.sleep(5000);
    }

    private static void notificationTest(WebDriver driver, LabPage labPage, String secondUserEmail, String secondUsername, String firstUserEmail, String firstUserName) throws InterruptedException {
        assertFalse(labPage.isLogin());
        LoginPage loginPage = labPage.goToLoginPage();
        justLogin(loginPage, secondUserEmail);

        driver.switchTo().defaultContent();
        Thread.sleep(10000);
        assertTrue(labPage.isLogin());

        Thread.sleep(3000);

        labPage.clickFollowNotifications();
        assertTrue(labPage.checkFollowNotification(firstUserName));

        writePost(labPage);

        assertTrue(labPage.isLogin());
        logout(labPage);
        assertFalse(labPage.isLogin());

        LoginPage secondLoginPage = labPage.goToLoginPage();
        justLogin(secondLoginPage, firstUserEmail);
        driver.switchTo().defaultContent();
        Thread.sleep(10000);
        assertTrue(labPage.isLogin());

        labPage.clickSystemNotifications();
        assertTrue(labPage.checkNewPostNotification(secondUsername));

    }


    private static void writePost(LabPage labPage) throws InterruptedException {
        NewArticlePage articlePage = labPage.writeNewPost();

        articlePage.writePostTitle("Классно");
        articlePage.writePost("Очень классно");

        articlePage.chooseGroup();
        Thread.sleep(2000);
        articlePage.chooseCategory();

        articlePage.clickOriginalWork();
        Thread.sleep(2000);
        articlePage.clickReportRight();

        articlePage.clickPostSend();

        Thread.sleep(3000);

        assertEquals("Классно", articlePage.getMyPostTitle());
        assertEquals("Очень классно", articlePage.getMyPost());
    }

}
