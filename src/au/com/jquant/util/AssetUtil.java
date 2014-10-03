/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.jquant.util;

import java.util.Date;
import org.joda.time.Days;

/**
 *
 * @author davidherod
 */
public class AssetUtil {
    
    public static int secondsOpen(Date date1, Date date2){
        //return Days.daysBetween(date1, date2).getDays();
        return 0;
    }
       
    public static int daysOpen(Date date1, Date date2){
        //return Days.daysBetween(date1, date2).getDays();
        return 0;
    }
    
    public static double pctChng(double p1, double p2){
        return (p2 - p1) / p1;
    }
}
