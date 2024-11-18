package PageObject;

import Asserts.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class MIMainPage {
    private final WebDriver driver;

    public MIMainPage(WebDriver driver) {
        this.driver = driver;
    }


        /*
                 Шапка
                                */


    //Смоук - кейс №3 продолжение с обработкой ассерта
    private final By necessaryNamePage = By.cssSelector("body > div.wrapper-content > aside > div > div > span");

    public boolean checkSuccessRedirect() {
        new WebDriverWait(driver,5).until(ExpectedConditions.textToBe(necessaryNamePage,"Mac"));
        return driver.findElement(necessaryNamePage).getText().contains(Asserts.NAME_MI_PAGE);
    }
    public String getNameMiPage() {
        return driver.findElement(necessaryNamePage).getText();
    }

    //Смоук - кейс №3 переход на сайт SI через лист
    private final By openLi = By.xpath("//*[@id=\"top\"]/div/div[4]/div[1]/div[1]");
    private final By btnGoSI = By.xpath("//*[@id=\"top\"]/div/div[4]/div[1]/div[2][@class='platform_dropdown platforms']/nav/a[@class='windows']");

    public SIMainPage goToSIPage() {
        driver.findElement(openLi).click();
        if (driver.findElement(btnGoSI).isDisplayed()) {
            driver.findElement(btnGoSI).click();
        } else {
            System.out.println("Тест кейс №4 провалился!");
        }
        return new SIMainPage(driver);
    }

    //Смоук - кейс №5 проверка работы редиректов на страницы посредством основных кнопок сервиса
    //Продолжение сквозного теста, здесь проверяется что нужная страница открыта, а далее мы снова возвращаемся на SI сервис
    private final By btnGoSIService = By.cssSelector("body > div.wrapper-content > aside > div > div > a");
    //Переменная для промежуточной проверки локатора сервиса, перед тем как перейти обратно в SI
    private final By checkNamePage = By.cssSelector("#top > div > a > img");
    private final String needNamePage = "Mac Informer";
    public SIMainPage goToSiService() {
        String lambda = driver.findElement(checkNamePage).getAttribute("alt");
        if (Objects.equals(lambda, needNamePage)) {
            driver.findElement(btnGoSIService).click();
        } else {
            System.out.println("Тест №5 провалился, так как на странице MI локатор не совпал");
        }
        return new SIMainPage(driver);
    }

}
