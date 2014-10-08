/*
 * Copyright 2014 davidherod.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
