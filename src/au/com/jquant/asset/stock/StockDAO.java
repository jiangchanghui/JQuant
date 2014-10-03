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

import au.com.jquant.asset.Interval;
import java.util.List;

/**
 *
 * @author davidherod
 */
public interface StockDAO {
    
    public int createStock(Stock stock);
    public boolean deleteStock(String ticker);
    public int updateStock(Stock stock);
    public Stock findStock(String symbol);
    public List<Stock> findStocks(String[] symbols);
    public List<Stock> getSP500();
    public List<Interval> getSP500HistoricalDaily();
    public List<Interval> getSP500HistoricalDaily(String startDate, String endDate);
    public List<Interval> getHistoricalDaily(String ticker, String startDate, String endDate);
    public List<Interval> getHistoricalDaily(String ticker);
    public int setHistoricalDaily(String ticker, List<Interval> intervals);
}
