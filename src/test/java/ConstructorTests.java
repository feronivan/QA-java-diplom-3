import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.MainPage;

import static config.Constants.MAIN_PAGE;

public class ConstructorTests {
    private WebDriver webDriver;
    MainPage mainPage;

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getWebDriver("chrome");
        webDriver.get(MAIN_PAGE);
        mainPage = new MainPage(webDriver);
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void transitionToBunsCheck() {
        /*Для проверки работы раздела «Булки», для начала необходимо сделать
        элемент «Булки» кликабельным. В связи с этим, в начале теста мы
        переходим на другой раздел - «Начинки» */
        mainPage.transitionToppingsClick();

        mainPage.transitionBunsClick();
        mainPage.bunsMenuIsVisibleCheck();
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void transitionToSaucesCheck() {
        /*Для проверки работы раздела «Булки», для начала необходимо скрыть раздел «Соусы».
        В связи с этим, в начале теста мы переходим на раздел - «Начинки» */
        mainPage.transitionToppingsClick();

        mainPage.transitionSaucesClick();
        mainPage.saucesMenuIsVisibleCheck();
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void transitionToToppingsCheck() {
        mainPage.transitionToppingsClick();
        mainPage.toppingsMenuIsVisibleCheck();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

}
