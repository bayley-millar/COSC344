package util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Utility class for storing date methods and variables.
 *
 * @author reuben
 */
public class TimeUtil {
    
    public static final String[] DAYS = {"01", "02", "03", "04", "05", "06", "07",
        "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
        "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
    };
    
    public static final String[] MONTHS = {"01", "02", "03", "04", "05", "06",
        "07", "08", "09", "10", "11", "12" };

    public static String convertStringToDate(Date d) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd-MM-yyyy");
        try {
            dateString = sdfr.format(d);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

}
