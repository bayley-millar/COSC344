package util;


import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static java.sql.Date getDate(String time) {
        try {
            SimpleDateFormat dateFormat =
                    new SimpleDateFormat("DD-MM-YYYY");
            Date d = dateFormat.parse(time);
           
            return new java.sql.Date(d.getTime());
        } catch (Exception e) {
            return null;
        }
    }

}
