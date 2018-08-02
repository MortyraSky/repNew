package test.java.com.MortyraSky.pages;

import test.java.com.MortyraSky.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


/**
 * Created by Admin on 25.07.2018.
 */
public class MarketPage {

    public MarketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public WebDriver driver;
    /*
    ArrayList<String> textLinkItems = new ArrayList<String>(); // если нужно будет сравнивать не количество(через .size()), а по тексту ссылки
    String url = driver.getCurrentUrl(); // для взятия адреса текущей страницы.
    */
    @FindBy(css = "a[href*='54425']")
    private WebElement menuItemComps;

    @FindBy(css = "a[href*='54545']")
    private WebElement subMenuItemPads;

    //@FindBy(xpath = "//*[@class='popup2__content']/descendant::div/span")
    //private WebElement locWindow; //

    @FindBy(css = ".n-snippet-card2")
    private List<WebElement> linksItem;

    @FindBy(css = "button[role='listbox']")
    private WebElement btnSelect;

    //@FindBy(xpath = "//*[@class='select__list']/div[1]")
    //private WebElement firstValueList;

    //@FindBy(xpath = "//*[@class='select__list']/div[2]")
    //private WebElement secondValueList;

    @FindBy(xpath = "//*[@class='select__list']/div")
    private List<WebElement> valuesListShowBy;


    public void selectCategoryPads() {

        //locWindow.click();

        int time = 1;
        Actions actions = new Actions(driver);
        actions.moveToElement(menuItemComps);
        actions.perform();

        //BaseTest.waitForElement(By.cssSelector("a[href*='54545']"));
        BaseTest.waitForElements(time);

        subMenuItemPads.click();

    }

    public void changeItemsCount(int count){
        String countTostring = Integer.toString(count);
        //System.out.println("Int transfer to String: " + countTostring);

        //BaseTest.waitForElement(By.cssSelector("a[href*='54545']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(btnSelect);
        btnSelect.click();
        //System.out.println("Rjkbxtcnjd ^ :" + valuesListShowBy.size() + ": value 0 is:" + valuesListShowBy.get(0).getText());

        for (int i = 0; i<valuesListShowBy.size(); i++){
            if (valuesListShowBy.get(i).getText().contains(countTostring)){
                valuesListShowBy.get(i).click();
            }
        }

    }

    public int getItemsCount(){

        int time = 3;
        /*
        //changeItemsCount();

        if(count == 12)
            firstValueList.click();

        else if(count == 48)
            secondValueList.click();
        */

        BaseTest.waitForElements(time);
        //BaseTest.waitForElementt(By.cssSelector(".n-snippet-card2"));

        //System.out.println("Количество показанных элементов на странице, выбрав отображение по " +count+ " элементов: " + linksItem.size());
        return linksItem.size();

    }

    public void navigate(String URL) {
        driver.get(URL);
        System.out.println("Переход на: " + URL);

    }


}
