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


import com.ib.client.Contract;
import static au.com.jquant.algotrader.IBAlgoTrader.*;
import com.ib.client.Order;
import java.util.Date;



/**
 *
 * @author davidherod
 */
public class InteractiveBrokersTrade extends Trade {

    private Contract contract;
    private Order order;
    
    /**
     * Constructor with all required parameters to open a new interactivebrokers trade.
     * @param symbol
     * @param symbolId
     * @param assetType
     * @param positionType
     * @param quantity
     * @param orderType
     * @param signalOpenPrice 
     */
    public InteractiveBrokersTrade(String symbol, int symbolId, String assetType, String positionType, int quantity, String orderType, double signalOpenPrice) {
        super(symbol, symbolId, assetType, positionType, quantity, orderType, signalOpenPrice);
    }

    /**
     * Constructor with all required parameters to create an open instance of an interactivebrokers trade.
     * @param id
     * @param symbol
     * @param symbolId
     * @param assetType
     * @param positionType
     * @param openDate
     * @param openPrice
     * @param quantity
     * @param orderType
     * @param value
     * @param signalOpenPrice
     * @param open 
     */
    public InteractiveBrokersTrade(int id, String symbol, int symbolId, String assetType, String positionType, Date openDate, double openPrice, int quantity, String orderType, double value, double signalOpenPrice, boolean open) {
        super(id, symbol, symbolId, assetType, positionType, openDate, openPrice, quantity, orderType, value, signalOpenPrice, open);
    }

    /**
     * Opens a new trade.
     */
    @Override
    public void open() {

        setOpenDate(new Date());
        contract = new Contract();
        order = new Order();

        contract.m_symbol = getSymbol();
        contract.m_secType = getAssetType(); //STK
        contract.m_exchange = "SMART";
        contract.m_currency = "USD";
        order.m_action = (getPositionType().equals("long") ? "BUY":"SELL"); // TODO validate input
        order.m_totalQuantity = getQuantity();
        order.m_orderType = getOrderType(); //MKT

        ibClient.placeOrder(getId(), contract, order);  
    }

    /**
     * Closes an open trade.
     */
    @Override
    public void close() {
        
        contract = new Contract();
        order = new Order();

        contract.m_symbol = getSymbol();
        contract.m_secType = getAssetType(); //STK
        contract.m_exchange = "SMART";
        contract.m_currency = "USD";
        order.m_action = (getPositionType().equals("long") ? "SELL":"BUY"); // TODO validate input
        order.m_totalQuantity = getQuantity();
        order.m_orderType = getOrderType(); //MKT

        ibClient.placeOrder(getId(), contract, order);
    }
}
