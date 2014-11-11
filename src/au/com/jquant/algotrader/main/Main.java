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
import au.com.jquant.algotrader.strategy.momentum.BollingerBreakout;
import au.com.jquant.algotrader.strategy.Strategy;
import static au.com.jquant.backtest.strategy.Strategy.DATASET_SP500;



/**
 *
 * @author davidherod
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        Strategy bollingerBreakout = new BollingerBreakout(10, 2.5, 0.5, 2);

        bollingerBreakout.setMaxAllocation(20000);
        bollingerBreakout.setMaxPositionsOpen(10);
        bollingerBreakout.setPositionValue(1000);
        bollingerBreakout.setTargetAssets(DATASET_SP500);
        bollingerBreakout.setRandomiseDataset(true);
        
        IBAlgoTrader algoTrader = new IBAlgoTrader();
        algoTrader.addStrategy(bollingerBreakout);

        algoTrader.execute();
//        
//        
//        
//        Strategy test = new Test();
//        test.setTargetAssets(null);
//        
//        List<Asset> testTargetAsset = new ArrayList<>();
//        Asset spy = new Stock();
//        spy.setId(1);
//        spy.setSymbol("spy");
//        testTargetAsset.add(spy);
//        test.setTargetAssets(testTargetAsset);
        //        algoTrader.addStrategy(test);
//
        

        
//        IBAlgoTrader ib = new IBAlgoTrader();    
//        ib.execute();
//        Contract contract = new Contract();
//        contract.m_secType = "stk";
//        contract.m_symbol = "spy";
//        contract.m_exchange = "smart";
//        contract.m_currency = "usd";
//        IBAlgoTrader.ibClient.reqHistoricalData(1, contract, "20140101 00:00:00", "1 Y", "1 day", "TRADES", 1, 1, new Vector<TagValue>());
         
//        int count = 0;
//        List<Asset> assets = DatasetFactory.getDataset(0);
//        for (Asset a : assets) {
//            System.out.println(count++);
//            System.out.println(a.getSymbol());
//            Contract contract = new Contract();
//            contract.m_secType = "stk";
//            contract.m_symbol = a.getSymbol();
//            contract.m_exchange = "smart";
//            contract.m_currency = "usd";
//            
//            //IBAlgoTrader.ibClient.reqHistoricalData(count, contract, "20140101 00:00:00", "1 Y", "1 day", "TRADES", 1, 1, new Vector<TagValue>());
//            //IBAlgoTrader.ibClient.reqRealTimeBars(count, contract, 5, "MIDPOINT", false, new Vector<TagValue>());
//            
//            IBAlgoTrader.ibClient.reqMktData(count, contract, "", false, new Vector<TagValue>());
//            Thread.sleep(1000);
//            
//            if(count > 10)
//                break;
//        }
    }
}
