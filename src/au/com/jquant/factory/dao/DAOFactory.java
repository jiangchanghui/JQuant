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

package au.com.jquant.factory.dao;

import au.com.jquant.asset.AssetDAO;
import au.com.jquant.asset.stock.StockDAO;
import au.com.jquant.execution.TradeDAO;

/**
 *
 * @author davidherod
 */
public abstract class DAOFactory {

    public static final int JDBC = 1;

    public abstract StockDAO getStockDAO();
    public abstract AssetDAO getAssetDAO();
    public abstract TradeDAO getTradeDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            case JDBC:
                return new JDBCDAOFactory();
            default:
                return null;
        }

    }

    public static class getDAOFactory {

        public getDAOFactory(int JDBC) {
        }
    }
}
