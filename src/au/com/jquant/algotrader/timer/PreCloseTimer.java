/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
