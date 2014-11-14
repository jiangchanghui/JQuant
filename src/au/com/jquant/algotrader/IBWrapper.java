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
package au.com.jquant.algotrader;

import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.UnderComp;
import java.util.Date;

/**
 *
 * @author davidherod
 */
public class IBWrapper implements EWrapper {

    @Override
    public synchronized void tickPrice(int tickerId, int field, double price, int canAutoExecute) {
        System.out.println("tickprice");
        IBAlgoTrader.manageLiveTickData(tickerId, price);
        System.out.println(field + " " + price);
    }

    @Override
    public void tickSize(int tickerId, int field, int size) {
        //System.out.println("ticksize");
        //System.out.println(tickerId + " " + field + " " + size);
    }

    @Override
    public void tickOptionComputation(int tickerId, int field, double impliedVol, double delta, double optPrice, double pvDividend, double gamma, double vega, double theta, double undPrice) {
        System.out.println("tickoptioncomp");
    }

    @Override
    public void tickGeneric(int tickerId, int tickType, double value) {
        System.out.println("4");
    }

    @Override
    public void tickString(int tickerId, int tickType, String value) {
        System.out.println("tickstring");
    }

    @Override
    public void tickEFP(int tickerId, int tickType, double basisPoints, String formattedBasisPoints, double impliedFuture, int holdDays, String futureExpiry, double dividendImpact, double dividendsToExpiry) {
        System.out.println("tickefp");
    }

    @Override
    public void orderStatus(int orderId, String status, int filled, int remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {
        System.out.println("orderstatus");
        System.out.println(status);
        System.out.println(filled);
        System.out.println(remaining);
    }

    @Override
    public void openOrder(int orderId, Contract contract, Order order, OrderState orderState) {
        System.out.println("openorder");
        System.out.println(orderId);
        System.out.println(contract.m_symbol);
        System.out.println(contract.m_secType);
        System.out.println(contract.m_conId);
        System.out.println(order.m_account);
        System.out.println(order.m_action);
        System.out.println(order.m_activeStartTime);
        System.out.println(order.m_clientId);
        System.out.println(order.m_permId);
    }

    @Override
    public void openOrderEnd() {
        System.out.println("openorderend");
    }

    @Override
    public void updateAccountValue(String key, String value, String currency, String accountName) {
        System.out.println("updateaccountvalue");
    }

    @Override
    public void updatePortfolio(Contract contract, int position, double marketPrice, double marketValue, double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {
        System.out.println("updateportfolio");
    }

    @Override
    public void updateAccountTime(String timeStamp) {
        System.out.println("updateaccount");
    }

    @Override
    public void accountDownloadEnd(String accountName) {
        System.out.println("accountdownloadend");
    }

    @Override
    public void nextValidId(int orderId) {
        System.out.println("nextvalidid");
        System.out.println(orderId);
        IBAlgoTrader.nextvalidTradeID = orderId;
    }

    @Override
    public void contractDetails(int reqId, ContractDetails contractDetails) {
        System.out.println("contractdetails");
    }

    @Override
    public void bondContractDetails(int reqId, ContractDetails contractDetails) {
        System.out.println("bondcontract details");
    }

    @Override
    public void contractDetailsEnd(int reqId) {
        System.out.println("contractdetails end");
    }

    @Override
    public void execDetails(int reqId, Contract contract, Execution execution) {
        System.out.println("execdetails");
        System.out.println(contract.m_symbol);
        System.out.println(contract.m_currency);
        System.out.println(execution.m_shares);
    }

    @Override
    public void execDetailsEnd(int reqId) {
        System.out.println("executiondetailsend");
        System.out.println(reqId);
    }

    @Override
    public void updateMktDepth(int tickerId, int position, int operation, int side, double price, int size) {
        System.out.println("updateMktDepth");
    }

    @Override
    public void updateMktDepthL2(int tickerId, int position, String marketMaker, int operation, int side, double price, int size) {
        System.out.println("updateMktDepthl2");
    }

    @Override
    public void updateNewsBulletin(int msgId, int msgType, String message, String origExchange) {
        System.out.println("updateNewsBulletin");
    }

    @Override
    public void managedAccounts(String accountsList) {
        System.out.println("managedAccounts");
        System.out.println(accountsList);
    }

    @Override
    public void receiveFA(int faDataType, String xml) {
        System.out.println("receiveFA");
    }

    @Override
    public void historicalData(int reqId, String date, double open, double high, double low, double close, int volume, int count, double WAP, boolean hasGaps) {
        System.out.println(reqId + " " + date + " " + open + " " + high + " " + low + " " + close + " " + volume);
        //System.out.println("historicalData");
    }

    @Override
    public void scannerParameters(String xml) {
        System.out.println("scannerParameters");
    }

    @Override
    public void scannerData(int reqId, int rank, ContractDetails contractDetails, String distance, String benchmark, String projection, String legsStr) {
        System.out.println("scannerData");
    }

    @Override
    public void scannerDataEnd(int reqId) {
        System.out.println("scannerDataEnd");
    }

    @Override
    public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count) {
        System.out.println(reqId + " " + close);
        System.out.println("realtimeBar");
    }

    @Override
    public void currentTime(long time) {
        System.out.println("currenttime");
        System.out.println(new Date(time * 1000));
    }

    @Override
    public void fundamentalData(int reqId, String data) {
        System.out.println("fundamentalData");
    }

    @Override
    public void deltaNeutralValidation(int reqId, UnderComp underComp) {
        System.out.println("deltaNeutralValidation");
    }

    @Override
    public void tickSnapshotEnd(int reqId) {
        System.out.println("tickSnapshotEnd");
    }

    @Override
    public void marketDataType(int reqId, int marketDataType) {
        System.out.println("marketDataType");
    }

    @Override
    public void commissionReport(CommissionReport commissionReport) {
        System.out.println("commissionReport");
        System.out.println(commissionReport.m_commission);
        System.out.println(commissionReport.m_execId);
        System.out.println(commissionReport.m_realizedPNL);

    }

    @Override
    public void position(String account, Contract contract, int pos, double avgCost) {
        System.out.println("position");
    }

    @Override
    public void positionEnd() {
        System.out.println("positionEnd");
    }

    @Override
    public void accountSummary(int reqId, String account, String tag, String value, String currency) {
        System.out.println("accountSummary");
    }

    @Override
    public void accountSummaryEnd(int reqId) {
        System.out.println("accountSummaryEnd");
    }

    @Override
    public void verifyMessageAPI(String apiData) {
        System.out.println("verifyMessageAPI");
    }

    @Override
    public void verifyCompleted(boolean isSuccessful, String errorText) {
        System.out.println("verifyCompleted");
    }

    @Override
    public void displayGroupList(int reqId, String groups) {
        System.out.println("displayGroupList");
    }

    @Override
    public void displayGroupUpdated(int reqId, String contractInfo) {
        System.out.println("displayGroupUpdated");
    }

    @Override
    public void error(Exception e) {
        System.out.println("error exception");
        System.out.println(e.toString());
    }

    @Override
    public void error(String str) {
        System.out.println("error string");
        System.out.println(str);
    }

    @Override
    public void error(int id, int errorCode, String errorMsg) {
        System.out.println("error message");
        System.out.println(errorMsg);
    }

    @Override
    public void connectionClosed() {
        System.out.println("connection closed");
    }

}
