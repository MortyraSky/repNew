package test.java.com.MortyraSky.pages;

import test.java.com.MortyraSky.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MarketComparePage {

    // a[href*='/compare'] btn compare

    public MarketComparePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    ArrayList<String> toCompare = new ArrayList<String>();

    @FindBy(xpath = "//*[@class='n-snippet-card2__toolbar']/descendant::div/i[1]")
    private List<WebElement> addToCompare;

    @FindBy(xpath = "//*[@class='popup-informer__content']/descendant::div/a")
    private WebElement compareBtn;

    @FindBy(css = ".n-snippet-card2__title")
    private List<WebElement> titleItems;

    @FindBy(css = "a[class='n-compare-head__name link']")
    private List<WebElement> titleItemsInCompare;

    // a[class='n-compare-head__name link']

    @FindBy(xpath = "//*[@class='n-compare-content__line i-bem n-compare-content__line_js_inited']/div")
    private List<WebElement> countCompareItems;

    @FindBy(css = ".n-compare-toolbar__action")
    private WebElement removeItems;

    @FindBy(css = ".title_size_18")
    private WebElement afterRemoveText;

    @FindBy(css = "//*[@class='layout layout_type_maya n-page-compare']/descendant::div/a[2]") ////  a[@class='link'][@href='/'] a[contains(text(),'Перейти на главную')]  //*[@class='layout layout_type_maya n-page-compare']/descendant::div/a[2]
    private WebElement goMarket;

    public void getItemsToCompare(int firstItem, int secondItem) {
        int time = 2;
        Actions actions = new Actions(driver);
        actions.moveToElement(addToCompare.get(firstItem));
        addToCompare.get(firstItem).click();

        //BaseTest.waitForElement(By.xpath("//*[@class='n-snippet-card2__toolbar']/descendant::div/i[1]"));
        BaseTest.waitForElements(time);

        actions.moveToElement(addToCompare.get(secondItem));
        addToCompare.get(secondItem).click();

        toCompare.add(titleItems.get(firstItem).getText());
        toCompare.add(titleItems.get(secondItem).getText());

    }

    public boolean compareItems(){
        boolean resCompare = false;
        compareBtn.click();
        System.out.println(toCompare.size() + " : " + titleItemsInCompare.size());
        for (int i = 0;i < titleItemsInCompare.size(); i++)
            for (int j = toCompare.size() - 1;j >= 0; j--)
                if (titleItemsInCompare.get(i).getText().contains(toCompare.get(j)))
                    resCompare = true;

        return resCompare;
    }

    public int getCountCompareItems() {
        System.out.println("Elements in compare: " + countCompareItems.size());
        return countCompareItems.size();
    }

    public void removeItemsFromCompare() {
        removeItems.click();
    }

    public String getAfterRemoveText() {
        int time = 2;
        BaseTest.waitForElements(time);
        return afterRemoveText.getText();

    }
    public void goToMarket(By locator){

        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

    }
}
