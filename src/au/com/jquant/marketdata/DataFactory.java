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

import au.com.jquant.asset.Asset;
import au.com.jquant.factory.dao.DAOFactory;
import java.util.List;

/**
 *
 * @author davidherod
 */
public class DataFactory {
    
    public static List<Asset> getData(int dataset, String startDate, String endDate) throws Exception {
        switch (dataset) {
            case 0: // return S&P 500 
                return DAOFactory.getDAOFactory(DAOFactory.JDBC).getAssetDAO().getSP500Historical(startDate, endDate);
            default:
                throw new Exception("Error: Not a valid dataset.");
        }
    }
    
    
}
