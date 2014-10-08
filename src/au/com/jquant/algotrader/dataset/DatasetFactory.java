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
package au.com.jquant.algotrader.dataset;

import au.com.jquant.asset.Asset;
import au.com.jquant.asset.AssetDAO;
import au.com.jquant.factory.dao.DAOFactory;
import static au.com.jquant.factory.dao.DAOFactory.JDBC;
import java.util.List;

/**
 *
 * @author davidherod
 */
public class DatasetFactory {

    public static List<Asset> getDataset(int dataset) throws Exception {
        AssetDAO assetDAO = DAOFactory.getDAOFactory(JDBC).getAssetDAO();
        switch (dataset) {
            case 0:
                return assetDAO.getSP500(); 
            default:
                throw new Exception("Error: Dataset not found"); // TODO: add support for all datasets.
        }
    }
}
