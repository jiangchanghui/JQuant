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
package au.com.jquant.algotrader.timing;

import static au.com.jquant.algotrader.IBAlgoTrader.strategies;
import au.com.jquant.algotrader.strategy.MarketOpenStrategy;
import au.com.jquant.algotrader.strategy.Strategy;

/**
 *
 * @author davidherod
 */
public class MarketOpenTimer implements Runnable{
    
    public void callMatketOpenTasks(){
        for(Strategy s : strategies){
            if(s instanceof MarketOpenStrategy){
                MarketOpenStrategy marketOpenStrategy = (MarketOpenStrategy) s;
                marketOpenStrategy.onMarketOpen();
            }         
        }
    }

    @Override
    public void run() {
        callMatketOpenTasks();
    }
}
