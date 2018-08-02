package test.java.com.MortyraSky.pages;

import test.java.com.MortyraSky.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 26.07.2018.
 */
public class MarketSortPage {

    public MarketSortPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;
    final String nameAttributeElement = "class";
    String attributeBeforeSort, attributeAfterSort;

    //ArrayList<String> stringPriceItems = new ArrayList<String>();
    ArrayList<Integer> priceItems = new ArrayList<Integer>();

    @FindBy (xpath = "//*[@class='n-filter-block_pos_left i-bem']/descendant::div[3]/a")
    private WebElement sortByPriceLink;

    @FindBy (xpath = "//*[@class='n-snippet-card2__main-price']")
    private List<WebElement> sortedPrice;

    // @FindBy (xpath = "//*[@class='n-filter-block_pos_left i-bem']/descendant::div[3]") проверить с этим
    @FindBy (xpath = "//*[@class='n-filter-block_pos_left i-bem']/descendant::div[3]")
    private WebElement sortByPrice;


    public void sortByPrice(){
        int i = 0,time = 2;

        attributeBeforeSort = getAttributeElement(sortByPrice);
        sortByPriceLink.click();
        //BaseTest.waitForElements(time);
        //BaseTest.waitForElement(By.xpath("//*[@class='n-snippet-card2__main-price']"));
        BaseTest.waitForElements(time);
        System.out.println("Количество отсортированных элементов по цене : " + sortedPrice.size() );

        if (!priceItems.isEmpty())
            priceItems.clear();

        for(WebElement e : sortedPrice) {

            System.out.println(e.getText());
            String strThousand = e.getText().substring(0,1) + e.getText().substring(2,5);
            //System.out.println("Перевод : " + strThousand);
            priceItems.add(Integer.parseInt(strThousand));
            //System.out.println("Результат в числовом варианет: " + priceItems.get(i));
            i++;
        }
        //result = isSortByPrice(attributeBeforeSort, attributeAfterSort);
        //return isSortByPrice(attributeBeforeSort, attributeAfterSort);

        //return true;
        /*
        // рабочий вариант клика по нужному элементу, использовать для добавления в сравнение!!!!!!!!!!!!!!!!!!!!!
        sortedPrice.get(1).click();
        */
    }

    public String getAttributeElement(WebElement element){
        System.out.println("Attribute by class: " + element.getAttribute("class"));
        String attributeElement = element.getAttribute(nameAttributeElement);
        return attributeElement;

    }

    public boolean isSortByPrice(){
        attributeAfterSort = getAttributeElement(sortByPrice);
        if (attributeBeforeSort.equals(attributeAfterSort))
            return false;
        return true;
    }

    public boolean isSortedItemsByPrice(){
        boolean result = true;

        for (int i = 0; i < priceItems.size() - 1; i++){
            if (priceItems.get(i) <= priceItems.get(i+1))
                result = true;
            else{
                result = false;
                break;
            }
        }
        return result;
    }
}
