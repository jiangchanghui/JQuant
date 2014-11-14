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
package au.com.jquant.algotrader;

import au.com.jquant.algotrader.strategy.FiveMinuteStrategy;
import au.com.jquant.algotrader.strategy.OpenStrategy;
import au.com.jquant.algotrader.strategy.PreCloseStrategy;
import au.com.jquant.algotrader.strategy.PositionManager;
import au.com.jquant.algotrader.strategy.RealtimeStrategy;
import au.com.jquant.asset.Asset;
import au.com.jquant.algotrader.strategy.Strategy;
import au.com.jquant.algotrader.timer.PreCloseTimer;
import au.com.jquant.execution.Trade;
import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.ib.client.TagValue;
import com.ib.contracts.StkContract;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 *
 * @author davidherod
 */
public class IBAlgoTrader {

    public static List<Strategy> strategies = new ArrayList<>();
    private Timer fiveMinuteTimer;
    private Timer preCloseTimer;
    private Timer openTimer;
    private final Date preCloseTime = new Date(); // TODO set preclose time
    public static final EClientSocket ibClient = new EClientSocket(new IBWrapper());
    private final List<Asset> liveTickers = new ArrayList<>();
    public static int nextvalidTradeID;
    private boolean marketOpen;

    public IBAlgoTrader() {
    }

    /**
     * Adds a strategy to list of strategies that will be executed.
     *
     * @param strategy
     */
    public void addStrategy(Strategy strategy) {
        strategies.add(strategy);
    }

    /**
     * Executes the list of given strategies.
     *
     * @throws Exception
     */
    public void execute() throws Exception {
        ibClient.eConnect(null, 7496, 0);
        if (ibClient.isConnected()) {
            startTimers();
            reqRealTimeData();
        } else {
            throw new Exception("Error: could not connect to TWS");
        }
    }

    /**
     * Requests real-time data for assets of strategies that require it.
     */
    public void reqRealTimeData() {
        for (Strategy s : strategies) {
            if (s instanceof RealtimeStrategy) {
                for (Asset a : s.getTargetAssets()) {
                    //ibClient.reqMktData(a.getId(), new StkContract(a.getSymbol()), null, false, new ArrayList<TagValue>());
                    
                    Contract contract = new Contract();
                    contract.m_symbol = "aud"; //AUD
                    contract.m_secType = "cash"; //CASH
                    contract.m_exchange = "IDEALPRO"; //IDEALPRO
                    contract.m_currency = "USD";
                     ibClient.reqMktData(a.getId(), contract, null, false, new ArrayList<TagValue>());
                }
                if (s instanceof PositionManager) { // and ticker is not already being used
                    for (Trade t : s.getOpenTrades()) {
                        ibClient.reqMktData(t.getId(), new StkContract(t.getSymbol()), null, false, new ArrayList<TagValue>());
                    }
                }
            }
        }
    }

    /**
     * Calls the onTick method of strategies that require real-time date when
     * new data of target assets becomes available via TWS.
     *
     * @param symbolId
     * @param price
     */
    public static void manageLiveTickData(int symbolId, double price) {
        for (Strategy strategy : strategies) {
            if (strategy instanceof RealtimeStrategy) {
                for (Asset a : strategy.getTargetAssets()) {
                    if (a.getId() == symbolId) {
                        RealtimeStrategy realtimeStrategy = (RealtimeStrategy) strategy;
                        realtimeStrategy.onTick(symbolId, price);
                    }
                }
            }
        }
    }

    /**
     * Starts the timers required by the list of strategies.
     */
    private void startTimers() {
        
        // TODO Check time for changes  
        // TODO Check timezone is New York
        
        //boolean fiveMinuteIsRunning = false;
        boolean preCloseIsRunning = false;
        boolean openStrategyIsRunning = false;

        for (Strategy s : strategies) {
            
            if (s instanceof PreCloseStrategy && !preCloseIsRunning) {
                preCloseTimer = new Timer("PreCloseTimer");
                preCloseTimer.schedule(new PreCloseTimer(strategies), preCloseTime, 86400000);
                preCloseIsRunning = true;
            }
            if (s instanceof OpenStrategy && !openStrategyIsRunning) {
                openTimer = new Timer("OpenTimer");
                openTimer.schedule(new PreCloseTimer(strategies), preCloseTime, 86400000);
                preCloseIsRunning = true;
            }
        }
    }

}
