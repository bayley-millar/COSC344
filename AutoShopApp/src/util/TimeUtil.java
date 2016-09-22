package util;


import java.sql.Timestamp;
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

    public static Timestamp getTimeStamp(String time) {
        try {
            SimpleDateFormat dateFormat =
                    new SimpleDateFormat("DD-MON-RRHH24:MI:SS.FF");
            Date parsedDate = dateFormat.parse(time);
            return new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            return null;
        }
    }

}
