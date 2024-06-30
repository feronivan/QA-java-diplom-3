package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import user.User;

import java.time.Duration;

import static config.Constants.REGISTRATION_PAGE;

public class RegistrationPage {
    private final WebDriver webDriver;

    public RegistrationPage(WebDriver driver) {
        this.webDriver = driver;
    }

    //Локатор поля "Имя":
    private final By registrationPageNameFieldLoc = By.xpath("//label[text()='Имя']/following-sibling::input");
    //Локатор поля "Email":
    private final By registrationPageEmailFieldLoc = By.xpath("//label[text()='Email']/following-sibling::input");
    //Локатор поля "Пароль":
    private final By registrationPagePasswordFieldLoc = By.xpath("//label[text()='Пароль']/following-sibling::input");
    //Локатор кнопки "Зарегистрироваться":
    private final By registrationPageRegistrationButtonLoc = By.xpath("//button[text()='Зарегистрироваться']");
    //Локатор кнопки "Войти":
    private final By registrationPageAuthButtonLoc = By.xpath("//a[@class='Auth_link__1fOlj' and contains(@href, '/login')]");
    //Локатор сообщения об ошибке:
    private final By errorIncorrectPasswordLoc = By.xpath("//div[@class='input__container']//p[text() = 'Некорректный пароль']");

    @Step("Открытие страницы регистрации")
    public void openRegistrationPage() {
        webDriver.get(REGISTRATION_PAGE);
    }

    @Step("Регистрация. Ввод данных. Клик по кнопке Зарегистрироваться")
    public void registrationInput(User user) {
        webDriver.findElement(registrationPageNameFieldLoc).sendKeys(user.getName());
        webDriver.findElement(registrationPageEmailFieldLoc).sendKeys(user.getEmail());
        webDriver.findElement(registrationPagePasswordFieldLoc).sendKeys(user.getPassword());
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(registrationPageRegistrationButtonLoc));
        webDriver.findElement(registrationPageRegistrationButtonLoc).click();
    }

    @Step("")
    public void loginRegistrationClick() {
        webDriver.findElement(registrationPageAuthButtonLoc).click();
    }

    @Step("Проверка отображения сообщения об ошибке")
    public boolean errorMessageCheck() {
        return webDriver.findElement(errorIncorrectPasswordLoc).isDisplayed();
    }
}
