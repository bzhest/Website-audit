import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by DWork on 19.05.2017.
 */
public class PingdomPagesMethods {

    private final String PINGDOM_SCREENSHOTS_PATH = "Screenshots/" + currentTime() + "/Pingdom/sites/";
    String performanceLetter;
    Double seconds;
    Integer performanceGrade;
    Double pageSize;


    public void getPingdomSitesParameters(ArrayList<String> sites, ArrayList<PingdomPages> pingdomPage, PingdomPages pp, WebDriver driver, String csvFileName, WebDriverWait wait) {

        for (String site : sites) {
            List<Integer> performanceGradeList = new ArrayList<>();
            List<Double> pageSizeList = new ArrayList<>();
            List<Double> loadTimeList = new ArrayList<>();
            for(int i = 0; i <3; i++) {

                wait.until(ExpectedConditions.visibilityOf(pp.getLocationDropdownDiv()));
                inputSiteSelectCountry(pp, site);
                tryToSelectAllPingdomPagesFields(driver, pp, site, wait);
                performanceGradeList.add(calculatePerformanceGrade(pp));
                calculatePerformanceLetter(getPerformanceGrade(pp));
                pageSizeList.add(calculatePageSize(pp));
                loadTimeList.add(calculateLoadTime(pp));

                makePingdomScreenshot(site,driver);
                scrollToPingdomTable(driver);
                makePingdomScreenshot(site,driver);
            }
            Integer maxPerformanceGrade = Collections.max(performanceGradeList);
            System.out.print("Best grades result: " + maxPerformanceGrade + " ");
            String maxPerformanceLetter = calculatePerformanceLetter(maxPerformanceGrade);
            //System.out.println("Result " + maxPerformanceLetter);
            Double maxPageSize = Collections.max(pageSizeList);
            System.out.println("Best weight result:  " + maxPageSize + " MB");
            Double minPageSeconds = Collections.min(loadTimeList);
            System.out.println("Best time result: " + minPageSeconds + " sec");
            System.out.println("***********************************************");
            pingdomPage.add(new PingdomPages(site, maxPerformanceLetter, maxPerformanceGrade, maxPageSize, minPageSeconds));
            writeToCSV(pingdomPage, csvFileName);
        }
    }

