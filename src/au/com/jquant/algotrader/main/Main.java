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
package au.com.jquant.algotrader.main;

import au.com.jquant.algotrader.IBAlgoTrader;
import au.com.jquant.algotrader.strategy.user.BollingerBreakout;
import au.com.jquant.algotrader.strategy.Strategy;
import static au.com.jquant.backtest.strategy.Strategy.DATASET_SP500;

/**
 *
 * @author davidherod
 */
public class Main {
    
    private static final String host = null;
    private static final int port = 7496;
    private static final int clientID = 0;

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {


        Strategy bollingerBreakout = new BollingerBreakout(10, 2.5, 0.5, 2);
        bollingerBreakout.setMaxAllocation(20000);
        bollingerBreakout.setMaxPositionsOpen(6);
        bollingerBreakout.setPositionValue(1000);
        bollingerBreakout.setTargetAssets(DATASET_SP500);
        bollingerBreakout.randomiseDataset();

        IBAlgoTrader algoTrader = new IBAlgoTrader(host, port, clientID);
        algoTrader.addStrategy(bollingerBreakout);
        algoTrader.execute();
        
    }
}
