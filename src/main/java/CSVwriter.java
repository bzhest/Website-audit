import java.io.*;
import java.util.List;

/**
 * Created by Andrey on 13.05.2017.
 */
public class CSVwriter {
    private final static String CSV_SEPARATOR = ";";

    public void writeToCSV(List<PingdomPages> pages){
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("new_CSV_file"), "UTF-8"));
            for (PingdomPages p : pages)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append("url - " + p.getUrl());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append("Performance grade - " + p.getPerformGradeLetter());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(" " + p.getPerformGradeNumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append("page size - " + p.getMbValue());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append("page loading time - " + p.getSecValue());
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
