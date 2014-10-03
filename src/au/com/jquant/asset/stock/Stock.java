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
package au.com.jquant.asset.stock;

import au.com.jquant.asset.Asset;
import au.com.jquant.asset.Interval;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davidherod
 */
@XmlRootElement(name = "query")
@XmlAccessorType(XmlAccessType.FIELD)
public class Stock extends Asset {

    private String industryDevision;
    private String industrySector;
    private String industryGroup;
    private long marketCap;

    @XmlElementWrapper(name = "results")
    @XmlElement(name = "quote")
    private List<Interval> intervals;

    /**
     * Creates an empty stock object.
     */
    public Stock() {
    }

    /**
     * Creates a stock object with interval data retrieved for YAHOO Finance.
     *
     * @param ticker
     * @param startDate
     * @param endDate
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     */
    public Stock(String ticker, String startDate, String endDate) throws JAXBException, IOException {
        StockJDBCDAO stockJDBCDAO = new StockJDBCDAO();

        this.intervals = stockJDBCDAO.getHistoricalDaily(ticker);
    }

    /**
     * Creates a stock object with interval data from CSV.
     *
     * @param ticker
     * @param file
     */
    public Stock(String ticker, File file) {
        // TODO add file support
    }

    @Override
    public List<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
    }

    public String getIndustryDevision() {
        return industryDevision;
    }

    public void setIndustryDevision(String industryDevision) {
        this.industryDevision = industryDevision;
    }

    public String getIndustrySector() {
        return industrySector;
    }

    public void setIndustrySector(String industrySector) {
        this.industrySector = industrySector;
    }

    public String getIndustryGroup() {
        return industryGroup;
    }

    public void setIndustryGroup(String industryGroup) {
        this.industryGroup = industryGroup;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    /**
     * Returns a string representation of the stock object.
     *
     * @return
     */
    @Override
    public String toString() {
        return (getSymbol() + " " + getDescription() + " " + industryDevision + " " + industryGroup + " " + industrySector + " " + marketCap);
    }

}
