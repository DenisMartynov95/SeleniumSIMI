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
    private final By btnSwipeLeft = By.xpath("/html/body/div[1]/div/div[2]/div[1]/div[@class = 'bx-controls-direction']/a[@class = 'swiper-button-prev bx-prev']");
    private final By btnSwipeRight = By.xpath("/html/body/div[1]/div/div[2]/div[1]/div[@class = 'bx-controls-direction']/a[@class = 'swiper-button-next bx-next']");

    //Ассерт для ожидания и проверки смены баннера
    private final By bannerOneActive = By.xpath("/html/body/div[1]/div/div[2]/div[2]/span[@class = 'swiper-pagination-bullet swiper-pagination-bullet-active' and @aria-label = 'Go to slide 1']");
    private final By bannerTwoActive = By.xpath("/html/body/div[1]/div/div[2]/div[2]/span[@class = 'swiper-pagination-bullet swiper-pagination-bullet-active' and @aria-label = 'Go to slide 2']");

    //Реализация логики внутри одного метода, чтобы не распылять по классам функционал
    public void advChange() {
        if (driver.findElement(bannerOneActive).isEnabled()) {
            driver.findElement(btnSwipeRight).click();
            if (driver.findElement(bannerTwoActive).isEnabled()) {
                driver.findElement(btnSwipeLeft).click();
            }
            if (driver.findElement(bannerOneActive).isEnabled()) {
                System.out.println("Тест кейс №2: Прошел успешно! Баннеры переключаются");
            }
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
