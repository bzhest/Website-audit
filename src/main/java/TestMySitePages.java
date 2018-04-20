
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * Created by DWork on 16.01.2018.
 */

public class TestMySitePages {

    private ArrayList<String> times = new ArrayList<>();

    @FindBy(css = ".url-entry__input")
    private WebElement enterUrlField;

    @FindBy(css = ".url-entry__submit")
    private WebElement arrowSubmitButton;

    @FindBy(css = ".results__speed-number-numeral")
    private WebElement loadingTime;

    @FindBy(css = "a.change-button")
    private WebElement testAnotherUrlLink;

    public void getLoadingTime(ArrayList<String> sites, WebDriver driver, String csvFileName) {
        WebDriverWait wait = new WebDriverWait(driver, 140);
        for (String site : sites) {
            restartTestIfFailed(wait,site, driver);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String time = loadingTime.getText().trim();
            times.add(time);
            System.out.println("Mobile time is " + time + " on " + site);
            writeToCSV(sites, times, csvFileName);
            clickAnotherLink(wait, driver);
        }
    }

    public void getLoadingTimeToExcel(ArrayList<String> sites, WebDriver driver, String workbook, String sheet) {
        WebDriverWait wait = new WebDriverWait(driver, 140);
        for (String site : sites) {
            restartTestIfFailed(wait,site, driver);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String time = loadingTime.getText().trim();
            times.add(time);
            System.out.println("Mobile time is " + time + " on " + site);
            writeToExcel(sites, times, workbook,sheet);
            clickAnotherLink(wait, driver);
        }
    }

    public void restartTestIfFailed(WebDriverWait wait, String site, WebDriver driver){
        try{
            wait.until(ExpectedConditions.visibilityOf(enterUrlField));
            inputUrl(site);
            arrowSubmitButton.click();
            wait.until(ExpectedConditions.visibilityOf(loadingTime));
            sleep(2);
        }catch(Exception ex){
            driver.get("https://testmysite.withgoogle.com/intl/en-gb");
            restartTestIfFailed(wait,site, driver);
        }
    }

    public void clickAnotherLink(WebDriverWait wait, WebDriver driver){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(testAnotherUrlLink));
            testAnotherUrlLink.click();
        }catch (Exception ex){
            try{
                sleep(2);
                wait.until(ExpectedConditions.elementToBeClickable(testAnotherUrlLink));
                testAnotherUrlLink.click();
            }catch (Exception ex1){
                driver.get("https://testmysite.withgoogle.com/intl/en-gb");
            }


        }
    }


    public void writeToCSV(ArrayList<String> sites, ArrayList <String> times, String fileName) {
        CSVwriter writer = new CSVwriter();
        writer.testMySiteToCSV(sites, times, fileName);
    }

    public void writeToExcel(ArrayList<String> sites, ArrayList <String> times, String workBook, String sheet) {
        JavaExcelApp excel = new JavaExcelApp();
        excel.testMySiteToExcel( sites, times,workBook,sheet);
    }

    public void inputUrl(String url) {
        enterUrlField.clear();
        enterUrlField.sendKeys(url);
    }

    public void sleep(int sec){
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
