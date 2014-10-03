/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.jquant.algotrader.timer;

import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author davidherod
 */
public class BarTimer extends TimerTask {

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//   // List<Strategy> stratergies;
//    int oneMinute;
//    int fiveMinute;
//    int tenMinute;
//    int twentyMinute;
//    int thirtyMinute;
//    int oneHour;
//    int fourHour;
//    int sixtyMinute;
//
//    private void incrementTime() {
//        fiveMinute++;
//        tenMinute++;
//        twentyMinute++;
//        thirtyMinute++;
//        sixtyMinute++;
//    }
//
//    private void callPreCloseTasks() {
//        for (Strategy s : stratergies) {
//            s.onPreClose();
//        }
//        increment();
//    }
//
//    @Override
//    public void run() {
//        for (Strategy s : stratergies) {
//            s.onOneMintuteBar;
//        }
//
//        if (fiveMinute == 5) {
//            for (Strategy s : stratergies) {
//                s.onFiveMintuteBar;
//            }
//            fiveMinute = 0;
//        }
//    }
}
