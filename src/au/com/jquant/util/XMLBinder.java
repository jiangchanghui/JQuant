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

package au.com.jquant.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author David
 */
public class XMLBinder {

    public String marshaller(Object object) throws JAXBException, MalformedURLException, IOException {
        StringWriter out = new StringWriter();

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(object, new StreamResult(out));
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(out.toString());
        return out.toString();
    }

    public Object unMarshaller(InputStreamReader stream, Object clazz) throws JAXBException, MalformedURLException, IOException {
        Object object = null;
        
        JAXBContext jc = JAXBContext.newInstance(clazz.getClass());
        
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        InputStreamReader xmlStream = stream;
        object = (Object) unmarshaller.unmarshal(xmlStream);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(object, System.out);

        return object;
    }
}
