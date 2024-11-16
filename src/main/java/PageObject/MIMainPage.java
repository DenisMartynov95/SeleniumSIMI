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
    private final By necessaryNamePage = By.xpath(".//header/div[1]/div/a/img");
    public boolean checkSuccessRedirect() {
        return driver.findElement(necessaryNamePage).getText().contains(Asserts.NAME_MI_PAGE);
    }
    public String getNameMiPage() {
        return driver.findElement(necessaryNamePage).getText();
    }

}
