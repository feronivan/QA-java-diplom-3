import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.*;
import steps.UserSteps;
import user.DataGenerator;
import user.User;

import static config.Constants.MAIN_PAGE;

public class AuthenticationTests {
    private WebDriver webDriver;
    private final UserSteps userSteps = new UserSteps();
    private final User user = DataGenerator.randomUser();

    MainPage mainPage;
    AuthPage authPage;
    RegistrationPage registrationPage;
    AccountPage accountPage;
    RecoverPage recoverPage;

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        webDriver = WebDriverFactory.getWebDriver("chrome");
        webDriver.get(MAIN_PAGE);

        mainPage = new MainPage(webDriver);
        authPage = new AuthPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        accountPage = new AccountPage(webDriver);
        recoverPage = new RecoverPage(webDriver);

        userSteps.userCreate(user);

    }


    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void authMainPageAuthButtonCheck() {
        mainPage.loginClick();
        authPage.loginInput(user);
        //проверка успешного входа
        mainPage.accountClick();
        Assert.assertTrue(accountPage.userMenuIsDisplayedCheck());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет» на главной")
    public void authMainAccountButtonCheck() {
        mainPage.accountClick();
        authPage.loginInput(user);
        //проверка успешного входа
        mainPage.openMainPage();
        Assert.assertTrue(mainPage.createOrderButtonIsDisplayedCheck());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void authRegistrationPageCheck() {
        registrationPage.openRegistrationPage();
        //Клик через кнопке «Войти»
        registrationPage.loginRegistrationClick();
        //Страница входа в аккаунт. Заполнение полей
        authPage.loginInput(user);
        //проверка успешного входа
        mainPage.accountClick();
        Assert.assertTrue(accountPage.userMenuIsDisplayedCheck());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void authRecoverPageCheck() {
        //открытие страницы с формой восстановления пароля
        recoverPage.openRecoverPasswordPage();
        //Клик через кнопке «Войти»
        recoverPage.loginRecoverPage();
        authPage.loginInput(user);
        //проверка успешного входа
        mainPage.accountClick();
        Assert.assertTrue(accountPage.userMenuIsDisplayedCheck());
    }

    @After
    public void tearDown() {
        WebDriverFactory.driverQuit(webDriver);
        userSteps
                .userDelete(userSteps.getAccessToken(user));
    }

}
