/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.jquant.util;

import java.util.Date;

/**
 * A utility class used to perform asset related calculations.
 * @author davidherod
 */
public class AssetUtil {
    
    /**
     * Calculates the duration of a trade in seconds.
     * @param date1
     * @param date2
     * @return 
     */
    public static int secondsOpen(Date date1, Date date2){
        //return Days.daysBetween(date1, date2).getDays();
        return 0;
    }
       
    /**
     * Calculates the duration of a trade in days.
     * @param date1
     * @param date2
     * @return 
     */
    public static int daysOpen(Date date1, Date date2){
        //return Days.daysBetween(date1, date2).getDays();
        return 0;
    }
    
    /**
     * Calculates the percentage change of an asset.
     * @param p1
     * @param p2
     * @return 
     */
    public static double pctChng(double p1, double p2){
        return (p2 - p1) / p1;
    }
}
