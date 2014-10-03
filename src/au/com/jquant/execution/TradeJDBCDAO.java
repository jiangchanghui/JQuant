/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.jquant.execution;

import au.com.jquant.asset.Interval;
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
public class TradeJDBCDAO implements TradeDAO{

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    
    @Override
    public List<Trade> getBollingerBreakoutOpenTrades() {
     List<Trade> trades = new ArrayList<>();
        try {
            connection = JDBCDAOFactory.createConnection();
            statement = connection.createStatement();         
            resultSet = statement.executeQuery("SELECT * FROM trades WHERE strategy = \"BollingerBreakout\" AND open = true;");

            while (resultSet.next() == true) {
                Trade trade = new Trade();
                trade.setSymbol(resultSet.getString("date"));
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
