package PageObject;

import PageObject.SearchPages.FdmSearchPage;
import WebDriverFactory.WaitSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    //Смоук - кейс №1
    public FdmSearchPage SearchProgram() {
        driver.findElement(inputSearchOnSI).sendKeys("fdm");
        driver.findElement(desiredOption).isDisplayed();
        driver.findElement(desiredOption).click();
        return new FdmSearchPage(driver);
    }


    /*
            Блок для баннеров
                                    */

    //Смоук - кейс №2
    private final By btnSwipeLeft = By.xpath("/html/body/div[1]/div/div[2]/div[2]/span[1]");
    private final By btnSwipeRight = By.xpath("/html/body/div[1]/div/div[2]/div[2]/span[2]");

    //Ассерт для ожидания и проверки смены баннера
    private final By bannerOneActive = By.xpath("/html/body/div[1]/div/div[2]/div[2]/span[@class = 'swiper-pagination-bullet swiper-pagination-bullet-active' and @aria-label = 'Go to slide 1']");
    private final By bannerTwoActive = By.xpath("/html/body/div[1]/div/div[2]/div[2]/span[@class = 'swiper-pagination-bullet swiper-pagination-bullet-active' and @aria-label = 'Go to slide 2']");

    //Баннеры сами переключаются, соответственно нужно было писать условия для стабильности. Плюс ко всему загрузка страницы всегда проходит по разному
    public void advChange() {
        new WebDriverWait(driver,WaitSettings.WAIT_5_SEC);
        if (driver.findElement(bannerTwoActive).isDisplayed()) {
            driver.findElement(btnSwipeLeft).click();

            new WebDriverWait(driver,WaitSettings.WAIT_5_SEC);
            if (driver.findElement(bannerOneActive).isDisplayed()) {
                driver.findElement(btnSwipeRight).click();
            }
            System.out.println("Тест кейс №2: Прошел успешно! Баннеры переключаются");

        } else {
            System.out.println("Баннеры не переключатся");
        }
        new SIMainPage(driver);
    }

    //Смоук - кейс №3
    //Ассерты для проверок имеющихся кнопок в листе и методы для этого
    private final By textMiBtn = By.xpath("/html/body/header/div[1]/div/div[4]/div[1]/div[2]/nav/a[1]");
    private final By textSiBtn = By.xpath("/html/body/header/div[1]/div/div[4]/div[1]/div[2]/nav/a[2]");

    public String getMiTextBtn() {
        return driver.findElement(textMiBtn).getText();
    }

    public String getSiTextBtn() {
        return driver.findElement(textSiBtn).getText();
    }

    public MIMainPage goToMIPage() {
        driver.findElement(openLi).click();
        String siText = "Windows";
        String miText = "Mac";
        if (getMiTextBtn().equals(miText) && getSiTextBtn().equals(siText)) {
            driver.findElement(btnGoMI).click();

        } else {
            System.out.println("Тест кейс №3 провалился!");
        }
        driver.findElement(btnGoMI).click();
        return new MIMainPage(driver);
    }


}
