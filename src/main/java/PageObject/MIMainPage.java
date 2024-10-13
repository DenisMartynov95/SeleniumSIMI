package PageObject;

import Asserts.Asserts;
import WebDriverFactory.WaitSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MIMainPage {
    private final WebDriver driver;

    public MIMainPage(WebDriver driver) {
        this.driver = driver;
    }


        /*
                 Шапка
                                */


    //Смоук - кейс №3 продолжение с обработкой ассерта
    private final By necessaryNamePage = By.xpath("/html/body/header/div[1]/div/div[4]/div[1]/div[1]");
    public boolean checkSuccessRedirect() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Boolean element = wait.until(ExpectedConditions.textToBePresentInElementLocated(necessaryNamePage,"Mac"));
        if(element) {
            System.out.println("Тест кейс №3 успешен");
        } else {
            System.out.println("Тест кейс №3 провален!");
        }
        return element;
    }
    public String getNameMiPage() {
        return driver.findElement(necessaryNamePage).getText();
    }

}
