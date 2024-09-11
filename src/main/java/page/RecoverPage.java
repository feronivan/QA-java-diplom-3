package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static config.Constants.RECOVER_PASSWORD_PAGE;
import static java.time.Duration.ofSeconds;

public class RecoverPage {
    private final WebDriver webDriver;

    public RecoverPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Страница восстановления пароля. Локатор кнопки "Войти":
    private final By recoverPageAuthButtonLoc = By.xpath("//a[@class='Auth_link__1fOlj']");

    @Step("Открытие страницы восстановления пароля")
    public void openRecoverPasswordPage() {
        webDriver.get(RECOVER_PASSWORD_PAGE);
    }

    @Step("вход через кнопку в форме восстановления пароля")
    public void loginRecoverPage() {
        WebElement recoverPageAuthButton = webDriver.findElement(recoverPageAuthButtonLoc);
        new WebDriverWait(webDriver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(recoverPageAuthButton));
        recoverPageAuthButton.click();
    }
}
