import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by DWork on 18.04.2018.
 */
public class Screenshoter {


    public static void takeSnapShot(WebDriver driver, String filePath) {
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(filePath);
        try {
            FileUtils.copyFile(file, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
