import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 17.05.2017.
 */
public enum Sites {
    PINGDOM ("https://tools.pingdom.com/#!/"),
    THEEUROPEANMASTERS("http://www.theeuropeanmasters.com/");

    private String site;

    private Sites(String site){
        this.site = site;
    }
    public String getSite(){
        return site;
    }

}
