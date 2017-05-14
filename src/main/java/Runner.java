import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Andrey on 13.05.2017.
 */
public class Runner {
    public static List<String> sites = new ArrayList<String>();

    public Runner() {
        sites.add("https://tools.pingdom.com/#!/AVVKP/http://www.theeuropeanmasters.com/");
        sites.add("https://tools.pingdom.com/#!/XY9sK/http://www.inspectacargezina.co.za/");
    }


    public static void main(String[] args) {


        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://tools.pingdom.com/#!/XY9sK/http://www.inspectacargezina.co.za/");
        PingdomPages pp = PageFactory.initElements(driver, PingdomPages.class);//new PingdomPages();
        wait.until(ExpectedConditions.visibilityOf(pp.getLocationDropdownDiv()));
        pp.clickOnLocationDropdown();
        pp.clickOnLocationUSA();
        /*WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        for(int i = 0; i<sites.size(); i++){
            driver.get(sites.get(i));

        }*/


    }
}
