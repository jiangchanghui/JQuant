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
package au.com.jquant.execution;

import java.util.Date;
import java.util.List;

/**
 *
 * @author davidherod
 */
public class Trade {

    private int id;
    private String symbol;
    private int symbolId;
    private String assetType;
    private String positionType;
    private Date openDate;
    private double openPrice;
    private int quantity;
    private String orderType;
    private double value;
    private Date closeDate;
    private double closePrice;
    private double signalOpenPrice;
    private double signalClosePrice;
    private double profitLoss;
    private double profitLossPercentage;
    private double slippage;
    private double brokerage;
    private int duration;
    private double maxNegativeEquity;
    private double maxPositiveEquity;
    private boolean open;

    /**
     * Constructor with all required parameters to open a new trade.
     * @param symbol
     * @param symbolId
     * @param assetType
     * @param positionType
     * @param quantity
     * @param orderType
     * @param signalOpenPrice 
     */
    public Trade( String symbol, int symbolId, String assetType, String positionType, int quantity, String orderType, double signalOpenPrice) {
        this.symbol = symbol;
        this.symbolId = symbolId;
        this.assetType = assetType;
        this.positionType = positionType;
        this.quantity = quantity;
        this.orderType = orderType;
        this.signalOpenPrice = signalOpenPrice;
        
        this.open = true;
        this.id = 1; // TODO: create id generator;
    }

    /** Constructor with all required parameters to create an open-trade instance.
     * 
     * @param id
     * @param symbol
     * @param symbolId
     * @param assetType
     * @param positionType
     * @param openDate
     * @param openPrice
     * @param quantity
     * @param orderType
     * @param value
     * @param signalOpenPrice
     * @param open 
     */
    public Trade(int id, String symbol, int symbolId, String assetType, String positionType, Date openDate, double openPrice, int quantity, String orderType, double value, double signalOpenPrice, boolean open) {
        this.id = id;
        this.symbol = symbol;
        this.symbolId = symbolId;
        this.assetType = assetType;
        this.positionType = positionType;
        this.openDate = openDate;
        this.openPrice = openPrice;
        this.quantity = quantity;
        this.orderType = orderType;
        this.value = value;
        this.signalOpenPrice = signalOpenPrice;
        this.open = open;
    }
    
    /**
     * Constructor with all required parameters to create a closed-trade instance.
     * @param id
     * @param symbol
     * @param symbolId
     * @param assetType
     * @param positionType
     * @param openDate
     * @param openPrice
     * @param quantity
     * @param orderType
     * @param value
     * @param closeDate
     * @param closePrice
     * @param signalOpenPrice
     * @param signalClosePrice
     * @param profitLoss
     * @param profitLossPercentage
     * @param slippage
     * @param brokerage
     * @param duration
     * @param maxNegativeEquity
     * @param maxPositiveEquity
     * @param open 
     */
    public Trade(int id, String symbol, int symbolId, String assetType, String positionType, Date openDate, double openPrice, int quantity, String orderType, double value, Date closeDate, double closePrice, double signalOpenPrice, double signalClosePrice, double profitLoss, double profitLossPercentage, double slippage, double brokerage, int duration, double maxNegativeEquity, double maxPositiveEquity, boolean open) {
        this.id = id;
        this.symbol = symbol;
        this.symbolId = symbolId;
        this.assetType = assetType;
        this.positionType = positionType;
        this.openDate = openDate;
        this.openPrice = openPrice;
        this.quantity = quantity;
        this.orderType = orderType;
        this.value = value;
        this.closeDate = closeDate;
        this.closePrice = closePrice;
        this.signalOpenPrice = signalOpenPrice;
        this.signalClosePrice = signalClosePrice;
        this.profitLoss = profitLoss;
        this.profitLossPercentage = profitLossPercentage;
        this.slippage = slippage;
        this.brokerage = brokerage;
        this.duration = duration;
        this.maxNegativeEquity = maxNegativeEquity;
        this.maxPositiveEquity = maxPositiveEquity;
        this.open = open;
    }
    
    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }
    
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getSymbolId() {
        return symbolId;
    }

    public void setSymbolId(int symbolId) {
        this.symbolId = symbolId;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getSignalOpenPrice() {
        return signalOpenPrice;
    }

    public void setSignalOpenPrice(double signalOpenPrice) {
        this.signalOpenPrice = signalOpenPrice;
    }

    public double getSignalClosePrice() {
        return signalClosePrice;
    }

    public void setSignalClosePrice(double signalClosePrice) {
        this.signalClosePrice = signalClosePrice;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }

    public double getProfitLossPercentage() {
        return profitLossPercentage;
    }

    public void setProfitLossPercentage(double profitLossPercentage) {
        this.profitLossPercentage = profitLossPercentage;
    }

    public double getSlippage() {
        return slippage;
    }

    public void setSlippage(double slippage) {
        this.slippage = slippage;
    }

    public double getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(double brokerage) {
        this.brokerage = brokerage;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getMaxNegativeEquity() {
        return maxNegativeEquity;
    }

    public void setMaxNegativeEquity(double maxNegativeEquity) {
        this.maxNegativeEquity = maxNegativeEquity;
    }

    public double getMaxPositiveEquity() {
        return maxPositiveEquity;
    }

    public void setMaxPositiveEquity(double maxPositiveEquity) {
        this.maxPositiveEquity = maxPositiveEquity;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void open() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.  
    }

    public void printTrades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void toCSV(List<Trade> trades, String directory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
