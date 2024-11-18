package PageObject;

import Asserts.Asserts;
import WebDriverFactory.WaitSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

}
