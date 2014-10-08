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

import au.com.jquant.algotrader.strategy.RealtimeStrategy;
import au.com.jquant.asset.Asset;
import au.com.jquant.algotrader.strategy.Strategy;
import au.com.jquant.algotrader.timer.PreCloseTimer;
import com.ib.client.EClientSocket;
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
    private final Timer preCloseTimer = new Timer("PreCloseTimer");
    private final Date preCloseTime = new Date(); // TODO set preclose date
    private static final IBClient iBClient = new IBClient();
    public static final EClientSocket clientSocket = new EClientSocket(iBClient);

    public IBAlgoTrader() {  
    }

    public void addStrategy(Strategy strategy) {
        strategies.add(strategy);
    }

    public void execute() throws Exception {
        clientSocket.eConnect(null, 7496, 1);
        if (clientSocket.isConnected()) {
            preCloseTimer.schedule(new PreCloseTimer(strategies), preCloseTime, 86400000);
            reqRealTimeData();
        }else{
            throw new Exception("Error: could not connect to TWS");
        }
    }

    public void reqRealTimeData() {
        for (Strategy s : strategies) {
            for (Asset a : s.getRealtimeAssets()) {
                clientSocket.reqMktData(a.getId(), null, null, true, null);
            }
        }
    }

    public static void manageLiveTickData(int symbolId, double price) {
        for (Strategy strategy : strategies) {
            if (strategy instanceof RealtimeStrategy) {
                for (Asset a : strategy.getRealtimeAssets()) {
                    if (a.getId() == symbolId) {
                        RealtimeStrategy realtimeStrategy = (RealtimeStrategy) strategy;
                        realtimeStrategy.onTick(symbolId, price);
                    }
                }
            }
        }
    }
  
}
