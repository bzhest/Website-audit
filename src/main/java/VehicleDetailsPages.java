import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by Andrey on 17.05.2017.
 */
public class VehicleDetailsPages {


    // David's sites
    @FindBy(how = How.CSS, using = "a.h4.text-primary.vehicle-title")
    private List<WebElement> theeuropeanmasters;

    @FindBy(how = How.CSS, using = ".minv-img.vehicle_tooltip_container a")
    private List<WebElement> inspectacargezina;

    @FindBy(how = How.CSS, using = ".vehicle_title.inline.btn-link.h3")
    private List<WebElement> wallworktrucks;

    @FindBy(how = How.CSS, using = ".h4.text-primary.vehicle-title")
    private List<WebElement> eastcountypreowned;

    @FindBy(how = How.CSS, using = ".minv-tb-img a")
    private List<WebElement> zidocars;

    @FindBy(how = How.CSS, using = ".vehicle_title.inline.btn-link.h4")
    private List<WebElement> genuinemotorcars;

    @FindBy(how = How.CSS, using = ".h4.no-margin-top a")
    private List<WebElement> tmx;

    @FindBy(how = How.CSS, using = ".h4.text-primary.vehicle-title")
    private List<WebElement> kenworthnorthwest;

    @FindBy(how = How.CSS, using = ".h4.text-primary.vehicle-title")
    private List<WebElement> auctiondemo;

    @FindBy(how = How.CSS, using = ".btn-link.h4.no-margin-top.minvR-name.margin-left-lg-none.margin-left-md-none")
    private List<WebElement> globalcarexchange;

    @FindBy(how = How.CSS, using = ".minv-img.vehicle_tooltip_container a")
    private List<WebElement> carkingdirect;

    @FindBy(how = How.CSS, using = ".h4.text-primary.vehicle-title")
    private List<WebElement> motortrucks;

    //Adtem's sites

    @FindBy(how = How.CSS, using = ".h4.text-primary.vehicle-title")
    private List<WebElement> motortrader;

    @FindBy(how = How.CSS, using = ".product-title-link.product_s_desc>a")
    private List<WebElement> supertiresonline;

    // Competitors sites


    // David's sites methods

    public List<WebElement> getTheeuropeanmasters() {
        return theeuropeanmasters;
    }

    public List<WebElement> getInspectacargezina() {
        return inspectacargezina;
    }

    public List<WebElement> getWallworktrucks() {
        return wallworktrucks;
    }

    public List<WebElement> getEastcountypreowned() {
        return eastcountypreowned;
    }

    public List<WebElement> getZidocars() {
        return zidocars;
    }

    public List<WebElement> getGenuinemotorcars() {
        return genuinemotorcars;
    }

    public List<WebElement> getTMX() {
        return tmx;
    }

    public List<WebElement> getKenworthnorthwest() {
        return kenworthnorthwest;
    }

    public List<WebElement> getAuctiondemo() {
        return auctiondemo;
    }

    public List<WebElement> getGlobalcarexchange() {
        return globalcarexchange;
    }

    public List<WebElement> getCarkingdirect() {
        return carkingdirect;
    }

    public List<WebElement> getMotortrucks() {
        return motortrucks;
    }

    // Artems's sites methods

    public List<WebElement> getMotortrader() {
        return motortrader;
    }

    public List<WebElement> getSupertiresonline() {
        return supertiresonline;
    }


    public String getVDurl(WebDriver driver, String inventoryURL, List<WebElement> carLink) {
        driver.get(inventoryURL);
        return carLink.get(0).getAttribute("href");
    }

    public String getVDurlForKingofcars(WebDriver driver, String inventoryURL, String homeURL, List<WebElement> carLink) {
        driver.get(inventoryURL);
        String pass = driver.findElements(By.cssSelector(".vehicle_search_results_item")).get(0).findElements(By.tagName("a")).get(0).getAttribute("id");
        return (homeURL + pass);
    }
}
