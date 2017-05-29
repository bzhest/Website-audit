import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DWork on 19.05.2017.
 */
public class PingdomPagesMethods {

    PingdomPages pp = new PingdomPages();
    WebDriver driver;
    Integer performanceGrade;
    String pageSize;
    String performanceLetter;
    Double seconds;


    public void getPingdomSitesParameters(ArrayList<String> sites, ArrayList<PingdomPages> pingdomPage, PingdomPages pp, WebDriver driver, String csvFileName, WebDriverWait wait) {
        for (String site : sites) {
            wait.until(ExpectedConditions.visibilityOf(pp.getLocationDropdownDiv()));
            inputSiteSelectCountry(pp, site);
            tryToSelectAllPingdomPagesFields(driver, pp, site, wait);
            calculatePerformanceGrade(pp);
            calculatePerformanceLetter(getPerformanceGrade(pp));
            calculatePageSize(pp);
            calculateLoadTime(pp);
            createPingdomPageObject(pingdomPage, site, pp);
            writeToCSV(pingdomPage, csvFileName);
        }
    }

    public String getPageSize(PingdomPages pp) {

        Double loadNumber = Double.parseDouble(pp.getMb().getAttribute("textContent").replaceAll("[^0-9.]", ""));
        //count number of digits before sign ',' - if it 3 digits - it KB. if 1 or 2 - it MB
        //if it kb - cast it to mb (like from 238.1 kb to 0.2 Mb )
        int digitsNumberBeforePoint = Integer.parseInt(String.valueOf(loadNumber).substring(0,String.valueOf(loadNumber).indexOf('.')));
        if (digitsNumberBeforePoint < 3){
             return String.valueOf(loadNumber).replace('.',',');
        }else {
            Double mb = Math.round(loadNumber) / 1000.0d;
            mb = PingdomPagesMethods.round(mb, 1);
            return String.valueOf(mb).replace('.',',');
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

    public String getSeconds(PingdomPages pp) {
        List<WebElement> partLoadings = pp.getSec().get(0).findElements(By.xpath(".//*"));
        int totalLoadingTime = 0;
        for (WebElement w : partLoadings) {
            int loadingTime = Integer.parseInt(w.getAttribute("textContent").replaceAll("\\D+", ""));
            totalLoadingTime += loadingTime;
        }
        seconds = Math.round(totalLoadingTime) / 1000.0d;
        seconds = PingdomPagesMethods.round(seconds, 1);
        return String.valueOf(seconds).replace('.',',');
    }

    public void writeToCSV(ArrayList<PingdomPages> pingdomPage, String fileName) {
        CSVwriter writer = new CSVwriter();
        writer.writePingdomToCSV(pingdomPage, fileName);
    }

    public void createPingdomPageObject(ArrayList<PingdomPages> pingdomPage, String site, PingdomPages pp) {
        pingdomPage.add(new PingdomPages(site, getPerformanceLetter(getPerformanceGrade(pp)), getPerformanceGrade(pp), getPageSize(pp), getSeconds(pp)));
        System.out.println(pingdomPage);
    }

    public void calculateLoadTime(PingdomPages pp) {
        List<WebElement> partLoadings = pp.getSec().get(0).findElements(By.xpath(".//*"));
        int totalLoadingTime = 0;
        for (WebElement w : partLoadings) {
            int loadingTime = Integer.parseInt(w.getAttribute("textContent").replaceAll("\\D+", ""));
            totalLoadingTime += loadingTime;
        }
        System.out.println(totalLoadingTime + "ms");
        seconds = Math.round(totalLoadingTime) / 1000.0d;
        seconds = PingdomPagesMethods.round(seconds, 1);
        System.out.println(seconds + "sec");
    }

    public void calculatePageSize(PingdomPages pp) {
        Double pageSize = Double.parseDouble(pp.getMb().getAttribute("textContent").replaceAll("[^0-9.]", ""));
        System.out.println(pageSize);
    }

    public void calculatePerformanceLetter(Integer performanceGrade) {
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
    }


    public void calculatePerformanceGrade(PingdomPages pp) {
        Integer performanceGrade = Integer.parseInt(pp.getPerformanceGrade().getAttribute("textContent").replaceAll("\\D+", ""));//("innerHTML");
        System.out.println(performanceGrade);
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
            //if (pp.getLinkTryAgain() != null) {
            if (pp.getLinkTryAgain().size() !=0) {
                try {
                    pp.getLinkTryAgain().get(0).click();
                    wait.until(ExpectedConditions.visibilityOf(pp.getLocationDropdownDiv()));
                    inputSiteSelectCountryClickStart(pp, site);
                } catch (Exception ex1) {
                    tryToSelectAllPingdomPagesFields(driver, pp, site, wait);
                }
            } else if (!pp.getButtonClickStartTest().isDisplayed()) {
                try {
                    driver.get(driver.getCurrentUrl());
                    wait.until(ExpectedConditions.visibilityOf(pp.getLocationDropdownDiv()));
                    inputSiteSelectCountryClickStart(pp, site);
                } catch (Exception ex1) {
                    tryToSelectAllPingdomPagesFields(driver, pp, site, wait);
                }
            } else {
                try {
                    pp.clickStartTest();
                    wait.until(ExpectedConditions.visibilityOf(pp.getPerformanceGrade()));
                } catch (Exception ex1) {
                    tryToSelectAllPingdomPagesFields(driver, pp, site, wait);
                }
            }
        }
    }
}




