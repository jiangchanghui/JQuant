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

package au.com.jquant.asset;

import java.util.List;

/**
 *
 * @author davidherod
 */
public interface AssetDAO {
    public int createAsset(Asset asset);
    public List<Asset> getSP500Historical();
    public List<Asset> getSP500Historical(String startDate, String endDate);
    public List<Asset> getSP500();
}
