package au.com.jquant.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBException;

/**
 *
 * @author David
 */
public class Request {

    XMLBinder xmlBinder = null;
    private static final String OUTPUT_FILE = "c:/text.xml";

    public static Object doPost(String url, String xmlRequest, Object clazz) throws MalformedURLException, IOException, JAXBException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("content-type", "application/xml; charset=UTF-8");

        String urlParameters = xmlRequest;

        // Send post request
        con.setDoInput(true);
        con.setDoOutput(true);


        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        Writer out = new OutputStreamWriter(wr, "UTF-8");
        out.write(xmlRequest);
        out.flush();
        out.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters....\n " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        
       InputStream is = GZipUtil.decompressStream(con.getInputStream());
       InputStreamReader isr = new InputStreamReader(is, "UTF8");
        
       //Gzip compression 
//     GZIPInputStream gzip = new GZIPInputStream(con.getInputStream());
//     InputStreamReader isr = new InputStreamReader(gzip, "UTF8");

       //No compression  
//     InputStreamReader isr = new InputStreamReader(con.getInputStream(), "UTF8");


        //Print data
//        StringBuilder buffer = new StringBuilder();
//        Reader in = new BufferedReader(isr);
//             
//         int ch;
//        while ((ch = in.read()) > -1) {
//            buffer.append((char) ch);
//            //System.out.println(buffer.toString());
//        }
//        //in.close();
//        System.out.println(buffer.toString());

//        Write to file
//        byte[] buffer = new byte[1024];
//        FileOutputStream fo = 
//            new FileOutputStream(OUTPUT_FILE);
// 
//        int len;
//        while ((len = gzip.read(buffer)) > -1) {
//        	fo.write(buffer, 0,len);
//        }
//        ResponseMensagemCB responseMensagemCB = new ResponseMensagemCB();

        Object object = (Object) new XMLBinder().unMarshaller(isr, clazz);

        return object;
    }
}
