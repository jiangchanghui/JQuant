/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.jquant.algotrader;

import au.com.jquant.algotrader.strategy.RealtimeStrategy;
import au.com.jquant.asset.Asset;
import au.com.jquant.algotrader.strategy.Strategy;
import au.com.jquant.algotrader.timer.PreCloseTimer;
import com.ib.client.EClientSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 *
 * @author davidherod
 */
public class IBAlgoTrader {

    public static List<Strategy> strategies = new ArrayList<>();
    private final Timer preCloseTimer = new Timer("PreCloseTimer");
    private final Date preCloseTime = new Date(); // TODO set preclose date
    private static final IBClient iBClient = new IBClient();
    public static final EClientSocket clientSocket = new EClientSocket(iBClient);

    public IBAlgoTrader() {  
    }

    public void addStrategy(Strategy strategy) {
        strategies.add(strategy);
    }

    public void execute() throws Exception {
        clientSocket.eConnect(null, 7496, 1);
        if (clientSocket.isConnected()) {
            preCloseTimer.schedule(new PreCloseTimer(strategies), preCloseTime, 86400000);
            reqRealTimeData();
        }else{
            throw new Exception("Error: could not connect to TWS");
        }
    }

    public void reqRealTimeData() {
        for (Strategy s : strategies) {
            for (Asset a : s.getRealtimeAssets()) {
                clientSocket.reqMktData(a.getId(), null, null, true, null);
            }
        }
    }

    public static void manageLiveTickData(int symbolId, double price) {
        for (Strategy strategy : strategies) {
            if (strategy instanceof RealtimeStrategy) {
                for (Asset a : strategy.getRealtimeAssets()) {
                    if (a.getId() == symbolId) {
                        RealtimeStrategy realtimeStrategy = (RealtimeStrategy) strategy;
                        realtimeStrategy.onTick(symbolId, price);
                    }
                }
            }
        }
    }
    
    public static EClientSocket getClient(){
        return clientSocket;
    }
}
