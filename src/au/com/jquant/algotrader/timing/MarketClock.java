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
import au.com.jquant.algotrader.strategy.FiveMinuteStrategy;
import au.com.jquant.algotrader.strategy.PreCloseStrategy;
import au.com.jquant.algotrader.strategy.Strategy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author davidherod
 */
public class MarketClock {
    
    ScheduledExecutorService scheduledExecutorService;

    public MarketClock() {
        this.scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    }
    
    

    public void start() {
        boolean preCloseCalled = false;
        boolean fiveMinuteCalled = false;

        for (Strategy s : strategies) {
            if (s instanceof PreCloseStrategy && MarketTime.millisecondsUntilClose() > 300000 && !preCloseCalled) {
                scheduledExecutorService.schedule(new PreCloseTimer(), MarketTime.millisecondsUntilPreClose(), TimeUnit.MILLISECONDS);
            }
            
            if (s instanceof FiveMinuteStrategy && !fiveMinuteCalled) {
                scheduledExecutorService.schedule(new IntervalTimer(), MarketTime.millisecondsUntilPreClose(), TimeUnit.MILLISECONDS);
            }
        }
    }

    public void stop() {
        scheduledExecutorService.shutdown();
    }
}
