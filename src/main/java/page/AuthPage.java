package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import user.User;

import java.time.Duration;

import static config.Constants.AUTH_PAGE;

public class AuthPage {
    private final WebDriver webDriver;

    public AuthPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Страница входа в аккаунт. Локатор кнопки "Войти":
    private final By authPageAuthButtonLoc = By.xpath("//div[@class='Auth_login__3hAey']//button[text()='Войти']");
    //Страница входа в аккаунт. Локатор поля "Email":
    private final By authPagePasswordFieldLoc = By.xpath(".//label[contains(text(),'Email')]/../input");
    //Страница входа в аккаунт. Локатор поля "Пароль":
    private final By authPageEmailFieldLoc = By.xpath(".//label[contains(text(),'Пароль')]/../input");

    @Step("Открытие страницы авторизации")
    public void openAuthPage() {
        webDriver.get(AUTH_PAGE);
    }

    @Step("Заполнение данных для входа. Клик по кнопке Войти")
    public void loginInput(User user) {
        webDriver.findElement(authPagePasswordFieldLoc).sendKeys(user.getEmail());
        webDriver.findElement(authPageEmailFieldLoc).sendKeys(user.getPassword());
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(authPageAuthButtonLoc));
        webDriver.findElement(authPageAuthButtonLoc).click();
    }

}
