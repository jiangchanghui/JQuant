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
package au.com.jquant.algotrader.strategy.momentum;

import au.com.jquant.algotrader.strategy.RealtimeStrategy;
import au.com.jquant.algotrader.strategy.Strategy;
import au.com.jquant.asset.Asset;
import au.com.jquant.asset.Interval;
import java.util.List;

/**
 *
 * @author davidherod
 */
public class FXTest extends Strategy implements RealtimeStrategy {

    List<Asset> pairs = getTargetAssets();

    @Override
    public void onTick(int symbolId, double lastPrice) {
        addTick(symbolId, lastPrice);
        
    }
    
    public void addTick(int symbolId, double lastPrice){
        for(Asset a: pairs){
            if(a.getId()==symbolId){
            List<Interval> ticks = a.getIntervals();
            Interval i = new Interval();
            i.setClose(lastPrice);
            //a.setIntervals(ticks);
            }
        }
    }

    public static void action() {

    }

}
