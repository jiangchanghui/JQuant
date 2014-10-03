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

import au.com.jquant.asset.AssetJDBCDAO;
import au.com.jquant.asset.stock.StockDAO;
import au.com.jquant.asset.stock.StockJDBCDAO;
import au.com.jquant.execution.TradeDAO;
import au.com.jquant.execution.TradeJDBCDAO;
import au.com.jquant.util.PropertiesUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author davidherod
 */
public class JDBCDAOFactory extends DAOFactory {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String CONNECTION_URL = PropertiesUtil.getProperty("CONNECTION_STRING");
    private static final String USERNAME = PropertiesUtil.getProperty("USERNAME");
    private static final String PASSWORD = PropertiesUtil.getProperty("PASSWORD");

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
    }

    @Override
    public StockDAO getStockDAO() {
        return new StockJDBCDAO();
    }
    
    @Override
    public AssetJDBCDAO getAssetDAO() {
        return new AssetJDBCDAO();
    }

    @Override
    public TradeDAO getTradeDAO() {
        return new TradeJDBCDAO();
    }
}
