package PageObject;

import Asserts.Asserts;
import PageObject.SearchPages.FdmSearchPage;
import WebDriverFactory.WaitSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

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
        try {
            driver.findElement(inputSearchOnSI).sendKeys("fdm");
            driver.findElement(desiredOption).isDisplayed();
            driver.findElement(desiredOption).click();
            return new FdmSearchPage(driver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
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

    //Переменные для логирования
    private String line1;
    private String line2;

    //Баннеры сами переключаются, соответственно нужно было писать условия для стабильности. Плюс ко всему загрузка страницы всегда проходит по разному
    public void advChange() {
        try {
            new WebDriverWait(driver, WaitSettings.WAIT_5_SEC).until(ExpectedConditions.visibilityOfElementLocated(btnSwipeRight));
            if (driver.findElement(bannerOneActive).isDisplayed() || driver.findElement(bannerTwoActive).isDisplayed()) {
                driver.findElement(btnSwipeRight).click();
                line1 = driver.findElement(bannerTwoActive).getAttribute("aria-label");
                driver.findElement(btnSwipeLeft).click();
                line2 = driver.findElement(bannerOneActive).getAttribute("aria-label");
                //Так как на фронте реализован рандомный таймаут для переключения, перезаписываю переменную 1, чтобы 100% проверить работу переключений
                driver.findElement(btnSwipeRight).click();
                line1 = driver.findElement(bannerTwoActive).getAttribute("aria-label");
            } else {
                System.out.println("Баннеры не переключатся");
            }
            System.out.println("Информация для логирования локаторов у теста №2: " + line1 + " " + line2);
            new SIMainPage(driver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

        /*
            Блок для кнопок категорий
                                         */

    // Локаторы для гиперссылок страниц категорий, чтобы переходить по ним
    private final List<By> categoryLinks = List.of(
            By.xpath("//nav/ul/li[1]/a[@class = 'cat_327' and text() = 'AI']"),
            By.xpath("//a[starts-with(text(), 'Audio')]"),
            By.xpath("//a[starts-with(text(), 'Games')]"),
            By.xpath("//a[text() = 'System Tools']"),
            By.xpath("//a[starts-with(text(), 'Design')]"),
            By.xpath("//a[starts-with(text(), 'Mobile Phone')]"),
            By.xpath("//a[starts-with(text(), 'Developer Too')]"),
            By.xpath("//a[starts-with(text(), 'Busi')]"),
            By.xpath("//a[starts-with(text(), 'Internet')]"),
            By.xpath("//a[starts-with(text(), 'Edu')]"),
            By.xpath("//a[starts-with(text(), 'Comm')]"),
            By.xpath("//a[starts-with(text(), 'Anti')]"),
            By.xpath("//a[starts-with(text(), 'The')]"),
            By.xpath("//a[starts-with(text(), 'Prod')]"),
            By.xpath("//a[starts-with(text(), 'Life')]"),
            By.xpath("//a[starts-with(text(), 'Gener')]")
    );

    // Лист для поиска ассертовых локаторов на открытых страницах категорий
    private final List<By> categoryHeaders = List.of(
            By.xpath("//h1[text() = 'AI']"),
            By.xpath("//h1[starts-with(text(),'Audio')]"),
            By.xpath("//h1[starts-with(text(),'Game')]"),
            By.xpath("//h1[starts-with(text(),'System')]"),
            By.xpath("//h1[starts-with(text(),'Design')]"),
            By.xpath("//h1[starts-with(text(),'Mobile')]"),
            By.xpath("//h1[starts-with(text(),'Devel')]"),
            By.xpath("//h1[starts-with(text(),'Busi')]"),
            By.xpath("//h1[starts-with(text(),'Internet')]"),
            By.xpath("//h1[starts-with(text(),'Education')]"),
            By.xpath("//h1[starts-with(text(),'Commu')]"),
            By.xpath("//h1[starts-with(text(),'Anti')]"),
            By.xpath("//h1[starts-with(text(),'Them')]"), // НА ЭТОМ ВАЛИТСЯ
            By.xpath("//h1[starts-with(text(),'Prod')]"),
            By.xpath("//h1[starts-with(text(),'Lifest')]"),
            By.xpath("//h1[starts-with(text(),'General')]")
    );

    public void checkCategoriesLinks() {
        for (int i = 0; i < categoryLinks.size(); i++) {
            driver.findElement(categoryLinks.get(i)).click();

            WebElement header = driver.findElement(categoryHeaders.get(i));
            String actual = header.getText();
            String expected = Asserts.namesPagesAsserts.get(i);


            if (actual.equals(expected)) {
                System.out.println("Страница " + Asserts.namesPagesAsserts.get(i) + " открыта успешно");
            } else {
                System.out.println("!!!Ошибка! Страница " + Asserts.namesPagesAsserts.get(i) + " не была открыта, либо локатор ошибочен");
            }
        }
    }






    /*
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

     */

        //Смоук - кейс №3 переход на сайт MI через лист
        //Ассерты для проверок имеющихся кнопок в листе и методы для этого
        private final By textMiBtn = By.xpath("/html/body/header/div[1]/div/div[4]/div[1]/div[2]/nav/a[1]");
        private final By textSiBtn = By.xpath("/html/body/header/div[1]/div/div[4]/div[1]/div[2]/nav/a[2]");

        public String getMiTextBtn () {
            return driver.findElement(textMiBtn).getText();
        }

        public String getSiTextBtn () {
            return driver.findElement(textSiBtn).getText();
        }

        public MIMainPage goToMIPage () {
            driver.findElement(openLi).click();
            String siText = "Windows";
            String miText = "Mac";
            try {
                if (getMiTextBtn().equals(miText) && getSiTextBtn().equals(siText)) {
                    // Логирую локатор успешного нахождения нужной кнопки
                    System.out.println("Логирую локатор для кнопки: " + driver.findElement(btnGoMI).getText());
                    driver.findElement(btnGoMI).click();

                } else {
                    System.out.println("Тест кейс №3 провалился!");
                }
                return new MIMainPage(driver);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

        //Смоук - кейс №4 ПРОДОЛЖЕНИЕ с обработкой ассерта
        private final By necessaryNamePage = By.cssSelector("body > div.wrapper-content > aside > div > div > span");

        public boolean checkSuccessRedirect () {
            try {
                new WebDriverWait(driver, 5).until(ExpectedConditions.textToBe(necessaryNamePage, "Windows"));
                return driver.findElement(necessaryNamePage).getText().contains(Asserts.NAME_SI_PAGE);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        //Смоук - кейс №5 проверка работы редиректов на страницы посредством основных кнопок сервиса
        private final By btnGoMIService = By.cssSelector("body > div.wrapper-content > aside > div > div > a");

        public MIMainPage goToMiPageService () {
            try {
                driver.findElement(btnGoMIService).click();
                return new MIMainPage(driver);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

        //ПРОДОЛЖЕНИЕ тест №5, после обратного редиректа, здесь уже задаю ассерты и в конечном итоге проверяю
        private final By checkNamePage = By.cssSelector("#top > div > a > img");
        private final String needNamePage = "Software Informer";

        public void checkSuccessRedirectBetweenServices () {
            try {
                String lambda = driver.findElement(checkNamePage).getAttribute("alt");
                if (Objects.equals(lambda, needNamePage)) {
                    System.out.println("Тест №5 прошел успешно, адрессация и обратная адрессация работают корректно!");
                } else {
                    System.out.println("Тест №5 завершился не удачно, возможно проблема с локаторами:");
                    System.out.println("Локаторы для логгирования они должны быть одинаковыми: " + lambda + "\\" + needNamePage);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        //Смоук - кейс №6, работоспособность кнопки Top
        private final By btnGoTop = By.cssSelector("#float_top");
        //Локатор для скролла вниз
        private final By footerLocator = By.cssSelector("body > div.wrapper-content > div > section.questions > h2");
        private final By headLocator = By.cssSelector("#search_inp");

        public void checkOperabilityBtnTop () {
            WebElement element = driver.findElement(footerLocator);
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                if (element.isDisplayed() && driver.findElement(btnGoTop).isDisplayed()) {
                    driver.findElement(btnGoTop).click();
                    driver.findElement(headLocator).isDisplayed();
                    System.out.println("Тест №6 прошел успешно! Скролл сработал");
                } else {
                    System.out.println("Скролл наверх не сработал");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Тест №6 провалился!");
            }
        }

        // Смоук - кейс №7, переход на категорию AI
    }


