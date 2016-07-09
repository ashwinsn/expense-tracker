package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anatarajan on 6/26/16.
 */
public class StringUtils {

    public static List<String> stringSplitIgnoreTokenInsideQuotes(String source, String token){
        List<String> output = new ArrayList<String>();

        /*
         * Anything that is in between quotes or anything that is not , or space or quotes
         */
        Pattern stringSplitPattern = Pattern.compile("(\".*?\"|[^" + token + "\"]+)");
        Matcher matcher = stringSplitPattern.matcher(source);

        while(matcher.find()){
            String match = org.apache.commons.lang3.StringUtils.strip(matcher.group(1),"\" ");
            if(match.length() > 0) {
                output.add(match);
            }
        }
        return output;
    }
}
