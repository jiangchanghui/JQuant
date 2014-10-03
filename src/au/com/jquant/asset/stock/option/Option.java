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

package au.com.jquant.asset.stock.option;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author davidherod
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Option {
    
    @XmlAttribute(name = "type")
    private String type;
    @XmlAttribute(name = "symbol")
    private String symbol;
    @XmlElement(name = "strikePrice")
    private double strike;
    @XmlElement(name = "lastPrice")
    private double lastPrice;
    @XmlElement(name = "change")
    private double change;
    @XmlElement(name = "changeDir")
    private double changeDir;
    @XmlElement(name = "bid")
    private double bid;
    @XmlElement(name = "ask")
    private double ask;
    @XmlElement(name = "vol")
    private long volume;
    @XmlElement(name = "openInt")
    private long openInterest;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getStrike() {
        return strike;
    }

    public void setStrike(double strike) {
        this.strike = strike;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getChangeDir() {
        return changeDir;
    }

    public void setChangeDir(double changeDir) {
        this.changeDir = changeDir;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public long getOpenInterest() {
        return openInterest;
    }

    public void setOpenInterest(long openInterest) {
        this.openInterest = openInterest;
    }
    
}
