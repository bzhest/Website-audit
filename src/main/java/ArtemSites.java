import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
/*import GooglePages;
import PingdomPages;*/

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrey on 20.05.2017.
 */
public class ArtemSites {
    public  static ArrayList<String> sitsToArtem = new ArrayList<>();
    public static ArrayList<PingdomPages> artemPagesPingdom = new ArrayList<>();
    public static ArrayList<GooglePages> artemPagesGooglePages = new ArrayList<>();


    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 70);
        GooglePageMethods gm = new GooglePageMethods();
        PingdomPages pp = PageFactory.initElements(driver, PingdomPages.class);
        VehicleDetailsPages vdp = PageFactory.initElements(driver, VehicleDetailsPages.class);
        GooglePages g = PageFactory.initElements(driver, GooglePages.class);
        PingdomPagesMethods ppm = new PingdomPagesMethods();
        TestMySitePages testMySitePages = PageFactory.initElements(driver, TestMySitePages.class);

        String motortraderVDurl = vdp.getVDurl(driver,"http://www.motortrader.co.za/new-and-used-cars-for-sale-in-south-africa.html", vdp.getMotortrader());
        String supertiresonlineVDurl = vdp.getVDurl(driver,"http://supertiresonline.com/shop-tires-tampa-clearwater.html", vdp.getSupertiresonline());
        //String globalcarexchangeVDurl = vdp.getVDurl(driver, "https://www.globalcarexchange.com/cars-for-sale.html", vdp.getGlobalcarexchange());
        //String wallworktrucksVDurl = vdp.getVDurl(driver, "http://www.wallworktrucks.com/trucks-for-sale-inventory.html", vdp.getWallworktrucks());
        //String tmxwholesaleVDurl = vdp.getVDurl(driver, "https://www.tmxwholesale.com/cars-for-sale.html", vdp.getTMX());
        //String autoexpostoreVDurl = vdp.getVDurl(driver,"http://www.autoexpostore.com/cars-for-sale.html", vdp.getAutoexpostore());



        /*sitsToArtem.add("http://www.wallworktrucks.com/");
        sitsToArtem.add("http://www.wallworktrucks.com/trucks-for-sale-inventory.html");
        sitsToArtem.add(wallworktrucksVDurl);*/
        sitsToArtem.add("http://www.motortrader.co.za/");
        sitsToArtem.add("http://www.motortrader.co.za/new-and-used-cars-for-sale-in-south-africa.html");
        sitsToArtem.add(motortraderVDurl);
        /*sitsToArtem.add("http://www.globalcarexchange.com/");
        sitsToArtem.add("https://www.globalcarexchange.com/cars-for-sale.html");
        sitsToArtem.add(globalcarexchangeVDurl);*/
        sitsToArtem.add("http://supertiresonline.com/");
        sitsToArtem.add("http://supertiresonline.com/shop-tires-tampa-clearwater.html");
        sitsToArtem.add(supertiresonlineVDurl);
        //sitsToArtem.add("http://uvlrx.com/");
        sitsToArtem.add("https://www.autoxloo.com/");
        /*sitsToArtem.add("https://www.tmxwholesale.com/");
        sitsToArtem.add("https://www.tmxwholesale.com/cars-for-sale.html");
        sitsToArtem.add(tmxwholesaleVDurl);*/
        /*sitsToArtem.add("http://www.autoexpostore.com");
        sitsToArtem.add("http://www.autoexpostore.com/cars-for-sale.html");
        sitsToArtem.add(autoexpostoreVDurl);*/

        //--------------------PINGDOM--------------
        driver.get("https://tools.pingdom.com/#!/");
        ppm.getPingdomSitesParameters(sitsToArtem, artemPagesPingdom,pp, driver, "csvArtemPingdom",wait);

        //-------------------GTMETRIX - screenshots-----
        new JtmetrixMethods().makeGtmetrixScreenshots(sitsToArtem,driver, wait);

        //-------------------GOOGLE Insights-------
        driver.get("https://developers.google.com/speed/pagespeed/insights/");
        gm.getGoogleSitesParameters(sitsToArtem, artemPagesGooglePages, g, "csvArtemGoogle", pp, wait);


        //-------------------GOOGLE Test My Site
        driver.get("https://testmysite.withgoogle.com/intl/en-gb");
        testMySitePages.getLoadingTime(sitsToArtem,driver,"csvArtemTestMySite");

        }
    }

/*String globalcarexchangeVDurl = "";
        try{
            globalcarexchangeVDurl += vdp.getVDurl(driver, "https://www.globalcarexchange.com/cars-for-sale.html", vdp.getGlobalcarexchange());
        }catch(Exception ex){
            globalcarexchangeVDurl += "https://www.globalcarexchange.com/sitemap";
        }*/