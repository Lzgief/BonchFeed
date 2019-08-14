package ss.kupp.bonchfeed.Presenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tagRemover {

    public static String removeTags(String in){
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(in);
        return matcher.replaceAll("");
    }

}
