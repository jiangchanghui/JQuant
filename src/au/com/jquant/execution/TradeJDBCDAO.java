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

import au.com.jquant.algotrader.timing.MarketTime;
import au.com.jquant.asset.Interval;
import au.com.jquant.asset.stock.StockJDBCDAO;
import au.com.jquant.factory.dao.JDBCDAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
            preparedStatement = connection.prepareStatement("SELECT t.id, t.strategy, t.symbol_id, s.symbol, t.asset_type, t.position_type, t.open_date, t.open_price, t.quantity, t.order_type, t.value, signal_open_price, t.open\n"
                    + "FROM trade t, stock s\n"
                    + "WHERE t.symbol_id = s.id\n"
                    + "AND t.strategy = ?;");
            preparedStatement.setString(1, strategy);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next() == true) {
                int id = resultSet.getInt("id");
                //String strategy = resultSet.getString('strategy');
                int symbolId = resultSet.getInt("symbol_id");
                String symbol = resultSet.getString("symbol");
                //String symbol = resultSet.getString("strategy");        
                String assetType = resultSet.getString("asset_type");
                String positionType = resultSet.getString("position_type");
                Date openDate = resultSet.getDate("open_date");
                double openPrice = resultSet.getDouble("open_price");
                int quantity = resultSet.getInt("quantity");
                String orderType = resultSet.getString("order_type");
                double value = resultSet.getDouble("value");
                double signalOpenPrice = resultSet.getDouble("signal_open_price");
                boolean isOpen = resultSet.getBoolean("open");

                Trade trade = new Trade(id, strategy, symbolId, symbol, assetType, positionType, openDate, openPrice, quantity, orderType, value, signalOpenPrice, isOpen);
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

    @Override
    public int createTrade(Trade trade) {

        int result = 0;

        try {
            connection = JDBCDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO trade VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, trade.getId());
            preparedStatement.setString(2, trade.getStrategy());
            preparedStatement.setInt(3, trade.getSymbolId());
            preparedStatement.setString(4, trade.getAssetType());
            preparedStatement.setString(5, trade.getPositionType());
            preparedStatement.setTimestamp(6, Timestamp.from(trade.getOpenDate().toInstant()));
            preparedStatement.setDouble(7, trade.getOpenPrice());
            preparedStatement.setInt(8, trade.getQuantity());
            preparedStatement.setString(9, trade.getOrderType());
            preparedStatement.setDouble(10, trade.getValue());
            preparedStatement.setDouble(11, trade.getValue());
            preparedStatement.setBoolean(12, trade.isOpen());

            result = preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StockJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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
        return result;
    }
}
