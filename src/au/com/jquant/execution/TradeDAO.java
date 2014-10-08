/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.jquant.execution;

import java.util.List;

/**
 *
 * @author davidherod
 */
public interface TradeDAO {
    public List<Trade> getOpenTrades(String strategyID);
}
