import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;

/**
 * Created by Andrey on 13.05.2017.
 */
public class PingdomPages {

    private String performGradeLetter;
    private Integer performGradeNumber;
    private String mbValue;
    private String secValue;
    private WebDriver driver;
    private String url;

    public PingdomPages(String url, String performGradeLetter, Integer performGradeNumber, String mbValue, String secValue){
        this.performGradeLetter = performGradeLetter;
        this.performGradeNumber = performGradeNumber;
        this.mbValue = mbValue;
        this.secValue = secValue;
        this.url = url;

    }

    public PingdomPages(){}



    @FindBy(how = How.CSS,using = "#urlinput")
    private WebElement urlInput;

    @FindBy(how = How.LINK_TEXT,using = "try again")
    private List <WebElement> linkTryAgain;

    @FindBy(how = How.CSS,using = ".rbc-summary-perfgrade .rbc-summary-info-value")
    private WebElement performanceGrade;

    @FindBy(how = How.CSS, using = ".rbc-summary-item.rbc-summary-pagesize .rbc-summary-info-value")
    private WebElement mb;

    @FindBy(how = How.CSS, using = ".button-starttest")
    private WebElement startTest;

    @FindBy(how = How.CSS, using = ".timelineBars")
    private List<WebElement> sec;

    @FindBy(how = How.CSS,using = "div.select")
    private List <WebElement> locationDropdownDiv;

    @FindBy(how = How.CSS,using = ".select.select-custom.select-location.select-hidden")
    private WebElement locationDropDownSelect;

    @FindBy(how = How.CSS,using = "li[data-country = 'USA']")
    private List<WebElement> locationUSA;

    //Methods------------------------------------------------------------------------------------

    //
    public WebElement getLocationDropdownDiv() {
        return locationDropdownDiv.get(0);
    }
    public List <WebElement> getLinkTryAgain() {return linkTryAgain;}
    public WebElement getUrlInput() {return urlInput;}
    public WebElement getPerformanceGrade() {
        return performanceGrade;
    }
    public WebElement getMb() {return mb;}
    public String getPerformGradeLetter() {
        return performGradeLetter;
    }
    public Integer getPerformGradeNumber() {
        return performGradeNumber;
    }
    public String getMbValue() {return mbValue;}
    public String getSecValue() {
        return secValue;
    }
    public List<WebElement> getSec() {return sec;}
    public String getUrl() {
        return url;
    }
    public WebElement getButtonClickStartTest(){return startTest;}
    public void clickOnLocationDropdown(){
        locationDropdownDiv.get(0).click();
    }
    public void clickOnLocationUSA(){locationUSA.get(0).click();}
    public void clickStartTest(){startTest.click();}

    public static void selectByText(WebElement element, String text) {
        Select selectElement = new Select(element);
        selectElement.selectByVisibleText(text);
    }

    public void inputText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    public String getLetterValue(){
        String letter = performanceGrade.getText().toUpperCase();
        return letter;
    }

    @Override
    public String toString() {
        return "PingdomPages{" +
                "performGradeLetter='" + performGradeLetter + '\'' +
                ", performGradeNumber=" + performGradeNumber +
                ", mbValue='" + mbValue + '\'' +
                ", secValue=" + secValue +
                '}';
    }

}
