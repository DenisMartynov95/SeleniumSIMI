package PageObject.SearchPages;

import Asserts.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FdmSearchPage {
    private final WebDriver driver;

    public FdmSearchPage(WebDriver driver) {
        this.driver = driver;
    }

            /*
                    Локаторы для ассерта
                                                */

    private final By necessaryName = By.xpath("/html/body/div[1]/div[3]/div/div[2]/div[1]/h1/span[1]");
    public boolean checkSuccessSearch() {
        return driver.findElement(necessaryName).getText().contains(Asserts.NAME_PROGRAM_FDM);
    }
    public  String getNameProgram() { // Для вывода в конце теста того, что нашел
        return driver.findElement(necessaryName).getText();
    }

}
