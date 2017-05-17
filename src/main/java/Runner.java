import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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


        List<PingdomPages> pages = new ArrayList<>();
        WebDriver driver = new ChromeDriver();
        PingdomPages pp = PageFactory.initElements(driver, PingdomPages.class);
        VehicleDetailsPages vdp = PageFactory.initElements(driver, VehicleDetailsPages.class);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        String theeuropeanmastersVDurl = vdp.getVDurl(driver, "http://www.theeuropeanmasters.com/cars-for-sale.html",vdp.getTheeuropeanmasters());
        String inspectacargezinaVDurl = vdp.getVDurl(driver, "http://www.inspectacargezina.co.za/cars-for-sale.html",vdp.getInspectacargezina());
        String wallworktrucksVDurl = vdp.getVDurl(driver, "http://www.wallworktrucks.com/trucks-for-sale-inventory.html",vdp.getWallworktrucks());


        driver.get("https://tools.pingdom.com/#!/");

        sites.add("http://www.theeuropeanmasters.com/");
        sites.add("http://www.theeuropeanmasters.com/cars-for-sale.html");
        sites.add(theeuropeanmastersVDurl);
        sites.add("http://www.inspectacargezina.co.za/");
        sites.add("http://www.inspectacargezina.co.za/cars-for-sale.html");
        sites.add(inspectacargezinaVDurl);
        sites.add("http://www.wallworktrucks.com/");
        sites.add("http://www.wallworktrucks.com/trucks-for-sale-inventory.html");
        sites.add(wallworktrucksVDurl);
        sites.add("http://www.eastcountypreowned.com/");
        sites.add("http://www.zidocars.co.za/");
        sites.add("http://www.genuinemotorcars.com/");
        sites.add("https://www.tmxwholesale.com/");
        sites.add("http://www.kenworthnorthwest.com/");
        sites.add("http://www.auctiondemo.ixloo.com/");
        sites.add("http://www.globalcarexchange.com/");
        sites.add("http://www.carkingdirect.co.za");
        sites.add("http://www.motortrucks.com");

        for (String s : sites) {
            wait.until(ExpectedConditions.visibilityOf(pp.getLocationDropdownDiv()));
            pp.inputText(pp.getUrlInput(), s);
            String url = s;
            System.out.println(url);
            pp.clickOnLocationDropdown();
            pp.clickOnLocationUSA();

            try {
                pp.clickStartTest();
                wait.until(ExpectedConditions.visibilityOf(pp.getPerformanceGrade()));
            } catch (Exception e) {
                pp.clickOnTryAgain();
            }
            Integer performanceGrade = Integer.parseInt(pp.getPerformanceGrade().getAttribute("textContent").replaceAll("\\D+", ""));//("innerHTML");
            System.out.println(performanceGrade);
            String performanceLetter = "";
            if (performanceGrade >= 90) {
                performanceLetter += "A";
            } else if (performanceGrade >= 80 && performanceGrade < 90) {
                performanceLetter += "B";
            } else if (performanceGrade >= 65 && performanceGrade < 80) {
                performanceLetter += "C";
            } else {
                performanceLetter += "D";
            }
            System.out.println(performanceLetter);
            double pageSize = Double.parseDouble(pp.getMb().getAttribute("textContent").replaceAll("[^0-9.]", ""));
            System.out.println(pageSize);
            List<WebElement> partLoadings = pp.getSec().get(0).findElements(By.xpath(".//*"));
            System.out.println(partLoadings.size());
            int totalLoadingTime = 0;
            for (WebElement w : partLoadings) {
                int loadingTime = Integer.parseInt(w.getAttribute("textContent").replaceAll("\\D+", ""));
                totalLoadingTime += loadingTime;
            }
            System.out.println(totalLoadingTime);
            double seconds = totalLoadingTime / 1000;
            System.out.println(seconds);
            pages.add(new PingdomPages(url, performanceLetter, performanceGrade, pageSize, seconds));
            System.out.println(pages);
            CSVwriter writer = new CSVwriter();
            writer.writeToCSV(pages);

        }


    }
}
