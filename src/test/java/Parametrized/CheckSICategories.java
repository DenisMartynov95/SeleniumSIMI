package Parametrized;

import WebDriverFactory.UrlSettings;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@RunWith(Parameterized.class)
public class CheckSICategories {


    /*
        БЛОК КОДА связанный с локаторами, где должен храниться локатор для КЛИКА, чтобы открыть ответ на вопрос
                                                                                                                     */
    public static final By CATEGORY1 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[1]/a[@class = 'cat_201']");
    public static final By CATEGORY2 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[2]/a[@class = 'cat_243']");
    public static final By CATEGORY3 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[3]/a[@class = 'cat_288']");
    public static final By CATEGORY4 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[4]/a[@class = 'cat_221']");
    public static final By CATEGORY5 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[5]/a[@class = 'cat_317']");
    public static final By CATEGORY6 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[6]/a[@class = 'cat_228']");
    public static final By CATEGORY7 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[7]/a[@class = 'cat_208']");
    public static final By CATEGORY8 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[8]/a[@class = 'cat_260']");
    public static final By CATEGORY9 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[9]/a[@class = 'cat_236']");
    public static final By CATEGORY10 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[10]/a[@class = 'cat_216']");
    public static final By CATEGORY11 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[11]/a[@class = 'cat_284']");
    public static final By CATEGORY12 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[12]/a[@class = 'cat_307']");
    public static final By CATEGORY13 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[13]/a[@class = 'cat_277']");
    public static final By CATEGORY14 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[14]/a[@class = 'cat_266']");
    public static final By CATEGORY15 = By.xpath(".//body/div[1]/aside/div/nav[@class='nav_cats']//li[15]/a[@class = 'cat_314']");

    /*
        БЛОК КОДА связанный с ожидаемым текстом
                                                           */
    public static final String ASSERT_CATEGORY1 = "Audio & Video";
    public static final String ASSERT_CATEGORY2 = "Games";
    public static final String ASSERT_CATEGORY3 = "System Tools";
    public static final String ASSERT_CATEGORY4 = "Design & Photo";
    public static final String ASSERT_CATEGORY5 = "Mobile Phone Utilities";
    public static final String ASSERT_CATEGORY6 = "Developer Tools";
    public static final String ASSERT_CATEGORY7 = "Business";
    public static final String ASSERT_CATEGORY8 = "Internet Tools";
    public static final String ASSERT_CATEGORY9 = "Education";
    public static final String ASSERT_CATEGORY10 = "Communication";
    public static final String ASSERT_CATEGORY11 = "Antivirus & Security";
    public static final String ASSERT_CATEGORY12 = "Theming";
    public static final String ASSERT_CATEGORY13 = "Productivity";
    public static final String ASSERT_CATEGORY14 = "Lifestyle";
    public static final String ASSERT_CATEGORY15 = "General";


    /*
                         БЛОК КОДА с параметризацией
                                                                      */
    private WebDriver driver;
    private final By locator;
    private final String expected;

    public CheckSICategories(By locator, String expected) {
        this.locator = locator;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {CATEGORY1, ASSERT_CATEGORY1},
                {CATEGORY2, ASSERT_CATEGORY2},
                {CATEGORY3, ASSERT_CATEGORY3},
                {CATEGORY4, ASSERT_CATEGORY4},
                {CATEGORY5, ASSERT_CATEGORY5},
                {CATEGORY6, ASSERT_CATEGORY6},
                {CATEGORY7, ASSERT_CATEGORY7},
                {CATEGORY8, ASSERT_CATEGORY8},
                {CATEGORY9, ASSERT_CATEGORY9},
                {CATEGORY10, ASSERT_CATEGORY10},
                {CATEGORY11, ASSERT_CATEGORY11},
                {CATEGORY12, ASSERT_CATEGORY12},
                {CATEGORY13, ASSERT_CATEGORY13},
                {CATEGORY14, ASSERT_CATEGORY14},
                {CATEGORY15, ASSERT_CATEGORY15},
        };
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");  //, "--headless"
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    static int number = 0;
    @Test
    public void checkSICategories() {
        driver.get(UrlSettings.SI_MAIN_PAGE_URL);

        // Ожидание появления элемента
        WebElement element = (new WebDriverWait(driver, 1)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (!element.isDisplayed()) {
            System.out.println("Тест кейс провалился, не обнаружен элемент");
        } else {

            String actual = element.getText().trim();
            Assert.assertEquals(expected, actual);
            System.out.println("ОЖИДАЕМЫЙ: " + expected + " равен ФАКТИЧЕСКОМУ: " + locator.toString());
            number = number + 1;
        }
        System.out.println("Параметризированный тест прошел успешно " + number + " из 15");
    }

    @After
    public void shutDown() {
        driver.quit();
    }
}
