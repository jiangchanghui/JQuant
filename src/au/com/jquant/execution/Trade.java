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

    private long id;
    private String symbol;
    private int symbolId;
    private Date openDate;
    private double openPrice;
    private int quantity;
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

    public Trade() {
    }
    
    public Trade(String symbol, Date openDate, double openPrice, double value, double slippage, double brokerage) {
        this.symbol = symbol;
        this.openDate = openDate;
        this.openPrice = openPrice;
        this.value = value;
        this.slippage = slippage;
        this.brokerage = brokerage;

        this.open = true;
        this.quantity = (int) (value / openPrice);
    }

    public Trade(long id, String symbol, Date openDate, double openPrice, int quantity, double value, Date closeDate, double closePrice, double signalOpenPrice, double signalClosePrice, double profitLoss, double profitLossPercentage, double slippage, double brokerage, int duration, double maxNegativeEquity, double maxPositiveEquity, boolean open) {
        this.id = id;
        this.symbol = symbol;
        this.openDate = openDate;
        this.openPrice = openPrice;
        this.quantity = quantity;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
