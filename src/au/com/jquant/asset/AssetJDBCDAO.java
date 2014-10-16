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
package au.com.jquant.asset;

import au.com.jquant.asset.stock.Stock;
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
public class AssetJDBCDAO implements AssetDAO {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    @Override
    public int createAsset(Asset asset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Asset> getSP500Historical(String startDate, String endDate) {

        List<Asset> assets = new ArrayList<>();
        try {
            connection = JDBCDAOFactory.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sp_500");

            while (resultSet.next() == true) {
                Stock stock = new Stock();
                stock.setSymbol(resultSet.getString("symbol"));
                stock.setDescription(resultSet.getString("description"));
                stock.setIndustryDevision(resultSet.getString("industry_devision"));
                stock.setIndustrySector(resultSet.getString("industry_sector"));
                stock.setIndustryGroup(resultSet.getString("industry_group"));
                stock.setMarketCap(resultSet.getLong("market_cap"));

                // Change this!
                stock.setIntervals(new StockJDBCDAO().getHistoricalDaily(stock.getSymbol(), startDate, endDate));
                assets.add(stock);
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
        return assets;
    }

    @Override
    public List<Asset> getSP500Historical() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Asset> getSP500() {
        List<Asset> assets = new ArrayList<>();
        try {
            connection = JDBCDAOFactory.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sp_500");

            while (resultSet.next() == true) {
                Stock stock = new Stock();
                stock.setSymbol(resultSet.getString("symbol"));
                stock.setDescription(resultSet.getString("description"));
                stock.setIndustryDevision(resultSet.getString("industry_devision"));
                stock.setIndustrySector(resultSet.getString("industry_sector"));
                stock.setIndustryGroup(resultSet.getString("industry_group"));
                stock.setMarketCap(resultSet.getLong("market_cap"));
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
        return assets;
    }
}
