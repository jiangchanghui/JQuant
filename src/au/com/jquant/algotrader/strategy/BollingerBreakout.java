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
package au.com.jquant.algotrader.strategy;

import au.com.jquant.analytics.indicators.BollingerBands;
import au.com.jquant.asset.Asset;
import au.com.jquant.asset.Interval;
import au.com.jquant.execution.InteractiveBrokersTrade;
import au.com.jquant.execution.Trade;
import au.com.jquant.factory.dao.DAOFactory;
import au.com.jquant.factory.dao.JDBCDAOFactory;
import static au.com.jquant.util.AssetUtil.*;
import java.util.Date;
import java.util.List;

/**
 * Opens long trades at pre-close if a given asset traded above its upper
 * Bollinger band, and price action of the previous day was below the previous
 * day upper Bollinger band. Trade is closed if duration of (n) days is exceeded
 * or of price change is above (k) % (pre-close time is defined in
 * IBAlgoTrader).
 *
 * @author davidherod
 * @throws java.lang.Exception
 */
public class BollingerBreakout extends Strategy implements PreCloseStrategy, RealtimeStrategy {

    private final int smaLengh;
    private final double upperStdFactor;
    private final double targetPercentageChange;
    private final int maxHoldingPeriod;

    /**
     *
     * @param movingAverage
     * @param upperStdFactor
     * @param percentageChange
     * @param maxHoldingPeriod
     */
    public BollingerBreakout(int movingAverage, double upperStdFactor, double percentageChange, int maxHoldingPeriod) {
        this.smaLengh = movingAverage;
        this.upperStdFactor = upperStdFactor;
        this.targetPercentageChange = percentageChange;
        this.maxHoldingPeriod = maxHoldingPeriod;
    }

    /**
     * Closes all trades that are equal to or have exceeded their maximum holding
     * period.
     */
    public void closeOnDurationCondition() {
        for (Trade trade : openTrades) {
            if (daysOpen(trade.getOpenDate(), new Date()) >= maxHoldingPeriod) {
                trade.close();
                openTrades.remove(trade);
            }
        }
    }

    /**
     * Closes all trades that are equal to or exceeded the percentage change
     * threshold.
     *
     * @param symbolId
     * @param lastPrice
     */
    private void closeOnPercentageChange(int symbolId, double lastPrice) {
        for (Trade trade : openTrades) {
            if (trade.getSymbolId() == symbolId) {
                if (pctChng(trade.getOpenPrice(), lastPrice) >= targetPercentageChange) {
                    trade.close();
                    openTrades.remove(trade);
                }
            }
        }
    }

    /**
     * Scans and opens positions for assets that meet the open requirements.
     */
    public void scanAndOpen() {
        // TODO Check market data is up to date
        DAOFactory df = new JDBCDAOFactory();
        for (Asset asset : targetAssets) {
            List<Interval> intervals = df.getStockDAO().getHistoricalDaily(asset.getSymbol(), "", "");     // TODO create method that retrieves intervals required for BB calculation based on SMA  
            double high = intervals.get(0).getHigh();
            double preHigh = intervals.get(1).getHigh();
            BollingerBands preBands = new BollingerBands(smaLengh, upperStdFactor, 0, intervals.subList(1, smaLengh +1));
            BollingerBands curBands = new BollingerBands(smaLengh, upperStdFactor, 0, intervals.subList(0, smaLengh));

            // Open trades
            if (high > curBands.getUpperBand() && preHigh < preBands.getUpperBand() && positionOpenable()) {
                Trade trade = new InteractiveBrokersTrade();
                trade.open(); // TODO create method with appropriate parameters.
            }
        }
    }

    @Override
    public void onTick(int symbolId, double lastPrice) {
        closeOnPercentageChange(symbolId, lastPrice);
    }

    @Override
    public void onPreClose() {
        if (openTrades.size() > 0) { // Check if open trades exist and if they need to be closed based on duration rule.
            closeOnDurationCondition();
        }
        scanAndOpen();
    }
}
