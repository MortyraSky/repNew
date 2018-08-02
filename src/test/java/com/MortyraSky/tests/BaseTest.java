package test.java.com.MortyraSky.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 25.07.2018.
 */
public class BaseTest {

    public static WebDriver driver;

    @BeforeClass
    public static void setDriver(){
        System.setProperty("webdriver.chrome.driver", "./src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.get("https://market.yandex.ru");
        System.out.println("Запуск метода сетДрайвер во время запуска маин тест");

    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void waitForElement(By locator){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementt(By locators){
        (new WebDriverWait(driver, 12))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locators));
    }
    public static void waitForElements(int time) {

        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public static void tearDown() {

        driver.quit();
    }
}
