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

package au.com.jquant.marketdata;

import au.com.jquant.asset.Interval;
import au.com.jquant.asset.stock.Stock;
import au.com.jquant.factory.JAXBObjectFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author davidherod
 */
public class YahooClient{
    
    public static List<Interval> getDailyHistoricalData(String symbol, String startDate, String endDate) throws JAXBException, MalformedURLException, IOException{
        Stock stock;
        JAXBContext jc = JAXBContext.newInstance(new JAXBObjectFactory().getClass());

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22" + symbol + "%22%20and%20startDate%20%3D%20%22" + startDate + "%22%20and%20endDate%20%3D%20%22" + endDate + "%22&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        if(con.getResponseCode() == 200){
            System.out.println("Connection sucessful...");
        }
        InputStream xmlStream = con.getInputStream();
        stock = (Stock) unmarshaller.unmarshal(xmlStream);

        return stock.getIntervals();
    }   

}
