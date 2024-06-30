import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.AccountPage;
import page.AuthPage;
import page.MainPage;
import page.RegistrationPage;
import steps.UserSteps;
import user.DataGenerator;
import user.User;

import static config.Constants.MAIN_PAGE;

public class TransitionTests {
    private WebDriver webDriver;

    MainPage mainPage;
    AuthPage authPage;
    RegistrationPage registrationPage;
    AccountPage accountPage;

    private final UserSteps userSteps = new UserSteps();
    private final User user = DataGenerator.randomUser();

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        webDriver = WebDriverFactory.getWebDriver("chrome");
        webDriver.get(MAIN_PAGE);

        mainPage = new MainPage(webDriver);
        authPage = new AuthPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        accountPage = new AccountPage(webDriver);

        userSteps.userCreate(user);
        authPage.openAuthPage();
        authPage.loginInput(user);
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет»")
    public void transitionToAccountClickAccount() {
        mainPage.accountClick();
        Assert.assertTrue(accountPage.userMenuIsDisplayedCheck());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void transitionToConstructorClickConstructor() {
        mainPage.accountClick();
        accountPage.clickConstructorButton();
        Assert.assertTrue(mainPage.constructorIsDisplayedCheck());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void transitionToConstructorClickStellarBurgers() {
        mainPage.accountClick();
        accountPage.clickStellarBurgersLogo();
        Assert.assertTrue(mainPage.constructorIsDisplayedCheck());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void logoutClickExit() {
        mainPage.accountClick();
        accountPage.logoutAccount();
        mainPage.openMainPage();
        Assert.assertTrue(mainPage.authButtonIsDisplayedCheck());
    }

    @After
    public void tearDown() {
        WebDriverFactory.driverQuit(webDriver);
        userSteps
                .userDelete(userSteps.getAccessToken(user));
    }
}
