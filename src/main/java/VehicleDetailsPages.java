import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by Andrey on 17.05.2017.
 */
public class VehicleDetailsPages {


    @FindBy(how = How.CSS, using = "a.h4.text-primary.vehicle-title")
    private List<WebElement> theeuropeanmasters;

    @FindBy(how = How.CSS, using = ".minv-img.vehicle_tooltip_container a")
    private List<WebElement> inspectacargezina;

    @FindBy(how = How.CSS, using = ".vehicle_title.inline.btn-link.h3")
    private List<WebElement> wallworktrucks;

    public List<WebElement> getTheeuropeanmasters() {
        return theeuropeanmasters;
    }

    public List<WebElement> getInspectacargezina() {
        return inspectacargezina;
    }

    public List<WebElement> getWallworktrucks() {
        return wallworktrucks;
    }

    public String getVDurl(WebDriver driver, String inventoryURL, List <WebElement> carLink){
        driver.get(inventoryURL);
        return carLink.get(0).getAttribute("href");
    }
}