    protected String currentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public void makePingdomScreenshot(String site, WebDriver driver){
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.cssSelector(".rbc-summary-group")));
        act.perform();
        String siteWithoutSlash = site.replaceAll("[./:]","_");
        String siteName = siteWithoutSlash.substring(siteWithoutSlash.indexOf("www_")+4);
        Screenshoter.takeSnapShot(driver, PINGDOM_SCREENSHOTS_PATH + siteName + ".png");
    }

        public void scrollToPingdomTable(WebDriver driver){
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.cssSelector(".table-general.table-perfinsights")));
        act.perform();
    }

    /*public void makeGtmetrixScreenshots(ArrayList<String> sites) {
        Configuration.reportsFolder = "Screenshots/" + currentTime() + "/Gtmetrix";
        for (String site : sites) {

            open("https://gtmetrix.com/");
            $(By.cssSelector("input[type = 'url']")).setValue("http://www.motortrader.co.za/toyota-for-sale-sa.html");
            $(By.cssSelector("button[type = 'submit']")).click();
            $("#username").waitUntil(visible, 5000);
            $$(By.cssSelector("r-tabs-tab")).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(0));
            $$(By.cssSelector(".r-tabs-tab")).get(0).scrollTo();
            screenshot(site);
        }
    }*/

    public void getPingdomSitesParametersWriteExcel(ArrayList<String> sites, ArrayList<PingdomPages> pingdomPage, PingdomPages pp, WebDriver driver, String workBook, String sheet, WebDriverWait wait) {
        for (String site : sites) {
            wait.until(ExpectedConditions.visibilityOf(pp.getLocationDropdownDiv()));
            inputSiteSelectCountry(pp, site);
            tryToSelectAllPingdomPagesFields(driver, pp, site, wait);
            calculatePerformanceGrade(pp);
            calculatePerformanceLetter(getPerformanceGrade(pp));
            calculatePageSize(pp);
            calculateLoadTime(pp);
            createPingdomPageObject(pingdomPage, site, pp);
            writeToExcel(pingdomPage, workBook, sheet);
        }
    }

    public Double getPageSize(PingdomPages pp) {

        Double loadNumber = Double.parseDouble(pp.getMb().getAttribute("textContent").replaceAll("[^0-9.]", ""));

        //if it KB - cast it to MB (like from 238.1 KB to 0.2 MB )
        if(pp.getMb().getText().contains("MB")){
            String result =  String.valueOf(loadNumber).replace('.',',');
            return Double.parseDouble(result);
        }else{
            Double mb = Math.round(loadNumber) / 1000.0d;
            mb = PingdomPagesMethods.round(mb, 1);
            String result1 =   String.valueOf(mb).replace('.',',');
            return Double.parseDouble(result1);
        }
    }

    public Integer getPerformanceGrade(PingdomPages pp) {
        return Integer.parseInt(pp.getPerformanceGrade().getAttribute("textContent").replaceAll("\\D+", ""));
    }

    public String getPerformanceLetter(Integer performanceGrade) {
        performanceLetter = "";
        if (performanceGrade >= 90) {
            performanceLetter += "A";
        } else if (performanceGrade >= 80 && performanceGrade < 90) {
            performanceLetter += "B";
        } else if (performanceGrade >= 65 && performanceGrade < 80) {
            performanceLetter += "C";
        } else {
            performanceLetter += "D";
        }
        return performanceLetter;
    }

    public Double getSeconds(PingdomPages pp) {
        List<WebElement> partLoadings = pp.getSec().get(0).findElements(By.xpath(".//*"));
        int totalLoadingTime = 0;
        for (WebElement w : partLoadings) {
            int loadingTime = Integer.parseInt(w.getAttribute("textContent").replaceAll("\\D+", ""));
            totalLoadingTime += loadingTime;
        }
        seconds = Math.round(totalLoadingTime) / 1000.0d;
        seconds = PingdomPagesMethods.round(seconds, 1);
        String sec =  String.valueOf(seconds).replace('.',',');
        return Double.parseDouble(sec);
    }

    public void writeToCSV(ArrayList<PingdomPages> pingdomPage, String fileName) {
        CSVwriter writer = new CSVwriter();
        writer.writePingdomToCSV(pingdomPage, fileName);
    }

    public void writeToExcel(ArrayList<PingdomPages> pingdomPage, String workBook, String sheet) {
        JavaExcelApp excel = new JavaExcelApp();
        excel.writePingdomToExcel(pingdomPage,workBook,sheet);
    }



    public void createPingdomPageObject(ArrayList<PingdomPages> pingdomPage, String site, PingdomPages pp) {
        pingdomPage.add(new PingdomPages(site, getPerformanceLetter(getPerformanceGrade(pp)), getPerformanceGrade(pp), getPageSize(pp), getSeconds(pp)));
        //System.out.println(pingdomPage);
    }

    public Double calculateLoadTime(PingdomPages pp) {
        List<WebElement> partLoadings = pp.getSec().get(0).findElements(By.xpath(".//*"));
        Double totalLoadingTime = 0.0;
        for (WebElement w : partLoadings) {
            int loadingTime = Integer.parseInt(w.getAttribute("textContent").replaceAll("\\D+", ""));
            totalLoadingTime += loadingTime;
            //totalLoadingTime.doubleValue();
        }
        //System.out.println(totalLoadingTime + "ms");
        seconds = Math.round(totalLoadingTime) / 1000.0d;
        seconds = PingdomPagesMethods.round(seconds, 1);
        System.out.println(seconds + " sec");
        System.out.println("-----------------------------------------------");
        return seconds;
    }

    public Double calculatePageSize(PingdomPages pp) {
        Double pageSize = Double.parseDouble(pp.getMb().getAttribute("textContent").replaceAll("[^0-9.]", ""));
        pageSize = returnSizeInMb(pageSize);//количество цифр перед точкой 3 -> делим на 1000 число)
        System.out.println(pageSize + " MB");
        return pageSize;
    }

    private Double returnSizeInMb(Double pageSize){
        String [] parts = String.valueOf(pageSize).split(".");
        int digitsNumberBeforePoint = parts[0].toCharArray().length;
        if(digitsNumberBeforePoint>2){
            return round(pageSize/1000d, 1);
        }else{
            return pageSize;
        }
    }


    public String calculatePerformanceLetter(Integer performanceGrade) {
        performanceLetter = "";
        if (performanceGrade >= 90) {
            performanceLetter += "A";
        } else if (performanceGrade >= 80 && performanceGrade < 90) {
            performanceLetter += "B";
        } else if (performanceGrade >= 70 && performanceGrade < 80) {
            performanceLetter += "C";
        } else {
            performanceLetter += "D";
        }
        System.out.println(performanceLetter);
        return performanceLetter;
    }


    public Integer calculatePerformanceGrade(PingdomPages pp) {
        Integer performanceGrade = Integer.parseInt(pp.getPerformanceGrade().getAttribute("textContent").replaceAll("\\D+", ""));//("innerHTML");
        System.out.print(performanceGrade + " ");
        return performanceGrade;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void inputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void inputSiteSelectCountry(PingdomPages pp, String site) {
        pp.inputText(pp.getUrlInput(), site);
        System.out.println(site);
        pp.clickOnLocationDropdown();
        pp.clickOnLocationUSA();
    }

    public void inputSiteSelectCountryClickStart(PingdomPages pp, String site) {
        inputSiteSelectCountry(pp, site);
        pp.clickStartTest();
    }

    public void tryToSelectAllPingdomPagesFields(WebDriver driver, PingdomPages pp, String site, WebDriverWait wait) {
        try {
            pp.clickStartTest();
            wait.until(ExpectedConditions.visibilityOf(pp.getPerformanceGrade()));
        } catch (Exception ex) {
            if (pp.getLinkTryAgain().size() !=0) {
                clickLinkTryAgain(driver, pp, site, wait);
            } else if (!pp.getButtonClickStartTest().isDisplayed()) {
                refreshPage_StartTestAgain(driver, pp, site, wait);
            } else {
                clickStartTest(driver, pp, site, wait);
            }
        }
    }

    private void clickStartTest(WebDriver driver, PingdomPages pp, String site, WebDriverWait wait) {
        try {
            pp.clickStartTest();
            wait.until(ExpectedConditions.visibilityOf(pp.getPerformanceGrade()));
        } catch (Exception ex1) {
            tryToSelectAllPingdomPagesFields(driver, pp, site, wait);
        }
    }

    private void refreshPage_StartTestAgain(WebDriver driver, PingdomPages pp, String site, WebDriverWait wait) {
        try {
            driver.get(driver.getCurrentUrl());
            wait.until(ExpectedConditions.visibilityOf(pp.getLocationDropdownDiv()));
            inputSiteSelectCountryClickStart(pp, site);
            wait.until(ExpectedConditions.visibilityOf(pp.getPerformanceGrade()));
        } catch (Exception ex1) {
            tryToSelectAllPingdomPagesFields(driver, pp, site, wait);
        }
    }

    private void clickLinkTryAgain(WebDriver driver, PingdomPages pp, String site, WebDriverWait wait) {
        try {
            pp.getLinkTryAgain().get(0).click();
            wait.until(ExpectedConditions.visibilityOf(pp.getLocationDropdownDiv()));
            inputSiteSelectCountryClickStart(pp, site);
            wait.until(ExpectedConditions.visibilityOf(pp.getPerformanceGrade()));
        } catch (Exception ex1) {
            tryToSelectAllPingdomPagesFields(driver, pp, site, wait);
        }
    }


}




