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
package au.com.jquant.backtest.strategy.momentum;

import au.com.jquant.analytics.indicators.BollingerBands;
import au.com.jquant.backtest.strategy.Strategy;
import au.com.jquant.execution.Trade;
import java.util.Date;

/**
 * Opens long trades at the close asset price action traded above the upper
 * Bollinger band and price action of previous day was below upper Bollinger
 * band. Trade is closed if duration of (n) days is exceeded or of price change
 * is above (k)%
 *
 * @author davidherod
 * @throws java.lang.Exception
 */
public class BollingerBreakout extends Strategy {

    private final int movingAverage;
    private final double upperStdFactor;
    private final double lowerStdFactor;
    private final double percentageChange;
    private final int holdingPeriod;

    public BollingerBreakout(int movingAverage, double upperStdFactor, double lowerStdFactor, double percentageChange, int holdingPeriod) {
        this.movingAverage = movingAverage;
        this.upperStdFactor = upperStdFactor;
        this.lowerStdFactor = lowerStdFactor;
        this.percentageChange = percentageChange;
        this.holdingPeriod = holdingPeriod;
    }

    @Override
    public void backtest() {
        if (backtestParamIsValid()) {

            for (int i = 0; i < totalPeriods; i++) {
                for (int j = 0; j < assets.size(); j++) {
                    //String symbol = assets.get(j).getSymbol();
                    Date openDate = assets.get(j).getIntervals().get(i).getDate(); 
                    System.out.println(openDate + " ");
//                    double close = assets.get(j).getIntervals().get(i).getClose();
//                    double high = assets.get(j).getIntervals().get(i).getHigh();
//                    double preHigh = assets.get(j).getIntervals().get(i - 1).getHigh();
//                    double openPrice = close;                          
//                    BollingerBands preBands = new BollingerBands(movingAverage, upperStdFactor, lowerStdFactor, assets.get(i + 1).getIntervals());
//                    BollingerBands curBands = new BollingerBands(movingAverage, upperStdFactor, lowerStdFactor, assets.get(i).getIntervals());
//                    
//                    System.out.println(close);
//                    // Open trades
//                    if (high > curBands.getUpperBand() && preHigh < preBands.getUpperBand() && positionOpenable()) {
//                        Trade trade = new Trade(symbol, openDate, openPrice, getPositionValue(), getSlippage(), getBrokerageFee());
//                        trade.open();
//                        System.out.println(trade.toString());
//                        
//                        // Manage open trades
////                        if (percentageChange(close, assets.get(j).getIntervals().get(i + holdingPeriod).getClose()) > percentageChange || getDuration(openDate, currentDate) > holdingPeriod) {                       
////                            trade.close();
////                            trades.add(trade);
////                        }
//                    }
                }
            }
        }
    }


}
