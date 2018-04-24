import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DWork on 21.04.2018.
 */
public class JtmetrixMethods {

    private  final String JTMETRIX_SCREENSHOTS_PATH = "Screenshots/" + currentTime() + "/Jtmetrix/sites/";
    By byUserName = By.cssSelector("#username");
    By byUrlField = By.cssSelector("input[type = 'url']");
    By bySubmitButton = By.cssSelector("button[type = 'submit']");
    By byTabs = By.cssSelector(".r-tabs-tab");
    By byBox = By.cssSelector(".box");


    public  void makeGtmetrixScreenshots(List<String> sites, WebDriver driver, WebDriverWait wait) {


        for (String site : sites) {

            driver.get("https://gtmetrix.com/");
            wait.until(ExpectedConditions.visibilityOfElementLocated(byUrlField));
            inputText(driver.findElement(byUrlField), site);
            driver.findElement(bySubmitButton).click();
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(byTabs,0));
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElements(byBox).get(2));
            act.perform();
            String siteWithoutSlash = site.replaceAll("[./:]","_");
            String siteName = siteWithoutSlash.substring(siteWithoutSlash.indexOf("www_")+4);
            Screenshoter.takeSnapShot(driver, JTMETRIX_SCREENSHOTS_PATH + siteName + ".png");
        }
    }

    protected String currentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public void inputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
