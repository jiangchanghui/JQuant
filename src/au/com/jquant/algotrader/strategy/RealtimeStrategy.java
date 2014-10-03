/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.jquant.algotrader.strategy;

/**
 *
 * @author davidherod
 */
public interface RealtimeStrategy {
    public void onTick(int symbolId, double lastPrice);
}
