package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static config.Constants.*;

public class MainPage {
    private final WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Главная страница. Локатор кнопки "Личный кабинет":
    private final By mainPageAccountButtonLoc = By.xpath("//p[contains(text(),'Личный Кабинет')]");
    //Главная страница. Локатор кнопки "Войти в аккаунт":
    private final By mainPageAuthButtonLoc = By.xpath("//button[text()='Войти в аккаунт']");
    //Главная страница. Локатор кнопки "Оформить заказ":
    private final By mainPageCreateOrderButtonLoc = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    //Главная страница. Локатор кнопки "Булки":
    private final By mainPageBunsButtonLoc = By.xpath("//span[contains(text(),'Булки')]");
    //Главная страница. Локатор меню "Булки":
    private final By mainPageBunsMenuLoc = By.xpath("//h2[contains(text(),'Булки')]");
    //Главная страница. Локатор кнопки "Соусы":
    private final By mainPageSaucesButtonLoc = By.xpath("//span[contains(text(),'Соусы')]");
    //Главная страница. Локатор меню "Соусы":
    private final By mainPageSaucesMenuLoc = By.xpath("//h2[contains(text(),'Соусы')]");
    //Главная страница. Локатор кнопки "Начинки":
    private final By mainPageToppingsButtonLoc = By.xpath("//span[contains(text(),'Начинки')]");
    //Главная страница. Локатор меню "Начинки":
    private final By mainPageToppingsMenuLoc = By.xpath("//h2[contains(text(),'Начинки')]");
    //Главная страница. Локатор меню конструктора
    private final By mainPageConstructorMenuLoc = By.xpath("//*[@id=\"root\"]/div/main/section[1]");


    @Step("Открытие главной страницы")
    public void openMainPage() {
        webDriver.get(MAIN_PAGE);
    }

    @Step("Клик по кнопке «Войти в аккаунт» на главной")
    public void loginClick() {
        webDriver.findElement(mainPageAuthButtonLoc).click();
    }

    @Step("Клик по кнопке «Личный кабинет»")
    public void accountClick() {
        webDriver.findElement(mainPageAccountButtonLoc).click();
    }

    @Step("Главная страница. Проверка отображения меню конструктора")
    public boolean constructorIsDisplayedCheck() {
        return webDriver.findElement(mainPageConstructorMenuLoc).isDisplayed();
    }

    @Step("Главная страница. Проверка отображения кнопки Войти в аккаунт")
    public boolean authButtonIsDisplayedCheck() {
        return webDriver.findElement(mainPageAuthButtonLoc).isDisplayed();
    }

    @Step
    public boolean createOrderButtonIsDisplayedCheck() {
        return webDriver.findElement(mainPageCreateOrderButtonLoc).isDisplayed();
    }

    @Step("Переход к разделу «Булки»")
    public void transitionBunsClick() {
        webDriver.findElement(mainPageBunsButtonLoc).click();
    }

    @Step("Проверка отображения раздела «Булки»")
    public boolean bunsMenuIsVisibleCheck() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(mainPageBunsMenuLoc));
        return webDriver.findElement(mainPageBunsMenuLoc).isDisplayed();
    }

    @Step("Переход к разделу «Соусы»")
    public void transitionSaucesClick() {
        webDriver.findElement(mainPageSaucesButtonLoc).click();
    }

    @Step("Проверка отображения раздела «Соусы»")
    public boolean saucesMenuIsVisibleCheck() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(mainPageSaucesMenuLoc));
        return webDriver.findElement(mainPageSaucesMenuLoc).isDisplayed();
    }

    @Step("Переход к разделу «Начинки»")
    public void transitionToppingsClick() {
        webDriver.findElement(mainPageToppingsButtonLoc).click();
    }

    @Step("Проверка отображения раздела «Начинки»")
    public boolean toppingsMenuIsVisibleCheck() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(mainPageToppingsMenuLoc));
        return webDriver.findElement(mainPageToppingsMenuLoc).isDisplayed();
    }

}
