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
    private final ArrayList<String> pagesLocators = new ArrayList<>();
    public ArrayList<String> getPagesLocators() {
        pagesLocators.add(0,".//nav/ul/li[1]/a[@class = 'cat_327' and text() = 'AI']"); // AI Page
        pagesLocators.add(1,".//a[starts-with(text(),  'Audio')]"); // Audio & Video Page
        pagesLocators.add(2,".//a[starts-with(text(), 'Games')]"); // Games Page
        pagesLocators.add(3,".//a[text() = 'System Tools']"); // System Tools Page
        pagesLocators.add(4,".//a[starts-with(text(), 'Design')]"); // "Design & Photo" Page
        pagesLocators.add(5,".//a[starts-with(text(), 'Mobile Phone')]"); // "Mobile Phone Utilities" Page
        pagesLocators.add(6,".//a[starts-with(text(), 'Developer Too')]"); // "Developer Tools" Page
        pagesLocators.add(7,".//a[starts-with(text(), 'Busi')]"); // "Business" Page
        pagesLocators.add(8,".//a[starts-with(text(), 'Internet')]"); // "Internet Tools" Page
        pagesLocators.add(9,".//a[starts-with(text(), 'Edu')]"); // "Education" Page
        pagesLocators.add(10,".//a[starts-with(text(), 'Comm')]"); // "Communication" Page
        pagesLocators.add(11,".//a[starts-with(text(), 'Anti')]"); // "Antivirus & Security" Page
        pagesLocators.add(12,".//a[starts-with(text(), 'The')]"); // "Theming" Page
        pagesLocators.add(13,".//a[starts-with(text(), 'Prod')]"); // "Productivity" Page
        pagesLocators.add(14,".//a[starts-with(text(), 'Life')]"); // "Lifestyle" Page
        pagesLocators.add(15,".//a[starts-with(text(), 'Gener')]"); // "General" Page
        return pagesLocators;
    }

    // Лист для поиска ассертовых локаторов на открытых страницах категорий
    private final ArrayList<String> assertsNamePages = new ArrayList<>();
    public ArrayList<String> getAssertsNamePages() {
        pagesLocators.add(0,".//nav/ul/li[1]/a[@class = 'cat_327' and text() = 'AI']"); // AI Page
        pagesLocators.add(1,".//a[starts-with(text(),  'Audio')]"); // Audio & Video Page
        pagesLocators.add(2,".//a[starts-with(text(), 'Games')]"); // Games Page
        pagesLocators.add(3,".//a[text() = 'System Tools']"); // System Tools Page
        pagesLocators.add(4,".//a[starts-with(text(), 'Design')]"); // "Design & Photo" Page
        pagesLocators.add(5,".//a[starts-with(text(), 'Mobile Phone')]"); // "Mobile Phone Utilities" Page
        pagesLocators.add(6,".//a[starts-with(text(), 'Developer Too')]"); // "Developer Tools" Page
        pagesLocators.add(7,".//a[starts-with(text(), 'Busi')]"); // "Business" Page
        pagesLocators.add(8,".//a[starts-with(text(), 'Internet')]"); // "Internet Tools" Page
        pagesLocators.add(9,".//a[starts-with(text(), 'Edu')]"); // "Education" Page
        pagesLocators.add(10,".//a[starts-with(text(), 'Comm')]"); // "Communication" Page
        pagesLocators.add(11,".//a[starts-with(text(), 'Anti')]"); // "Antivirus & Security" Page
        pagesLocators.add(12,".//a[starts-with(text(), 'The')]"); // "Theming" Page
        pagesLocators.add(13,".//a[starts-with(text(), 'Prod')]"); // "Productivity" Page
        pagesLocators.add(14,".//a[starts-with(text(), 'Life')]"); // "Lifestyle" Page
        pagesLocators.add(15,".//a[starts-with(text(), 'Gener')]"); // "General" Page
        return assertsNamePages;
    }



