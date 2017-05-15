import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.List;

/**
 * Created by Andrey on 13.05.2017.
 */
public class PingdomPages {

    private String letterValue;
    private String numberValue;
    private String mbValue;
    private String secValue;
    private WebDriver driver;

    public PingdomPages(String letterValue, String numberValue, String mbValue, String secValue){
        this.letterValue = getLetterValue();
        this.numberValue = getNumberValue();
        this.mbValue = getMbValue();
        this.secValue = getSecValue();
    }

    public PingdomPages(){}



    @FindBy(how = How.CSS,using = "#urlinput")


    private WebElement urlInput;

    @FindBy(how = How.CSS,using = ".rbc-summary-perfgrade .rbc-summary-info-value")
    private WebElement performanceGrade;

    @FindBy(how = How.CSS, using = ".rbc-summary-item.rbc-summary-pagesize .rbc-summary-info-value")
    private WebElement mb;

    @FindBy(how = How.CSS, using = ".timelineBars")
    private List<WebElement> sec;

    @FindBy(how = How.CSS,using = "div.select")
    private List <WebElement> locationDropdownDiv;

    @FindBy(how = How.CSS,using = ".select.select-custom.select-location.select-hidden")
    private WebElement locationDropDownSelect;

    @FindBy(how = How.CSS,using = "li[data-country = 'USA']")
    private List<WebElement> locationUSA;

    //Methods------------------------------------------------------------------------------------

    public WebElement getLocationDropdownDiv() {
        return locationDropdownDiv.get(0);
    }

    public WebElement getLocationDropdownSelect() {
        return locationDropDownSelect;
    }

    public WebElement getUrlInput() {return urlInput;}
    public WebElement getPerformanceGrade() {return performanceGrade;}

    public static void selectByText(WebElement element, String text) {
        Select selectElement = new Select(element);
        selectElement.selectByVisibleText(text);
    }

    public void inputText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    public void clickOnLocationDropdown(){
        locationDropdownDiv.get(0).click();
    }

    public void clickOnLocationUSA(){
        locationUSA.get(0).click();
    }


    public String getLetterValue(){
        String letter = performanceGrade.getText().toUpperCase();
        return letter;
    }

    public String getNumberValue(){
        return "";
    }

    public String getMbValue(){
        return "";
    }
    public String getSecValue(){
        return "";
    }






}
