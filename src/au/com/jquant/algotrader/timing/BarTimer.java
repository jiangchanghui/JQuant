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

/**
 *
 * @author davidherod
 */
public class BarTimer implements Runnable{

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