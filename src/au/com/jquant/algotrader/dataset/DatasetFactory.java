/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
