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
public class StockJDBCDAO implements StockDAO {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    @Override
    public int createStock(Stock stock) {
        int result = 0;
        try {
            connection = JDBCDAOFactory.createConnection();

            preparedStatement = connection.prepareStatement("INSERT into STOCK values (?, ?, ?, ? , ?)");
            preparedStatement.setString(1, stock.getSymbol());
            preparedStatement.setString(2, stock.getDescription());
            preparedStatement.setString(3, stock.getIndustryDevision());
            preparedStatement.setString(4, stock.getIndustrySector());
            preparedStatement.setString(5, stock.getIndustryGroup());
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

    @Override
    public boolean deleteStock(String ticker) {
        boolean deleted = false;
        try {
            connection = JDBCDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement("DELETE from stock WHERE ticker = ?;");
            preparedStatement.setString(1, ticker);
            deleted = preparedStatement.executeUpdate() == 0;

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
        return deleted;
    }

    @Override
    public int updateStock(Stock stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Stock> getSP500() {
        List<Stock> stocks = new ArrayList<>();
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

                stocks.add(stock);
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
        return stocks;
    }

    @Override
    public List<Interval> getHistoricalDaily(String ticker, String startDate, String endDate) {
        List<Interval> intervals = new ArrayList<>();
        try {
            connection = JDBCDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM historical_daily WHERE ticker =? AND date > ? AND date < ? ORDER BY date");
            preparedStatement.setString(1, ticker);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next() == true) {
                Interval interval = new Interval();
                interval.setDate(resultSet.getDate("date"));
                interval.setOpen(resultSet.getDouble("open"));
                interval.setHigh(resultSet.getDouble("high"));
                interval.setLow(resultSet.getDouble("low"));
                interval.setClose(resultSet.getDouble("close"));
                interval.setVolume(resultSet.getLong("volume"));
                interval.setAdjClose(resultSet.getDouble("close_adj"));
                intervals.add(interval);
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
        return intervals;
    }

    @Override
    public List<Interval> getHistoricalDaily(String ticker) {
        List<Interval> intervals = new ArrayList<>();
        try {
            connection = JDBCDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM historical_daily WHERE ticker =?");
            preparedStatement.setString(1, ticker);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next() == true) {
                Interval interval = new Interval();
                interval.setDate(resultSet.getDate("date"));
                interval.setOpen(resultSet.getDouble("open"));
                interval.setHigh(resultSet.getDouble("high"));
                interval.setLow(resultSet.getDouble("low"));
                interval.setClose(resultSet.getDouble("close"));
                interval.setVolume(resultSet.getLong("volume"));
                interval.setAdjClose(resultSet.getDouble("close_adj"));

                intervals.add(interval);

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
        return intervals;
    }

    @Override
    public int setHistoricalDaily(String ticker, List<Interval> intervals) {
        int result = 0;
        int count = 0;
        try {
            connection = JDBCDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO historical_daily VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            for (Interval i : intervals) {
                preparedStatement.setString(1, ticker);
                preparedStatement.setDate(2, new java.sql.Date(i.getDate().getTime()));
                preparedStatement.setDouble(3, i.getOpen());
                preparedStatement.setDouble(4, i.getHigh());
                preparedStatement.setDouble(5, i.getLow());
                preparedStatement.setDouble(6, i.getClose());
                preparedStatement.setDouble(7, i.getAdjClose());
                preparedStatement.setLong(8, i.getVolume());
                preparedStatement.addBatch();

                if (++count % 1000 == 0) {
                    preparedStatement.executeBatch(); // Execute every 1000 items.
                    count = 0;
                }
                result = preparedStatement.executeUpdate();

            }
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

    @Override
    public Stock findStock(String symbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Stock> findStocks(String[] symbols) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Interval> getSP500HistoricalDaily() {
        List<Interval> intervals = new ArrayList<>();
        try {
            connection = JDBCDAOFactory.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM historical_daily ORDER BY date;");

            while (resultSet.next() == true) {
                Interval interval = new Interval();
                interval.setDate(resultSet.getDate("date"));
                interval.setOpen(resultSet.getDouble("open"));
                interval.setHigh(resultSet.getDouble("high"));
                interval.setLow(resultSet.getDouble("low"));
                interval.setClose(resultSet.getDouble("close"));
                interval.setVolume(resultSet.getLong("volume"));
                interval.setAdjClose(resultSet.getDouble("close_adj"));

                intervals.add(interval);

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
        return intervals;
    }

    @Override
    public List<Interval> getSP500HistoricalDaily(String startDate, String endDate) {
        List<Interval> intervals = new ArrayList<>();
        try {
            connection = JDBCDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM historical_daily WHERE date > ? AND date < ? ORDER BY date");
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next() == true) {
                Interval interval = new Interval();
                interval.setDate(resultSet.getDate("date"));
                interval.setOpen(resultSet.getDouble("open"));
                interval.setHigh(resultSet.getDouble("high"));
                interval.setLow(resultSet.getDouble("low"));
                interval.setClose(resultSet.getDouble("close"));
                interval.setVolume(resultSet.getLong("volume"));
                interval.setAdjClose(resultSet.getDouble("close_adj"));

                intervals.add(interval);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                
                }
            }
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
        return intervals;
    }
    /**
     * 
     * @param ticker
     * @param periods
     * @return 
     */
    @Override
    public List<Interval> getHistoricalDaily(String ticker, int periods) {
        List<Interval> intervals = new ArrayList<>();
        try {
            connection = JDBCDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM historical_daily where ticker = ? order by date desc limit ?");
            preparedStatement.setString(1, ticker);
            preparedStatement.setInt(2, periods);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next() == true) {
                Interval interval = new Interval();
                interval.setDate(resultSet.getDate("date"));
                interval.setOpen(resultSet.getDouble("open"));
                interval.setHigh(resultSet.getDouble("high"));
                interval.setLow(resultSet.getDouble("low"));
                interval.setClose(resultSet.getDouble("close"));
                interval.setVolume(resultSet.getLong("volume"));
                interval.setAdjClose(resultSet.getDouble("close_adj"));

                intervals.add(interval);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                
                }
            }
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
        return intervals;
    }
}
