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

import au.com.jquant.algotrader.IBClient;
import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import static au.com.jquant.algotrader.IBAlgoTrader.*;
import com.ib.client.Order;
import java.util.Date;



/**
 *
 * @author davidherod
 */
public class InteractiveBrokersTrade extends Trade {

    public InteractiveBrokersTrade() {
    }
    
    public InteractiveBrokersTrade(String symbol, Date openDate, double openPrice, double value, double slippage, double brokerage) {
        super(symbol, openDate, openPrice, value, slippage, brokerage);
    }

    @Override
    public void open() {

        Contract contract = new Contract();
        Order order = new Order();

        contract.m_symbol = "IBKR";
        contract.m_secType = "STK";
        contract.m_exchange = "SMART";
        contract.m_currency = "USD";

        order.m_action = "BUY";
        order.m_totalQuantity = 100;
        order.m_orderType = "MKT";

        clientSocket.eConnect(null, 7496, 1);
        clientSocket.placeOrder(1, contract, order);
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
