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
package au.com.jquant.algotrader.timer;

import au.com.jquant.algotrader.strategy.PreCloseStrategy;
import au.com.jquant.algotrader.strategy.Strategy;
import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author davidherod
 */
public class PreCloseTimer extends TimerTask {

    private final List<Strategy> stratergies;

    public PreCloseTimer(List<Strategy> stratergies) {
        this.stratergies = stratergies;
    }
    
    private void callPreCloseTasks(){
        for(Strategy s : stratergies){
            if(s instanceof PreCloseStrategy){
                PreCloseStrategy preCloseStrategy = (PreCloseStrategy) s;
                preCloseStrategy.onPreClose();
            }         
        }
    }
    
    @Override
    public void run() {
        callPreCloseTasks();
    }    
}
