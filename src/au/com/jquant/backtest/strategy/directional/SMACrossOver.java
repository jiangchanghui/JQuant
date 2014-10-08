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

package au.com.jquant.backtest.strategy.directional;

import au.com.jquant.asset.Interval;
import au.com.jquant.execution.Trade;
import java.util.ArrayList;
import java.util.List;

/**
     * Opens long trades when price crosses over the simple moving average.
     * @author davidherod
     * @throws java.lang.Exception
     */
public class SMACrossOver {
    
     /**
      * 
      * @param smaInterval
      * @param intervals
      * @return
      * @throws Exception 
      */
    public List<Trade> backtest(int smaInterval, List<Interval> intervals) throws Exception {
        List<Trade> trades;
        double previousSMA = 0;

        /* 
         * Check that enough data exists to perform legal trades.
         */
        if (intervals.size() > smaInterval) {
            trades = new ArrayList<>();

            // Get previous SMA.
            for (int i = intervals.size() - 1; i >= intervals.size() - smaInterval; i--) {
                previousSMA += intervals.get(i).getClose() / smaInterval;
            }

            // Iterate over data in reverse order.
            for (int i = intervals.size() - smaInterval; i >= 0; i--) {
                double sma = 0;

                for (int j = i; j < smaInterval + i; j++) {
                    sma += intervals.get(j).getClose() / smaInterval;
                }

                /* Check for SMA cross-over.
                 *
                 * If previous close < previousSMA && current close > current SMA.
                 */
                if (intervals.get(i + 1).getClose() < previousSMA && intervals.get(i).getClose() > sma) {
                    //Trade trade = new Trade(symbol, openDate, openPrice, getPositionValue(), getSlippage(), getBrokerageFee());
                    //trade.setOpenDate(intervals.get(i).getDate());
                    //trade.setOpenPrice(intervals.get(i).getClose());
                    //trades.add(trade);
                }
                
                // TODO Manage trades
                previousSMA = sma;
            }
        } else {
            throw new Exception("Insufficient data");
        }
        return trades;
    }
}
