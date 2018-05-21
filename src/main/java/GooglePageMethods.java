import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrey on 21.05.2017.
 */
public class GooglePageMethods {

    private String mobileValue;
    private String desktopValue;


    public void getGoogleSitesParameters(ArrayList<String> sites, ArrayList<GooglePages> googlePages, GooglePages g, String csvFileName, PingdomPages pp, WebDriverWait wait) {

        for (String site : sites) {
            List<Integer> mobileList = new ArrayList<>();
            List<Integer> desctopList = new ArrayList<>();
            for(int i = 0; i <3; i++) {

                pp.inputText(g.getUrlInput(), site);
                System.out.println(site);
                g.getButtonAnalize().click();
                wait.until(ExpectedConditions.visibilityOf(g.getMobileTab()));
                g.getMobileTab().click();
                mobileValue = g.getMobileOptimizationGrade().getText();
                mobileList.add(Integer.parseInt(mobileValue));
                System.out.println("On mob.: " + mobileValue);
                g.getDesktopTab().click();
                desktopValue = g.getDesctopOptimizationGrade().getText();
                desctopList.add(Integer.parseInt(desktopValue));
                System.out.println("On desk.: " + desktopValue);
            }
            String maxMobile = Collections.max(mobileList).toString();
            System.out.println("Best mobile result: " + maxMobile);
            String maxDesctop = Collections.max(desctopList).toString();
            System.out.println("Best desktop result: " + maxDesctop);
            System.out.println("-----------------------------------------------------");
            googlePages.add(new GooglePages(site, maxMobile, maxDesctop));
            writeToCSV(googlePages,csvFileName);
        }
    }

    public void getGoogleSitesParameterstoExcel(ArrayList<String> sites, ArrayList<GooglePages> googlePages, GooglePages g, String workBook, String sheet, PingdomPages pp, WebDriverWait wait) {

        for (String site : sites) {

            pp.inputText(g.getUrlInput(), site);
            System.out.println(site);
            g.getButtonAnalize().click();
            wait.until(ExpectedConditions.visibilityOf(g.getMobileTab()));
            g.getMobileTab().click();
            //String mobileValue = g.getNumberMobile().getAttribute("textContent").replace(" / 100","");
            String mobileValue = g.getMobileOptimizationGrade().getText();
            System.out.println("On mob.: " + mobileValue);
            g.getDesktopTab().click();
            //String desktopValue = g.getNumberDesktop().getAttribute("textContent").replace(" / 100","");
            String desktopValue = g.getDesctopOptimizationGrade().getText();
            System.out.println("On desk.: " + desktopValue);
            googlePages.add(new GooglePages(site, mobileValue,desktopValue));
            writeToExcel(googlePages,workBook, sheet);
        }
    }

    public void writeToCSV(ArrayList<GooglePages> googlePages, String fileName){
        CSVwriter writer = new CSVwriter();
        writer.writeGoogleToCSV(googlePages, fileName);
    }

    public void writeToExcel(ArrayList<GooglePages> googlePages, String workBook, String sheet) {
        JavaExcelApp excel = new JavaExcelApp();
        excel.writeGoogleToExcel(googlePages,workBook,sheet);
    }





}
