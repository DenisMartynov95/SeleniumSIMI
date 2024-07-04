import Asserts.Asserts;
import PageObject.SIMainPage;
import PageObject.SearchPages.FdmSearchPage;
import WebDriverFactory.UrlSettings;
import WebDriverFactory.WaitSettings;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestClass {
    private WebDriver driver;

    @Before
    public void SetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage"); // , "--headless"
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WaitSettings.WAIT_2_SEC, TimeUnit.SECONDS);
    }
    /*
        Тест-сьют №1 по поиску ресторана через поля для ввода
                                                              */
    @Test
    @Step
    @DisplayName("Тест №1: Поиск программы из главной страницы")
    public  void  t1_1_searchProgram(){
        driver.get(UrlSettings.SI_MAIN_PAGE_URL);
        boolean checkSuccessSearch = new SIMainPage(driver)
                .SearchProgram()
                        .checkSuccessSearch();
        MatcherAssert.assertThat(Asserts.NAME_PROGRAM_FDM, checkSuccessSearch);
        String getNameProgram = new FdmSearchPage(driver) // А теперь выведу текст успешного завершения теста с наименованием найденной проги
                .getNameProgram();
        System.out.println("Тест №1 прошел успешно, страницы открылась, программа " + getNameProgram + " найдена");
    }
}
