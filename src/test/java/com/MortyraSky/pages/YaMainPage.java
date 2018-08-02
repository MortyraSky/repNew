package test.java.com.MortyraSky.pages;

import test.java.com.MortyraSky.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
/*
Проверить навигацию. Открыть яндекс,
нажать на видео, картинки, новости, карты, маркет, переводчик, музыка.
 */

public class YaMainPage {
    public YaMainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;

    ArrayList<String> currentURL = new ArrayList<String>();
    ArrayList<String> hrefElements = new ArrayList<String>();
    ArrayList<String> currentTitle = new ArrayList<String>();

    @FindBy(css = "a.logo:nth-child(1)")
    private WebElement linkTitle;


    public String getAttributeHref(WebElement element){
        String href = element.getAttribute("href");
        System.out.println("Href of element " + href);
        return href;
    }


    public void goToYa(){
        System.out.println("Переход на яндекс, кликнув по: " + linkTitle.getText());
        linkTitle.click();
    }

    public boolean goToPagesAndVerifyPages(String[] pageTitle){
        /*
        for (int i=1; i < 8; i++){
        resultToNavigate = yaMainPage.goToPage(By.xpath("//div[@role='navigation']/a["+i+"]"), TITLEPAGES[i]);
        System.out.println("Result navigate to pages: " + resultToNavigate);
         */
        boolean addresCompareRes, titleCompareRes, resuleNavigate = false;
        //int time = 3;

        for (int i = 1; i < 8; i++){
            int j = i-1;
            //int time = 1;
            WebElement element = driver.findElement(By.xpath("//div[@role='navigation']/a["+i+"]"));
            hrefElements.add(getAttributeHref(element));
            element.click();

            //BaseTest.waitForElements(time);
            currentURL.add(driver.getCurrentUrl());
            currentTitle.add(driver.getTitle());
            addresCompareRes= addressesMatched(hrefElements.get(j), currentURL.get(j));
            titleCompareRes = titleMatched(pageTitle[i], currentTitle.get(j));

            if(addresCompareRes == titleCompareRes == true)
                resuleNavigate = true;
            else{
                resuleNavigate =false;
                break;
            }
            driver.navigate().back();

        }
        return resuleNavigate;

    }

    public boolean addressesMatched(String href, String currentURL){
        return currentURL.contains(href);
    }

    public boolean titleMatched(String pageTitle, String currentTitle){
        return currentTitle.contains(pageTitle);
    }
}
