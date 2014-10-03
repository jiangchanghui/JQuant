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

package au.com.jquant.util;

/**
 *
 * @author davidherod
 */
import au.com.jquant.asset.stock.Stock;
import au.com.jquant.asset.stock.StockJDBCDAO;
import au.com.jquant.marketdata.UpdateService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {

    public void updateSP500() {
        ExecutorService executor = Executors.newFixedThreadPool(15);
        StockJDBCDAO stockDao = new StockJDBCDAO();
        List<Stock> stocks = stockDao.getSP500();
        for (Stock s : stocks) {
            Runnable worker = new UpdateService(s.getSymbol());
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

}
