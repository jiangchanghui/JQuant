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

import au.com.jquant.algotrader.marketdata.IBDataHandler;
import au.com.jquant.algotrader.strategy.Strategy;
import au.com.jquant.algotrader.timing.MarketClock;
import au.com.jquant.algotrader.timing.MarketOpenTimer;
import au.com.jquant.algotrader.timing.MarketTime;
import au.com.jquant.algotrader.timing.PreCloseTimer;
import com.ib.client.EClientSocket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidherod
 */
public class IBAlgoTrader {

    public static List<Strategy> strategies;
    public static EClientSocket ibClient;
    private final MarketClock marketClock;
    public static int nextvalidTradeID;
    
    private final String host;
    private final int port;
    private final int clientID;

    /**
     * 
     * @param host URL of TWS instance.
     * @param port port of TWS instance.
     * @param clientID client ID to be associated with all actions performed.
     */
    public IBAlgoTrader(String host, int port, int clientID) {
        this.host = host;
        this.port = port;
        this.clientID = clientID;
        strategies = new ArrayList<>();
        ibClient = new EClientSocket(new IBWrapper());
        marketClock = new MarketClock();
    }

    /**
     * Adds a strategy to the list of strategies that will be executed.
     *
     * @param strategy a strategy that is to be executed.
     */
    public void addStrategy(Strategy strategy) {
        strategies.add(strategy);
    }

    /**
     * Executes the list of given strategies.
     *
     * @throws Exception if unable to connect to TWS instance.
     */
    public void execute() throws Exception {

        ibClient.eConnect(host, port, clientID);
        //new PreCloseTimer().run();
        //ibClient.reqGlobalCancel();
        if (ibClient.isConnected()) {
            while (true) {

                if (MarketTime.marketIsOpen()) {
                    new IBDataHandler().reqRealTimeData(); //request rt data
                    marketClock.start();
                    Thread.sleep(MarketTime.millisecondsUntilClose());
                    marketClock.stop();
                    // TODO: Wait 15 then update EOD data
                    

                } else {

                    if (MarketTime.millisecondsUntilOpen() - 7200000 > 0) {
                        Thread.sleep(MarketTime.millisecondsUntilOpen() - 7200000); // Allow 2 hours before open to check for time changes due to daylight saving.                 
                    }
                    MarketTime.timeUntilOpen();
                    Thread.sleep(MarketTime.millisecondsUntilOpen());

                    new MarketOpenTimer().callMatketOpenTasks(); // TODO: Run in seperate thread.
                    marketClock.start();
                    Thread.sleep(MarketTime.millisecondsUntilClose());
                    marketClock.stop();
                    // TODO: Wait 15 then update EOD data
                    //new IBDataHandler().reqRealTimeData(); //request rt data
                }
            }

        } else {
            throw new Exception("Error: could not connect to TWS. Please check that TWS is open the correct host and port values are correct.");
        }
    }
}
