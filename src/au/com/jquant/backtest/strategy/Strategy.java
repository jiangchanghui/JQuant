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
package au.com.jquant.backtest.strategy;

import au.com.jquant.asset.Asset;
import au.com.jquant.execution.Trade;
import au.com.jquant.factory.dao.DAOFactory;
import au.com.jquant.marketdata.DataFactory;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author davidherod
 */
public abstract class Strategy {

    public static int BROKER_BACKTEST = 0;
    public static int BROKER_INTERACTIVE_BROKERS = 1;
    
    public static int DATASET_SP500 = 0;
    

    private double accountValue; // backtest class
    private int maxAllocation; // backtest class
    private int positionsOpen; // backtest class
    private double valuePositionsOpen;
    private int maxPositionsOpen;
    private double positionValue;
    private int positionSize; // backtest class
    private String startDate; // backtest class
    private String endDate; // backtest class
    private double brokerageFee; // backtest class
    private double slippage; // backtest class
    private boolean randomiseDataset; // backtest class
    private int totalTrades; // backtest class
    protected int totalPeriods;// backtest class
    private int totalAssetsTested;
    private int winningTrades;
    private int loosingTrades;
    private int longTrades;
    private int shortTrades;
    private int duration;
    private int broker;
    protected List<Trade> trades;
    protected List<Asset> assets;

    public double getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(double accountValue) {
        this.accountValue = accountValue;
    }

    public int getMaxAllocation() {
        return maxAllocation;
    }

    public void setMaxAllocation(int maxAllocation) {
        this.maxAllocation = maxAllocation;
    }

    public int getPositionsOpen() {
        return positionsOpen;
    }

    public void setPositionsOpen(int positionsOpen) {
        this.positionsOpen = positionsOpen;
    }

    public double getValuePositionsOpen() {
        return valuePositionsOpen;
    }

    public void setValuePositionsOpen(double valuePositionsOpen) {
        this.valuePositionsOpen = valuePositionsOpen;
    }

    public int getMaxPositionsOpen() {
        return maxPositionsOpen;
    }

    public void setMaxPositionsOpen(int maxPositionsOpen) {
        this.maxPositionsOpen = maxPositionsOpen;
    }

    public double getPositionValue() {
        return positionValue;
    }

    public void setPositionValue(double positionValue) {
        this.positionValue = positionValue;
    }

    public int getPositionSize() {
        return positionSize;
    }

    public void setPositionSize(int positionSize) {
        this.positionSize = positionSize;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(double brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public double getSlippage() {
        return slippage;
    }

    public void setSlippage(double slippage) {
        this.slippage = slippage;
    }

    public boolean isRandomiseDataset() {
        return randomiseDataset;
    }

    public void setRandomiseDataset(boolean randomiseDataset) {
        this.randomiseDataset = randomiseDataset;
    }

    public int getTotalTrades() {
        return totalTrades;
    }

    public void setTotalTrades(int totalTrades) {
        this.totalTrades = totalTrades;
    }

    public int getTotalPeriods() {
        return totalPeriods;
    }

    public void setTotalPeriods(int totalPeriods) {
        this.totalPeriods = totalPeriods;
    }

    public int getTotalAssetsTested() {
        return totalAssetsTested;
    }

    public void setTotalAssetsTested(int totalAssetsTested) {
        this.totalAssetsTested = totalAssetsTested;
    }

    public int getWinningTrades() {
        return winningTrades;
    }

    public void setWinningTrades(int winningTrades) {
        this.winningTrades = winningTrades;
    }

    public int getLoosingTrades() {
        return loosingTrades;
    }

    public void setLoosingTrades(int loosingTrades) {
        this.loosingTrades = loosingTrades;
    }

    public int getLongTrades() {
        return longTrades;
    }

    public void setLongTrades(int longTrades) {
        this.longTrades = longTrades;
    }

    public int getShortTrades() {
        return shortTrades;
    }

    public void setShortTrades(int shortTrades) {
        this.shortTrades = shortTrades;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBroker() {
        return broker;
    }

    public void setBroker(int broker) {
        this.broker = broker;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    protected void randomiseDataset() {
            Collections.shuffle(assets);
    }

    protected boolean positionOpenable() {
        return positionsOpen < maxPositionsOpen && (positionValue + valuePositionsOpen) < maxAllocation;
    }
    
    public void setSampleData(int dataset) throws Exception {        
        this.assets = DataFactory.getData(dataset, startDate, endDate);
    }
    
    public void setTargetAssets(int dataset){

    }
    
    /** Checks that all necessary backtest parameters were provided.
     * 
     * @return 
     */
    protected boolean backtestParamIsValid(){
        
        if(getAccountValue() == 0){
           return false; 
        }
        if(getStartDate().isEmpty()){
            return false;
        }
        if(getEndDate().isEmpty()){
            return false;
        }
        if(isRandomiseDataset()){
            randomiseDataset();
        }
        return true;
    }

    public abstract void backtest();
}
