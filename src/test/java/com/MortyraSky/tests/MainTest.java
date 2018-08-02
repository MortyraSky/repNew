package test.java.com.MortyraSky.tests;

import org.openqa.selenium.WebDriver;
import test.java.com.MortyraSky.pages.MarketComparePage;
import test.java.com.MortyraSky.pages.MarketPage;
import test.java.com.MortyraSky.pages.MarketSortPage;
import test.java.com.MortyraSky.pages.YaMainPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Admin on 25.07.2018.
 * после перехода вылазит всплывающее окно
 * разобраться как с ним работать!
 */

public class MainTest extends BaseTest {
    public MarketPage marketPage;
    public MarketSortPage marketSortPage;
    public MarketComparePage marketComparePage;
    public YaMainPage yaMainPage;


    final String URL = "https://market.yandex.ru";
    final String MATCHINTEXT = "Товаров нет";
    final String GOMARKETMAINPAGE = "//a[contains(text(),'Перейти на главную')]"; //  "//*[@class='layout layout_type_maya n-page-compare']/descendant::div/a[2]"
    final String[] TITLEPAGES = {"","видео", "Картинки", "Новости", "Карты", "Маркет", "Переводчик", "Музыка"};

    final int TWELVEITEMS = 12;
    final int FORTYEIGHTITEMS = 48; //forty eight

    final int FIRSTITEMINLIST = 0;
    final int SECONDITEMINLIST = 1;

    @Test(groups = { "include-test-one" })
    public void marketTest1(){

        marketPage = new MarketPage(driver);
        int countItems;

        marketPage.navigate(URL);
        marketPage.selectCategoryPads();
        marketPage.changeItemsCount(TWELVEITEMS);
        countItems = marketPage.getItemsCount();
        Assert.assertEquals(countItems,TWELVEITEMS);
        marketPage.changeItemsCount(FORTYEIGHTITEMS);
        countItems = marketPage.getItemsCount();
        Assert.assertEquals(countItems, FORTYEIGHTITEMS);

    }

    @Test(groups = { "include-test-two" })
    public void marketTest2(){
        marketSortPage = new MarketSortPage(driver);
        boolean resultSort, usedSortByPryce;

        marketSortPage.sortByPrice();
        usedSortByPryce = marketSortPage.isSortByPrice();
        Assert.assertTrue(usedSortByPryce);
        resultSort = marketSortPage.isSortedItemsByPrice();
        Assert.assertTrue(resultSort);
    }

    @Test(groups = { "include-test-three" })
    public void marketTest3(){
        marketComparePage = new MarketComparePage(driver);
        String takeTitle;
        boolean resCompareItems;

        marketComparePage.getItemsToCompare(FIRSTITEMINLIST, SECONDITEMINLIST);
        resCompareItems = marketComparePage.compareItems();
        Assert.assertTrue(resCompareItems);
        marketComparePage.removeItemsFromCompare();
        takeTitle = marketComparePage.getAfterRemoveText();
        Assert.assertTrue(takeTitle.contains(MATCHINTEXT));
        marketComparePage.goToMarket(By.xpath(GOMARKETMAINPAGE));

    }
    @Test(groups = { "include-test-four" })
    public void marketTest4(){
        yaMainPage = new YaMainPage(driver);
        boolean resultToNavigate;

        yaMainPage.goToYa();
        resultToNavigate = yaMainPage.goToPagesAndVerifyPages(TITLEPAGES);
        Assert.assertTrue(resultToNavigate);

    }

}
