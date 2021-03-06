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
package au.com.jquant.algotrader.strategy;

import au.com.jquant.algotrader.dataset.DatasetFactory;
import au.com.jquant.asset.Asset;
import au.com.jquant.execution.Trade;
import au.com.jquant.execution.TradeJDBCDAO;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author davidherod
 */
public class Strategy {

    private int maxAllocation;
    private int maxPositionsOpen;
    private int positionsOpen;
    private double valuePositionsOpen;
    private double positionValue;
    private int positionSize;
    protected List<Trade> openTrades;
    protected List<Asset> targetAssets;

    // TODO: Retrieve any open trades from database so they can be managed.
    // create interface for where real time position monitoring to close requested

    public Strategy() {
        this.openTrades = new TradeJDBCDAO().getOpenTrades(this.getClass().getSimpleName()); 
        positionsOpen = openTrades.size();
    }
 
    public int getMaxAllocation() {
        return maxAllocation;
    }

    public void setMaxAllocation(int maxAllocation) {
        this.maxAllocation = maxAllocation;
    }

    public int getMaxPositionsOpen() {
        return maxPositionsOpen;
    }

    public void setMaxPositionsOpen(int maxPositionsOpen) {
        this.maxPositionsOpen = maxPositionsOpen;
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

    public List<Trade> getOpenTrades() {
        return openTrades;
    }

    public void setOpenTrades(List<Trade> openTrades) {
        this.openTrades = openTrades;
    }

    public List<Asset> getTargetAssets() {
        return targetAssets;
    }

    public void setTargetAssets(int dataset) throws Exception {
        this.targetAssets = DatasetFactory.getDataset(dataset);
    }
    
    public void setTargetAssets(List<Asset> assetList) throws Exception {
        this.targetAssets = assetList;
    }

    protected boolean positionOpenable() {    
        //!(positionsOpen > maxPositionsOpen || (positionValue + valuePositionsOpen) < maxAllocation);
        return positionsOpen < maxPositionsOpen;
    }
       
    public void randomiseDataset() {
        Collections.shuffle(targetAssets);
    }
    
    protected void adjustPositonValues(){
        positionsOpen++;
    }

}
