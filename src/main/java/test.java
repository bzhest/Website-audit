import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by DWork on 06.06.2017.
 */
public class test {
    WebDriver driver = new ChromeDriver();
    @Test
    public void isWidgetDisplayed(){
        driver.get("http://www.solomia.andreyb.ixloo.com/dealer-review-form_dealer_6287.html");
        Assert.assertTrue(driver.getCurrentUrl().equals("http://www.solomia.andreyb.ixloo.com/dealer-review-form_dealer_6287.html"));
        driver.quit();
    }

    @Test
    public void isWidgetNotDisplayed(){
        driver.get("http://www.solomia.andreyb.ixloo.com/dealer-review-form_dealer_6287.html");
        Assert.assertTrue(driver.getCurrentUrl().equals("http://www.solomia.andreyb.ixloo.com/dealer-review-form_dealer_6288.html"));
        driver.quit();
    }
}
