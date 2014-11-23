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

import au.com.jquant.algotrader.IBAlgoTrader;
import au.com.jquant.algotrader.strategy.RealtimeStrategy;
import au.com.jquant.algotrader.strategy.Strategy;
import au.com.jquant.execution.InteractiveBrokersTrade;

/**
 *
 * @author davidherod
 */
public class Test extends Strategy implements RealtimeStrategy{

    private int i = 20;
    @Override
    public void onTick(int symbolId, double lastPrice) {
        //i++;
        //System.out.println(symbolId + " " + lastPrice);
        System.out.println("test on tick");
        //InteractiveBrokersTrade t = new InteractiveBrokersTrade("spy", 10, "stk", "long", 100, "mkt", 100);
        //t.setId(i);
        //t.open();
       
    }
    
}
