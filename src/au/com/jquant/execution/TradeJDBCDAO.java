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

import au.com.jquant.asset.stock.StockJDBCDAO;
import au.com.jquant.factory.dao.JDBCDAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidherod
 */
public class TradeJDBCDAO implements TradeDAO {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    @Override
    public List<Trade> getOpenTrades(String strategy) {
        List<Trade> trades = new ArrayList<>();
        try {
            connection = JDBCDAOFactory.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM trades WHERE strategy = \"" + strategy + "\" AND open = true;");

            while (resultSet.next() == true) {
                int id = resultSet.getInt("id");
                String symbol = resultSet.getString("symbol");
                int symbolID = resultSet.getInt("symbol_id");
                String assetType = resultSet.getString("asset_type");

                
                trade.setSymbol(resultSet.getString("date"));
                
                Trade trade = new Trade(id, symbol, symbolID, assetType, assetType, null, id, id, assetType, id, symbolID, true)
                trades.add(trade);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StockJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {

                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {

                }
            }

        }
        return trades;
    }
}
