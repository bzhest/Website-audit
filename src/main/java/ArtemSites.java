import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrey on 20.05.2017.
 */
public class ArtemSites {
    public  static ArrayList<String> sitsToArtem = new ArrayList<>();
    public static ArrayList<PingdomPages> ArtemPages = new ArrayList<>();


    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        PingdomPages pp = PageFactory.initElements(driver, PingdomPages.class);
        VehicleDetailsPages vdp = PageFactory.initElements(driver, VehicleDetailsPages.class);
        PingdomPagesMethods ppm = new PingdomPagesMethods();

        String motortraderVDurl = vdp.getVDurl(driver,"http://www.motortrader.co.za/new-and-used-cars-for-sale-in-south-africa.html", vdp.getMotortrader());
        String supertiresonlineVDurl = vdp.getVDurl(driver,"http://supertiresonline.com/shop-tires-tampa-clearwater.html", vdp.getSupertiresonline());
        String wallworktrucksVDurl = vdp.getVDurl(driver, "http://www.wallworktrucks.com/trucks-for-sale-inventory.html", vdp.getWallworktrucks());
        String globalcarexchangeVDurl = vdp.getVDurl(driver, "https://www.globalcarexchange.com/cars-for-sale.html", vdp.getGlobalcarexchange());
        String tmxwholesaleVDurl = vdp.getVDurl(driver, "https://www.tmxwholesale.com/cars-for-sale.html", vdp.getTMX());

        driver.get("https://tools.pingdom.com/#!/");

        sitsToArtem.add("http://www.wallworktrucks.com/");
        sitsToArtem.add("http://www.wallworktrucks.com/trucks-for-sale-inventory.html");
        sitsToArtem.add(wallworktrucksVDurl);
        sitsToArtem.add("http://www.motortrader.co.za/");
        sitsToArtem.add("http://www.motortrader.co.za/new-and-used-cars-for-sale-in-south-africa.html");
        sitsToArtem.add(motortraderVDurl);
        sitsToArtem.add("http://www.globalcarexchange.com/");
        sitsToArtem.add("https://www.globalcarexchange.com/cars-for-sale.html");
        sitsToArtem.add(globalcarexchangeVDurl);
        sitsToArtem.add("http://supertiresonline.com/");
        sitsToArtem.add("http://supertiresonline.com/shop-tires-tampa-clearwater.html");
        sitsToArtem.add(supertiresonlineVDurl);
        sitsToArtem.add("http://uvlrx.com/");
        sitsToArtem.add("https://www.autoxloo.com/");
        sitsToArtem.add("https://www.tmxwholesale.com/");
        sitsToArtem.add("https://www.tmxwholesale.com/cars-for-sale.html");
        sitsToArtem.add(tmxwholesaleVDurl);

        ppm.getPingdomSitesParameters(sitsToArtem, ArtemPages,pp, driver, "csvArtem",wait);


    }
}