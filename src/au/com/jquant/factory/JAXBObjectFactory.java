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

package au.com.jquant.factory;

import au.com.jquant.asset.stock.Stock;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRegistry;

/**
 *
 * @author davidherod
 */
@XmlRegistry
public class JAXBObjectFactory {

    public JAXBObjectFactory() {
    }

    public Stock createStock() throws IOException, MalformedURLException, JAXBException {
        return new Stock();
    }
}
