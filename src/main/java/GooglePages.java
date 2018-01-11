import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by DWork on 19.05.2017.
 */
public class GooglePages {

    private String mobileValue;
    private String desktopValue;
    private String url;


    public GooglePages(String url, String mobileValue, String desktopValue) {
        this.url = url;
        this.mobileValue = mobileValue;
        this.desktopValue = desktopValue;
    }

    public String getMobileValue() {
        return mobileValue;
    }

    public String getDesktopValue() {
        return desktopValue;
    }

    public GooglePages(){}



    @FindBy(how = How.CSS,using = ".url")
    private WebElement urlInput;

    @FindBy(how = How.CSS,using = ".button.button-red.analyze")
    private WebElement buttonAnalize;

    /*@FindBy(how = How.CSS,using = ".ranking span:nth-child(2)")
    private List<WebElement> numberMobile;

    @FindBy(how = How.CSS,using = ".ranking span:nth-child(2)")
    private List<WebElement> numberDesktop;*/

    @FindBy(how = How.CSS,using = ".speed-report-card-score span")
    private List<WebElement> numberMobile;

    @FindBy(how = How.CSS,using = ".speed-report-card-score span")
    private List<WebElement> numberDesktop;

    @FindBy(how = How.CSS,using = ".ranking")
    private List<WebElement> tabMobile_Desktop;

    @FindBy(css = ".speed-report-card")
    public List<WebElement> blocksOfGrades;

    public WebElement getMobileOptimizationGrade(){
        return blocksOfGrades.get(1).findElement(By.cssSelector(".speed-report-card-score span"));
    }

    public WebElement getDesctopOptimizationGrade(){
        return blocksOfGrades.get(3).findElement(By.cssSelector(".speed-report-card-score span"));
    }

    public WebElement getNumberMobile(){return numberMobile.get(0);}
    public WebElement getNumberDesktop(){return numberDesktop.get(1);}
    public WebElement getUrlInput(){return urlInput;}
    public WebElement getButtonAnalize(){return buttonAnalize;}
    public String getUrl(){return url;}


    public WebElement getMobileTab(){return tabMobile_Desktop.get(0);}
    public WebElement getDesktopTab(){return tabMobile_Desktop.get(1);}

    public void clickOnMobileTab(){getMobileTab().click();}
    public void clickOnDesktopTab(){getDesktopTab().click();}

}
