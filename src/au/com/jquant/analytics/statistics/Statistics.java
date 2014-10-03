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

package au.com.jquant.analytics.statistics;

import au.com.jquant.asset.stock.Stock;
import au.com.jquant.asset.Interval;
import java.util.List;

/**
 *
 * @author davidherod
 */
public class Statistics {

    public static int maxHigherClose(List<Interval> historical) {
        int count = 0;
        int maxCount = 0;
        double preClose = historical.get(0).getClose();
        double close;
        
        for (int i = 1; i < 1000; i++) {  
            if ((close = historical.get(i).getClose()) >= preClose) {
                count++;                               
            } else {
                if (count > maxCount) {
                    maxCount = count;
                    count = 0;
                }
                else count = 0;
            }
            preClose = close;
        }
        return maxCount;
    }

     
    public static int maxLowerClose(List<Interval> historical) {
        int count = 0;
        int maxCount = 0;
        double preClose = historical.get(0).getClose();
        double close;
        
        for (int i = 1; i < 1000; i++) {  
            if ((close = historical.get(i).getClose()) <= preClose) {
                count++;                               
            } else {
                if (count > maxCount) {
                    maxCount = count;
                    count = 0;
                }
                else count = 0;
            }
            preClose = close;
        }
        return maxCount;
    }
    
    public static int maxHigherClose(Stock stock) {
        
        List<Interval> stockQuotes = stock.getIntervals();
        
        int count = 0;
        int maxCount = 0;
        double preClose = stockQuotes.get(0).getClose();
        System.out.println(stockQuotes.get(0).getDate());
        double close;
        
        for (int i = 1; i < stockQuotes.size(); i++) {  

            if ((close = stockQuotes.get(i).getClose()) <= preClose) {
                System.out.println(stockQuotes.get(i).getDate() + " " +close);
                count++; 
            } else {
                System.out.println("\n");
                if (count > maxCount) {
                    maxCount = count;
                    count = 0;
                }else{
                    count = 0;
                }
            }
            preClose = close;
        }
        return maxCount;
    }
       
}
