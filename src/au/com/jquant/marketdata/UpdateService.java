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

package au.com.jquant.marketdata;

import au.com.jquant.asset.Interval;
import au.com.jquant.asset.stock.StockDAO;
import au.com.jquant.asset.stock.StockJDBCDAO;
import au.com.jquant.factory.dao.DAOFactory;
import au.com.jquant.marketdata.yahoofinance.YahooClient;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author davidherod
 */
public class UpdateService implements Runnable {

    private final String symbol;

    public UpdateService(String symbol) {
        this.symbol = symbol;
    }

    private void updateStock() throws JAXBException, IOException {
        //DAOFactory jdbcDao = DAOFactory.getDAOFactory(DAOFactory.JDBC);       
        //StockDAO stockDao = jdbcDao.getStockDAO();
        StockDAO stockDao = new StockJDBCDAO();
        List<Interval> intervals = YahooClient.getDailyHistoricalData(symbol, "2014-01-01", "2014-11-21");
        stockDao.setHistoricalDaily(symbol, intervals);
    }

    @Override
    public void run() {
        try {
            updateStock();
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(UpdateService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
