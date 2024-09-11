package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private final WebDriver webDriver;

    public AccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Страница "Личный кабинет". Локатор кнопки "Выйти":
    private final By accountPageLogoutButtonLoc = By.xpath("//button[contains(text(),'Выход')]");
    //Страница личного кабинета. Локатор кнопки "Конструктор":
    private final By accountPageConstructorButtonLoc = By.xpath("//p[contains(text(),'Конструктор')]");
    //Страница личного кабинета. Локатор логотипа "Stellar Burgers":
    private final By accountPageStellarBurgersLogoLoc = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']//a//*[name()='svg']");
    //Страница личного кабинета. Локатор меню пользователя
    private final By accountPageProfileTextLoc = By.xpath("//p[@class='Account_text__fZAIn text text_type_main-default' and contains(text(), 'В этом разделе')]");

    @Step("Личный кабинет. Проверка отображения меню пользователя")
    public boolean userMenuIsDisplayedCheck() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(accountPageProfileTextLoc));
        return webDriver.findElement(accountPageProfileTextLoc).isDisplayed();
    }

    @Step("Клик на Конструктор")
    public void clickConstructorButton() {
        webDriver.findElement(accountPageConstructorButtonLoc).click();
    }

    @Step("Клик на логотип Stellar Burgers")
    public void clickStellarBurgersLogo() {
        webDriver.findElement(accountPageStellarBurgersLogoLoc).click();
    }

    @Step("Выход из аккаунта по кнопке Выйти в личном кабинете")
    public void logoutAccount() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(accountPageLogoutButtonLoc));
        webDriver.findElement(accountPageLogoutButtonLoc).click();
    }

}
