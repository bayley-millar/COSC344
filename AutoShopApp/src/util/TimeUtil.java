package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import oracle.sql.DATE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author reuben
 */
public class TimeUtil {

    public static String convertStringToDate(Date d) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("DD-MM-YYYY");
        /*you can also use DateFormat reference instead of SimpleDateFormat 
    * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
         */
        try {
            dateString = sdfr.format(d);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

}
