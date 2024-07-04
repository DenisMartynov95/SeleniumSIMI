package PageObject;

import PageObject.SearchPages.FdmSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SIMainPage {
    private final WebDriver driver;

    public SIMainPage(WebDriver driver) {
        this.driver = driver;
    }


        /*
                 Шапка
                                */
    private final By inputSearchOnSI = By.xpath("//*[@id=\"search_inp\"]");
        private final By desiredOption = By.xpath("//*[@id=\"https://free-download-manager.software.informer.com/\"]");
    private final By openLi = By.xpath("//*[@id=\"top\"]/div/div[4]/div[1]/div[1]");
        private final By btnGoMI = By.xpath("//*[@id=\"top\"]/div/div[4]/div[1]/div[2][@class='platform_dropdown platforms']/nav/a[@class='mac']");
        private final By btnGoSI = By.xpath("//*[@id=\"top\"]/div/div[4]/div[1]/div[2][@class='platform_dropdown platforms']/nav/a[@class='windows']");
    private final By btnGoLogin = By.xpath("//*[@id=\"top\"]/div/div[4]/div[2]/a");

    public FdmSearchPage SearchProgram() {
        driver.findElement(inputSearchOnSI).sendKeys("fdm");
        driver.findElement(desiredOption).isDisplayed();
        driver.findElement(desiredOption).click();
        return new FdmSearchPage(driver);
    }
}
