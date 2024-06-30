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

import static config.Constants.REGISTRATION_PAGE;

public class RegistrationTests {
    private WebDriver webDriver;

    MainPage mainPage;
    AuthPage authPage;
    RegistrationPage registrationPage;
    AccountPage accountPage;

    private final UserSteps userSteps = new UserSteps();
    private final User user = DataGenerator.randomUser();
    private final User userWithShortPassword = DataGenerator.userWithShortPassword();
    private boolean userCreated = false;


    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        webDriver = WebDriverFactory.getWebDriver("chrome");
        webDriver.get(REGISTRATION_PAGE);

        mainPage = new MainPage(webDriver);
        authPage = new AuthPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        accountPage = new AccountPage(webDriver);

        registrationPage.openRegistrationPage();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void userRegistrationOkCheck() {
        //Регистрация
        registrationPage.registrationInput(user);

        mainPage.openMainPage();
        Assert.assertTrue(mainPage.createOrderButtonIsDisplayedCheck());
        userCreated = true;
    }

    @Test
    @DisplayName("Проверка появления ошибки при вводе пароля менее 6 символов")
    public void registrationWithShortPasswordFailCheck() {
        registrationPage.registrationInput(userWithShortPassword);
        Assert.assertTrue(registrationPage.errorMessageCheck());
    }


    @After
    public void tearDown() {
        WebDriverFactory.driverQuit(webDriver);
        if (userCreated) {
            userSteps
                    .userDelete(userSteps.getAccessToken(user));
        }
    }

}
