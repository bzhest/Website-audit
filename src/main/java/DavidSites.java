import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.script.*;


/**
 * Created by Andrey on 17.05.2017.
 */
public class DavidSites {

    public static WebDriver driver;
    public static ArrayList<String> sitsToDavid = new ArrayList<>();
    public static ArrayList<PingdomPages> davidPagesPingdom = new ArrayList<>();
    public static ArrayList<GooglePages> davidPagesGooglePages = new ArrayList<>();
    private static String workbookExcel = "WebSiteAudit.xls";
    private static String workbookCsv = "csvDavidPingdom";
    private static String sheetDavidPingdom = "DavidPingdom";
    private static String sheetDavidTestMySite = "DavidTestMySite";
    private static String sheetDavidGoogle = "DavidGoogle";


    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 50);
        GooglePageMethods gm = new GooglePageMethods();
        GooglePages g = PageFactory.initElements(driver, GooglePages.class);
        PingdomPages pp = PageFactory.initElements(driver, PingdomPages.class);
        VehicleDetailsPages vdp = PageFactory.initElements(driver, VehicleDetailsPages.class);
        //PingdomPagesMethods ppm = new PingdomPagesMethods();
        PingdomPagesMethods ppm = PageFactory.initElements(driver, PingdomPagesMethods.class);
        TestMySitePages testMySitePages = PageFactory.initElements(driver, TestMySitePages.class);

        //String theeuropeanmastersVDurl = vdp.getVDurl(driver, "http://www.theeuropeanmasters.com/cars-for-sale.html", vdp.getTheeuropeanmasters());

        String inspectacargezinaVDurl = vdp.getVDurl(driver, "http://www.inspectacargezina.co.za/cars-for-sale.html", vdp.getInspectacargezina());
        String wallworktrucksVDurl = vdp.getVDurl(driver, "http://www.wallworktrucks.com/trucks-for-sale-inventory.html", vdp.getWallworktrucks());
        String eastcountypreownedVDurl = vdp.getVDurl(driver, "http://www.eastcountypreowned.com/cars-for-sale.html", vdp.getEastcountypreowned());
        String zidocarsVDurl = vdp.getVDurl(driver, "http://www.zidocars.co.za/cars-for-sale.html", vdp.getZidocars());
        String genuinemotorcarsVDurl = vdp.getVDurl(driver, "http://www.genuinemotorcars.com/cars-for-sale.html", vdp.getGenuinemotorcars());
        //String tmxwholesaleVDurl = vdp.getVDurl(driver, "https://www.tmxwholesale.com/cars-for-sale.html", vdp.getTMX());
        String kenworthnorthwestVDurl = vdp.getVDurl(driver, "http://www.kenworthnorthwest.com/trucks-for-sale_condition_2.html", vdp.getKenworthnorthwest());
        String auctiondemoVDurl = vdp.getVDurl(driver, "http://www.auctiondemo.ixloo.com/presaleinventory", vdp.getAuctiondemo());
        String globalcarexchangeVDurl = vdp.getVDurl(driver, "https://www.globalcarexchange.com/cars-for-sale.html", vdp.getGlobalcarexchange());
        String motortrucksVDurl = vdp.getVDurl(driver, "http://www.motortrucks.com/trucks-for-sale_condition_2.html", vdp.getMotortrucks());


        /*sitsToDavid.add("http://www.theeuropeanmasters.com/");
        sitsToDavid.add("http://www.theeuropeanmasters.com/cars-for-sale.html");
        sitsToDavid.add(theeuropeanmastersVDurl);*/


        sitsToDavid.add("http://www.inspectacargezina.co.za/");
        sitsToDavid.add("http://www.inspectacargezina.co.za/cars-for-sale.html");
        sitsToDavid.add(inspectacargezinaVDurl);
        sitsToDavid.add("http://www.wallworktrucks.com/");
        sitsToDavid.add("https://www.wallworktrucks.com/trucks-for-sale.html");
        sitsToDavid.add(wallworktrucksVDurl);
        sitsToDavid.add("https://www.eastcountypreowned.com/");
        sitsToDavid.add("https://www.eastcountypreowned.com/cars-for-sale.html");
        sitsToDavid.add(eastcountypreownedVDurl);
        sitsToDavid.add("http://www.zidocars.co.za/");
        sitsToDavid.add("http://www.zidocars.co.za/cars-for-sale.html");
        sitsToDavid.add(zidocarsVDurl);
        sitsToDavid.add("http://www.genuinemotorcars.com/");
        sitsToDavid.add("http://www.genuinemotorcars.com/cars-for-sale.html");
        sitsToDavid.add(genuinemotorcarsVDurl);
        /*sitsToDavid.add("https://www.tmxwholesale.com/");
        sitsToDavid.add("https://www.tmxwholesale.com/cars-for-sale.html");*/
        //sitsToDavid.add(tmxwholesaleVDurl);
        sitsToDavid.add("http://www.kenworthnorthwest.com/");
        sitsToDavid.add("http://www.kenworthnorthwest.com/trucks-for-sale_condition_2.html");
        sitsToDavid.add(kenworthnorthwestVDurl);
        sitsToDavid.add("http://www.auctiondemo.ixloo.com/");
        sitsToDavid.add("http://www.auctiondemo.ixloo.com/presaleinventory");
        sitsToDavid.add(auctiondemoVDurl);
        sitsToDavid.add("http://www.globalcarexchange.com/");
        sitsToDavid.add("https://www.globalcarexchange.com/cars-for-sale.html");
        sitsToDavid.add(globalcarexchangeVDurl);
        sitsToDavid.add("http://www.motortrucks.com");
        sitsToDavid.add("http://www.motortrucks.com/trucks-for-sale_condition_2.html");
        sitsToDavid.add(motortrucksVDurl);
        sitsToDavid.add("https://advancedregenerativeorthopedics.com/");



        //--------------------PINGDOM--------------
        driver.get("https://tools.pingdom.com/#!/");
        ppm.getPingdomSitesParameters(sitsToDavid, davidPagesPingdom, pp, driver, workbookCsv, wait); // for csv file
        //ppm.getPingdomSitesParametersWriteExcel(sitsToDavid, davidPagesPingdom, pp, driver, workbookExcel, sheetDavidPingdom, wait);


        //-------------------GTMETRIX - screenshots-----
        new JtmetrixMethods().makeGtmetrixScreenshots(sitsToDavid, driver, wait);


        //-------------------GOOGLE Insights----------
        driver.get("https://developers.google.com/speed/pagespeed/insights/");
        gm.getGoogleSitesParameters(sitsToDavid, davidPagesGooglePages, g, "csvDavidGoogle", pp, wait); // for csv file
        //gm.getGoogleSitesParameterstoExcel(sitsToDavid, davidPagesGooglePages, g, workbookExcel, sheetDavidGoogle, pp, wait);

        //------------------GOOGLE Test My Site--------
        driver.get("https://testmysite.withgoogle.com/intl/en-gb");
        testMySitePages.getLoadingTime(sitsToDavid,driver,"csvDavidTestMySite");// for csv file
        //testMySitePages.getLoadingTimeToExcel(sitsToDavid,driver,workbookExcel,sheetDavidTestMySite);

    }

}


/*String globalcarexchangeVDurl = "";
        try {
            globalcarexchangeVDurl += vdp.getVDurl(driver, "https://www.globalcarexchange.com/cars-for-sale.html", vdp.getGlobalcarexchange());
        } catch (Exception ex) {
            globalcarexchangeVDurl += "https://www.globalcarexchange.com/sitemap";
        }*/

    //String carkingdirectVDurl = vdp.getVDurlForKingofcars(driver, "http://www.carkingdirect.co.za/used-models","http://www.carkingdirect.co.za", vdp.getCarkingdirect());
