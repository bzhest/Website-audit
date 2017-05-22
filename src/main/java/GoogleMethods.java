import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * Created by Andrey on 21.05.2017.
 */
public class GoogleMethods {

    WebDriver driver;


    public void getGoogleSitesParameters(ArrayList<String> sites, ArrayList<GooglePages> googlePages, GooglePages g, String csvFileName, PingdomPages pp, WebDriverWait wait) {

        for (String site : sites) {

            pp.inputText(g.getUrlInput(), site);
            g.getButtonAnalize().click();
            wait.until(ExpectedConditions.visibilityOf(g.getMobileTab()));
            g.getMobileTab().click();
            String mobileValue = g.getNumberMobile().getAttribute("textContent").replace(" / 100","");
            g.getDesktopTab().click();
            String desktopValue = g.getNumberDesktop().getAttribute("textContent").replace(" / 100","");
            googlePages.add(new GooglePages(site, mobileValue,desktopValue));
            writeToCSV(googlePages,csvFileName);
        }
    }

    public void writeToCSV(ArrayList<GooglePages> googlePages, String fileName){
        CSVwriter writer = new CSVwriter();
        writer.writeGoogleToCSV(googlePages, fileName);
    }





}
