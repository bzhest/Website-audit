import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
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
        sites.add("http://www.theeuropeanmasters.com/");
        sites.add("http://www.inspectacargezina.co.za/");
    }


    public static void main(String[] args) {


        //WebDriver driver = new ChromeDriver();
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://tools.pingdom.com/#!/");
        sites.add("http://www.theeuropeanmasters.com/");
        sites.add("http://www.inspectacargezina.co.za/");
        PingdomPages pp = PageFactory.initElements(driver, PingdomPages.class);//new PingdomPages();
        for (String s:sites){
            wait.until(ExpectedConditions.visibilityOf(pp.getLocationDropdownDiv()));
            pp.inputText(pp.getUrlInput(), s);
            pp.clickOnLocationDropdown();
            pp.clickOnLocationUSA();
            pp.clickStartTest();
            /*if (pp.getLinkTryAgain().isDisplayed()){
                pp.getLinkTryAgain().click();
            }*/
            wait.until(ExpectedConditions.visibilityOf(pp.getPerformanceGrade()));
            String performanceGrade = pp.getPerformanceGrade().getAttribute("textContent").replaceAll("\\D+","");//("innerHTML");
            System.out.println(performanceGrade);
            String pageSize = pp.getMb().getAttribute("textContent").replaceAll("[^0-9.]", "");
            System.out.println(pageSize);
             List <WebElement> partLoadings = pp.getSec().get(0).findElements(By.xpath(".//*"));
            System.out.println(partLoadings.size());
            int totalLoadingTime = 0;
            for(WebElement w:partLoadings){
                int loadingTime = Integer.parseInt(w.getAttribute("textContent").replaceAll("\\D+",""));
                totalLoadingTime+=loadingTime;
            }
            System.out.println(totalLoadingTime);
            double seconds = totalLoadingTime/1000.0;
            System.out.println(seconds);

        }


    }
}
