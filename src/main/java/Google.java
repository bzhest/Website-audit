import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by DWork on 19.05.2017.
 */
public class Google {


    public Google(String mobileValue, String desktopValue) {
        this.mobileValue = mobileValue;
        this.desktopValue = desktopValue;
    }

    private String mobileValue;//
    private String desktopValue;// = getNumberDesktop().getAttribute("textContent").replace(" / 100","");

    @FindBy(how = How.CSS,using = ".url.label-input-label")
    private WebElement urlInput;

    @FindBy(how = How.CSS,using = ".button.button-red.analyze")
    private WebElement buttonAnalize;

    @FindBy(how = How.CSS,using = ".ranking .score.error")
    private WebElement numberMobile;

    @FindBy(how = How.CSS,using = ".ranking .score.warning")
    private WebElement numberDesktop;

    @FindBy(how = How.CSS,using = ".ranking")
    private List<WebElement> tabMobile_Desktop;

    public WebElement getNumberMobile(){return numberMobile;}
    public WebElement getNumberDesktop(){return numberDesktop;}
    public WebElement getUrlInput(){return urlInput;}
    public WebElement getButtonAnalize(){return buttonAnalize;}


    public WebElement getMobileTab(){return tabMobile_Desktop.get(0);}
    public WebElement getDesktopTab(){return tabMobile_Desktop.get(1);}

    public void clickOnMobileTab(){getMobileTab().click();}
    public void clickOnDesktopTab(){getDesktopTab().click();}

}
