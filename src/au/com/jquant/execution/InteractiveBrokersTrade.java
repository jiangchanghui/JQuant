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

import au.com.jquant.algotrader.IBAlgoTrader;
import com.ib.client.Contract;
import static au.com.jquant.algotrader.IBAlgoTrader.*;
import au.com.jquant.asset.Asset;
import com.ib.client.Order;
import com.ib.client.TagValue;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author davidherod
 */
public class InteractiveBrokersTrade extends Trade {

    /**
     * Constructor with all required parameters to open a new interactive brokers trade.
     *
     * @param asset The asset being bought/sold.
     * @param positionType The type of position long/short.
     * @param quantity The quantity being bought or sold.
     * @param orderType The type of order long/short.
     * @param strategy The name of the strategy executing the trade.
     */
    public InteractiveBrokersTrade(Asset asset, String positionType, int quantity, String orderType, String strategy) {
        super(asset, positionType, quantity, orderType, strategy);
    }

    /**
     * Constructor with all required parameters to create an open instance of an Interactive Brokers trade.
     *
     * @param asset
     * @param assetType
     * @param positionType
     * @param openDate
     * @param openPrice
     * @param quantity
     * @param orderType
     * @param value
     * @param signalOpenPrice
     * @param open
     * @param strategy
     */
    public InteractiveBrokersTrade(Asset asset, String assetType, String positionType, Date openDate, double openPrice, int quantity, String orderType, double value, double signalOpenPrice, boolean open, String strategy) {
        super(asset, positionType, quantity, orderType, strategy);
    }

    /**
     * Opens a new Interactive Brokers trade.
     */
    @Override
    public void open() {
        Contract contract;
        Order order;

        contract = new Contract();
        order = new Order();

        contract.m_symbol = getSymbol();
        contract.m_secType = (getAssetType().equals("Stock") ? "stk" : "err"); // TODO validate input //STK
        contract.m_exchange = "SMART";
        contract.m_currency = "USD";
        order.m_action = (getPositionType().equals("long") ? "BUY" : "SELL"); // TODO validate input
        order.m_totalQuantity = getQuantity();
        order.m_orderType = getOrderType(); //MKT

        ibClient.reqMktData(getSymbolId(), contract, null, false, new ArrayList<TagValue>());

        ibClient.placeOrder(IBAlgoTrader.nextvalidTradeID, contract, order);
        IBAlgoTrader.nextvalidTradeID++;
        setId(nextvalidTradeID); //TODO fix

        // TODO: if realtimemonitoring strategy and asset nit in live tick data list request market data
    }

    /**
     * Closes an open trade.
     */
    @Override
    public void close() {
        Contract contract;
        Order order;

        contract = new Contract();
        order = new Order();

        contract.m_symbol = getSymbol();
        contract.m_secType = getAssetType(); //STK
        contract.m_exchange = "SMART";
        contract.m_currency = "USD";
        order.m_action = (getPositionType().equals("long") ? "SELL" : "BUY"); // TODO validate input
        order.m_totalQuantity = getQuantity();
        order.m_orderType = getOrderType(); //MKT

        ibClient.placeOrder(IBAlgoTrader.nextvalidTradeID, contract, order);
        IBAlgoTrader.nextvalidTradeID++;
    }
}
