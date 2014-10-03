/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.jquant.algotrader.main;

import au.com.jquant.algotrader.IBAlgoTrader;
import au.com.jquant.algotrader.strategy.BollingerBreakout;
import au.com.jquant.algotrader.strategy.Strategy;
import static au.com.jquant.backtest.strategy.Strategy.DATASET_SP500;



/**
 *
 * @author davidherod
 */
public class JQuant {

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
        
    }   
}
