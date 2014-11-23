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
package au.com.jquant.algotrader.strategy.user;

import au.com.jquant.algotrader.strategy.PositionManager;
import au.com.jquant.algotrader.strategy.PreCloseStrategy;
import au.com.jquant.algotrader.strategy.Strategy;
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
// TODO: Evaluate concurrency issues.
public class BollingerBreakout extends Strategy implements PreCloseStrategy, PositionManager {

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
     * Closes all trades that are equal to or have exceeded their maximum
     * holding period.
     */
    private void closeOnDurationCondition() {
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
            if (trade.getSymbolId() == symbolId) { // TODO and days open is > 0
                if (pctChng(trade.getOpenPrice(), lastPrice) >= targetPercentageChange) {
    // CHECK THIS   trade.close();             
                    InteractiveBrokersTrade ibTrade = (InteractiveBrokersTrade)trade;
                    ibTrade.close();
                    openTrades.remove(ibTrade);
                    
                    //openTrades.remove(trade);
                }
            }
        }
    }

    /**
     * Scans and opens positions for assets that meet the open requirements.
     */
    private void scanAndOpen() {
        // TODO Check market data is up to date
        DAOFactory df = new JDBCDAOFactory();
        for (Asset asset : targetAssets) {
            List<Interval> intervals = df.getStockDAO().getHistoricalDaily(asset.getSymbol(), smaLengh +1);
            if (intervals.size() > 0) { // Check that historical exists for symbol.
                double high = intervals.get(0).getHigh();
                double preHigh = intervals.get(1).getHigh();
                double preUpperBand = BollingerBands.calculateUpperBand(2.5, intervals.subList(1, smaLengh + 1));
                double curUpperBand = BollingerBands.calculateUpperBand(2.5, intervals.subList(0, smaLengh));

                if (high > curUpperBand && preHigh < preUpperBand && positionOpenable()) {
                    System.out.println(asset.getSymbol());
                    Trade trade = new InteractiveBrokersTrade(asset,"long", 100, "MKT",this.getClass().getSimpleName());
                    trade.open();
                    openTrades.add(trade);
                }
            }
        }
    }

    @Override
    public void onPreClose() {
        if (openTrades.size() > 0) { // Check if open trades exist and if they need to be closed based on duration rule.
            closeOnDurationCondition();
        }
        scanAndOpen();
    }

    @Override
    public void checkCloseCondition(int symbolId, double lastPrice) {
        closeOnPercentageChange(symbolId, lastPrice);
    }

}