//    private final By AI = By.xpath(".//nav/ul/li[1]/a[@class = 'cat_327' and text() = 'AI']");
//    private final By necessaryNameAI = By.xpath(".//section[1]/div/div/h1");
//
//    private final By AudioAndVideo = By.xpath(".//a[starts-with(text(),  'Audio')]");
//    private final By audioAndVideoLocator = By.xpath("/html/body/div[1]/div/section[1]/div/div[1]/h1");
//
//    private final By Games = By.xpath(".//a[starts-with(text(), 'Games')]");
//    private final By gamesLocator = By.xpath("/html/body/div[1]/div/section[1]/div/div[1]/h1");
//
//
//    private final By SystemTools= By.xpath(".//div[1]/aside/div/nav/ul/li[4]/a[text() = 'System Tools']");
//    private final By DesignAndPhoto = By.xpath(".//div[1]/aside/div/nav/ul/li[5]/a[starts-with(text(), 'Design')]");
//    private final By MobilePhone = By.xpath(".//div[1]/aside/div/nav/ul/li[6]/a[starts-with(text(), 'Mobile Phone')]");
//    private final By DeveloperTools = By.xpath(".//div[1]/aside/div/nav/ul/li[7]/a[starts-with(text(), 'Developer Too')]");
//    private final By Business= By.xpath(".//div[1]/aside/div/nav/ul/li[8]/a[starts-with(text(), 'Busi')]");
//    private final By InternetTools = By.xpath(".//div[1]/aside/div/nav/ul/li[9]/a[starts-with(text(), 'Internet')]");
//    private final By Education = By.xpath(".//div[1]/aside/div/nav/ul/li[10]/a[starts-with(text(), 'Edu')]");
//    private final By Communication = By.xpath(".//div[1]/aside/div/nav/ul/li[11]/a[starts-with(text(), 'Comm')]");
//    private final By AntivirusAndSecurity = By.xpath(".//div[1]/aside/div/nav/ul/li[12]/a[starts-with(text(), 'Anti')]");
//    private final By Theming = By.xpath(".//div[1]/aside/div/nav/ul/li[13]/a[starts-with(text(), 'The')]");
//    private final By Productivity = By.xpath(".//div[1]/aside/div/nav/ul/li[14]/a[starts-with(text(), 'Prod')]");
//    private final By Lifestyle = By.xpath(".//div[1]/aside/div/nav/ul/li[15]/a[starts-with(text(), 'Life')]");
//    private final By General = By.xpath(".//div[1]/aside/div/nav/ul/li[16]/a[starts-with(text(), 'Gener')]");

    public  void checkCategoriesLinks() {
        for (int l = 0; l < pagesLocators.size(); l++) {
            driver.findElement(By.id(getPagesLocators().get(l))).click(); // Возможно тут БАГ
            if ()
        }

        driver.findElement(AI).click();
        if (driver.findElement(necessaryNameAI).getText().equals(Asserts.namesPagesAsserts.get(0))) {
            System.out.println("Страница AI открыта успешно!");
        } else {
            System.out.println("!!! Страница AI не открылась");
        }

        driver.findElement(AudioAndVideo).click();
        if (driver.findElement(audioAndVideoLocator).getText().equals(Asserts.namesPagesAsserts.get(1))
        ) {
            System.out.println("Страница Audio & Video открыта успешно!");
        } else {
            System.out.println("!!! Страница Audio & Video не открыта!");
        }

        driver.findElement(Games).click();
        if (driver.findElement(gamesLocator).getText().equals(Asserts.namesPagesAsserts.get(2))) {
            System.out.println("Страница Games открыта успешно!");
        } else {
            System.out.println("!!! Страница Games не открыта!");
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

    public boolean checkSuccessRedirect() {
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

    public MIMainPage goToMiPageService() {
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

    public void checkSuccessRedirectBetweenServices() {
        try {
            String lambda = driver.findElement(checkNamePage).getAttribute("alt");
            if (Objects.equals(lambda, needNamePage)) {
                System.out.println("Тест №5 прошел успешно, адрессация и обратная адрессация работают корректно!");
            } else {
                System.out.println("Тест №5 завершился не удачно, возможно проблема с локаторами:");
                System.out.println("Локаторы для логгирования они должны быть одинаковыми: " + lambda + "\\" + needNamePage);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Смоук - кейс №6, работоспособность кнопки Top
    private final By btnGoTop = By.cssSelector("#float_top");
    //Локатор для скролла вниз
    private final By footerLocator = By.cssSelector("body > div.wrapper-content > div > section.questions > h2");
    private final By headLocator = By.cssSelector("#search_inp");

    public void checkOperabilityBtnTop() {
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

