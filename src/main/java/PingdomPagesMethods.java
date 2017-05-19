import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by DWork on 19.05.2017.
 */
public class PingdomPagesMethods {

    PingdomPages pp = new PingdomPages();

    public void calculatePerformanceGradeAndLetter(){
        Integer performanceGrade = Integer.parseInt(pp.getPerformanceGrade().getAttribute("textContent").replaceAll("\\D+", ""));//("innerHTML");
        System.out.println(performanceGrade);
        String performanceLetter = "";
        if (performanceGrade >= 90) {
            performanceLetter += "A";
        } else if (performanceGrade >= 80 && performanceGrade < 90) {
            performanceLetter += "B";
        } else if (performanceGrade >= 65 && performanceGrade < 80) {
            performanceLetter += "C";
        } else {
            performanceLetter += "D";
        }
        System.out.println(performanceLetter);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
