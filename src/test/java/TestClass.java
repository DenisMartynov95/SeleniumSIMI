import Asserts.Asserts;
import PageObject.MIMainPage;
import PageObject.SIMainPage;
import PageObject.SearchPages.FdmSearchPage;
import WebDriverFactory.UrlSettings;
import WebDriverFactory.WaitSettings;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
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
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless"); // , "--headless"
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WaitSettings.WAIT_2_SEC, TimeUnit.SECONDS);
    }


        // Смоук - кейс №1 по поиску ресторана через поля для ввода
    @Test
    @Step
    @DisplayName("Тест №1: Поиск программы из главной страницы")
    public  void  t1_searchProgram(){
        driver.get(UrlSettings.SI_MAIN_PAGE_URL);
        boolean checkSuccessSearch = new SIMainPage(driver)
                .SearchProgram()
                        .checkSuccessSearch();
        MatcherAssert.assertThat(Asserts.NAME_PROGRAM_FDM, checkSuccessSearch);
        String getNameProgram = new FdmSearchPage(driver) // А теперь выведу текст успешного завершения теста с наименованием найденной проги
                .getNameProgram();
        System.out.println("Тест №1 прошел успешно, страницы открылась, программа " + getNameProgram + " найдена");
        driver.quit();
    }

      //Смоук - кейс  №2 работоспособность кнопок смены баннера
    @Test
    @Step
    @DisplayName("Тест №2: Проверка работы баннеров на сервере SI")
    public void t2_advChange(){
        driver.get(UrlSettings.SI_MAIN_PAGE_URL);
        new SIMainPage(driver).advChange();  // Не присваиваем возвращаемый объект
        System.out.println("Тест №2 прошел успешно, баннеры переключаются успешно!");
        driver.quit();
    }

    //Смоук - кейс  №3 переход на сайт MI через лист
    @Test
    @Step
    @DisplayName("Тест №3: Проверка работы перехода на сайт MI")
    public void t3_redirectOnMIPage(){
        driver.get(UrlSettings.SI_MAIN_PAGE_URL);
        boolean checkSuccessRedirect = new SIMainPage(driver)
                .goToMIPage()
                .checkSuccessRedirect();
        MatcherAssert.assertThat(Asserts.NAME_MI_PAGE, checkSuccessRedirect);
        String getNamePage = new MIMainPage(driver)
                .getNameMiPage();
        System.out.println("Тест №3 прошел успешно, страница открылась " + getNamePage + " найдена");
        driver.quit();
    }

    //Смоук - кейс  №4 переход на сайт SI через лист
    @Test
    @Step
    @DisplayName("Тест №4: Проверка работы перехода на сайт SI")
    public void t4_redirectOnSIPage(){
        driver.get(UrlSettings.MI_MAIN_PAGE_URL);
        boolean checkSuccessRedirect = new MIMainPage(driver)
                .goToSIPage()
                .checkSuccessRedirect();
        MatcherAssert.assertThat(Asserts.NAME_SI_PAGE, checkSuccessRedirect);
        String getNamePage = new MIMainPage(driver)
                .getNameMiPage();
        System.out.println("Тест №4 прошел успешно, страница открылась " + getNamePage + " найдена");
        driver.quit();
    }

    //Смоук - кейс №5 проверка работы редиректов на страницы посредством основных кнопок сервиса
    @Test
    @Step
    @DisplayName("Тест №5: Проверка работы редиректов между сервисами")
    public void t5_redirectBetweenPages(){
        driver.get(UrlSettings.SI_MAIN_PAGE_URL);
        new SIMainPage(driver)
                .goToMiPageService()
                .goToSiService()
                .checkSuccessRedirectBetweenServices();
        driver.quit();
    }
    //Смоук-тест №6 Работоспособность кнопки top
    @Test
    @Step
    @DisplayName("Тест №6: Проверка работы кнопки Наверх на странице SI")
    public void t6_btnGoTop() {
        driver.get(UrlSettings.SI_MAIN_PAGE_URL);
        new SIMainPage(driver)
                .checkOperabilityBtnTop();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }


}
