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
    public static List<String> sitsToDavid = new ArrayList<String>();


    /*public Runner() {
        sitsToDavid.add("http://www.theeuropeanmasters.com/");
        sitsToDavid.add("http://www.inspectacargezina.co.za/");
    }*/


    public static void main(String[] args) {

//Инициализирую Вебдрайвер + элементы на страницах PingdomPages и VehicleDetailsPages
        List<PingdomPages> pages = new ArrayList<>();
        WebDriver driver = new ChromeDriver();
        PingdomPages pp = PageFactory.initElements(driver, PingdomPages.class);
        VehicleDetailsPages vdp = PageFactory.initElements(driver, VehicleDetailsPages.class);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //Получаю Урл страницы деталей для всех диллер сайтов
        String theeuropeanmastersVDurl = vdp.getVDurl(driver, "http://www.theeuropeanmasters.com/cars-for-sale.html", vdp.getTheeuropeanmasters());
        String inspectacargezinaVDurl = vdp.getVDurl(driver, "http://www.inspectacargezina.co.za/cars-for-sale.html", vdp.getInspectacargezina());
        String wallworktrucksVDurl = vdp.getVDurl(driver, "http://www.wallworktrucks.com/trucks-for-sale-inventory.html", vdp.getWallworktrucks());
        String eastcountypreownedVDurl = vdp.getVDurl(driver, "http://www.eastcountypreowned.com/cars-for-sale.html", vdp.getEastcountypreowned());
        String zidocarsVDurl = vdp.getVDurl(driver, "http://www.zidocars.co.za/cars-for-sale.html", vdp.getZidocars());
        String genuinemotorcarsVDurl = vdp.getVDurl(driver, "http://www.genuinemotorcars.com/cars-for-sale.html", vdp.getGenuinemotorcars());
        String tmxwholesaleVDurl = vdp.getVDurl(driver, "https://www.tmxwholesale.com/cars-for-sale.html", vdp.getTMX());
        String kenworthnorthwestVDurl = vdp.getVDurl(driver, "https://www.tmxwholesale.com/cars-for-sale.html", vdp.getKenworthnorthwest());
        String auctiondemoVDurl = vdp.getVDurl(driver, "http://www.auctiondemo.ixloo.com/presaleinventory", vdp.getAuctiondemo());
        String globalcarexchangeVDurl = vdp.getVDurl(driver, "https://www.globalcarexchange.com/cars-for-sale.html", vdp.getGlobalcarexchange());
        String carkingdirectVDurl = vdp.getVDurl(driver, "http://www.carkingdirect.co.za/cars-for-sale.html", vdp.getCarkingdirect());
        String motortrucksVDurl = vdp.getVDurl(driver, "http://www.motortrucks.com/trucks-for-sale_condition_2.html", vdp.getMotortrucks());

// В Пигдоме поочередно запускаю все сайты и получаю для каждого Рейтинг, Вес, Время загрузки и все записываю в CSV файл
        driver.get("https://tools.pingdom.com/#!/");

        sitsToDavid.add("http://www.theeuropeanmasters.com/");
        sitsToDavid.add("http://www.theeuropeanmasters.com/cars-for-sale.html");
        sitsToDavid.add(theeuropeanmastersVDurl);
        sitsToDavid.add("http://www.inspectacargezina.co.za/");
        sitsToDavid.add("http://www.inspectacargezina.co.za/cars-for-sale.html");
        sitsToDavid.add(inspectacargezinaVDurl);
        sitsToDavid.add("http://www.wallworktrucks.com/");
        sitsToDavid.add("http://www.wallworktrucks.com/trucks-for-sale-inventory.html");
        sitsToDavid.add(wallworktrucksVDurl);
        sitsToDavid.add("http://www.eastcountypreowned.com/");
        sitsToDavid.add("http://www.eastcountypreowned.com/cars-for-sale.html");
        sitsToDavid.add(eastcountypreownedVDurl);
        sitsToDavid.add("http://www.zidocars.co.za/");
        sitsToDavid.add("http://www.zidocars.co.za/cars-for-sale.html");
        sitsToDavid.add(zidocarsVDurl);
        sitsToDavid.add("http://www.genuinemotorcars.com/");
        sitsToDavid.add("http://www.genuinemotorcars.com/cars-for-sale.html");
        sitsToDavid.add(genuinemotorcarsVDurl);
        sitsToDavid.add("https://www.tmxwholesale.com/");
        sitsToDavid.add("https://www.tmxwholesale.com/cars-for-sale.html");
        sitsToDavid.add(tmxwholesaleVDurl);
        sitsToDavid.add("http://www.kenworthnorthwest.com/");
        sitsToDavid.add("http://www.kenworthnorthwest.com/trucks-for-sale_condition_2.html");
        sitsToDavid.add(kenworthnorthwestVDurl);
        sitsToDavid.add("http://www.auctiondemo.ixloo.com/");
        sitsToDavid.add("http://www.auctiondemo.ixloo.com/presaleinventory");
        sitsToDavid.add(auctiondemoVDurl);
        sitsToDavid.add("http://www.globalcarexchange.com/");
        sitsToDavid.add("https://www.globalcarexchange.com/cars-for-sale.html");
        sitsToDavid.add(globalcarexchangeVDurl);
        sitsToDavid.add("http://www.carkingdirect.co.za");
        sitsToDavid.add("http://www.carkingdirect.co.za/cars-for-sale.html");
        sitsToDavid.add(carkingdirectVDurl);
        sitsToDavid.add("http://www.motortrucks.com");
        sitsToDavid.add("http://www.motortrucks.com/trucks-for-sale_condition_2.html");
        sitsToDavid.add(motortrucksVDurl);

        for (String s : sitsToDavid) {
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
                if (pp.getLinkTryAgain().isDisplayed()) {
                    pp.getLinkTryAgain().click();
                } else {
                    pp.clickStartTest();
                }

                //pp.clickOnTryAgain();
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
