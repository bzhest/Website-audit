import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

/**
 * Created by Andrey on 13.05.2017.
 */
public class CSVwriter {
    private final static String CSV_SEPARATOR = ";";
    private int counter = 1;

    //Add if p.getUrl contains "vid_" - write siteName + "Vehicle Details"

    public void writeToCSV(List<PingdomPages> pages, String fileName){
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            for (PingdomPages p : pages)
            {
                StringBuffer oneLine = new StringBuffer();
                if(p.getUrl().contains("vid_")){//String with "vid_" is Vehicle Details Page
                    int indexOfThirdSlash = StringUtils.ordinalIndexOf(p.getUrl(), "/",3);
                    String cuttedUrl = p.getUrl().substring(0,indexOfThirdSlash);
                    oneLine.append(counter++ +") " + cuttedUrl + "/Vehicle_Details");
                }else{
                    oneLine.append(counter++ +") " + p.getUrl());
                }
                oneLine.append(CSV_SEPARATOR);
                oneLine.append("  " + p.getPerformGradeLetter());
                //oneLine.append(CSV_SEPARATOR);
                oneLine.append(p.getPerformGradeNumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(p.getMbValue() + " MB");
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(p.getSecValue() + " Sec");
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
}
