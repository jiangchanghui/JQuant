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
package au.com.jquant.algotrader.marketdata;

import static au.com.jquant.algotrader.IBAlgoTrader.ibClient;
import static au.com.jquant.algotrader.IBAlgoTrader.strategies;
import au.com.jquant.algotrader.strategy.PositionManager;
import au.com.jquant.algotrader.strategy.RealtimeStrategy;
import au.com.jquant.algotrader.strategy.Strategy;
import au.com.jquant.asset.Asset;
import au.com.jquant.asset.Interval;
import au.com.jquant.execution.Trade;
import com.ib.client.Contract;
import com.ib.client.TagValue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidherod
 */
public class IBDataHandler {

    private final List<Asset> liveTickers;

    public IBDataHandler() {
        liveTickers = new ArrayList<>();
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

            }
            if (s instanceof PositionManager) { // and ticker is not already being used
                for (Trade t : s.getOpenTrades()) {
                    Contract contract = new Contract();
                    contract.m_symbol = t.getSymbol(); //AUD
                    contract.m_secType = (t.getAssetType().equals("Stock") ? "stk" : "err"); // TODO validate input //STK
                    contract.m_exchange = "smart"; //IDEALPRO FIX THIS!!! SO FX can be used
                    contract.m_currency = "usd";

                    ibClient.reqMktData(t.getSymbolId(), contract, null, false, new ArrayList<TagValue>());
                    //liveTickers.add(t.getAssetType());
             
                }
            }
        }
    }

    /**
     * Calls the onTick method of strategies that require real-time date when new data of target assets becomes available via TWS.
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

    public static void cancelMktData() {

    }
    
    private void addTick(int symbolID, double price){
        for(Asset a : liveTickers){
            if(a.getId() == symbolID){
                Interval i = new Interval();
                i.setClose(price);
                //a.setIntervals(a.getIntervals().add(i));
            }
        }
    }

}
