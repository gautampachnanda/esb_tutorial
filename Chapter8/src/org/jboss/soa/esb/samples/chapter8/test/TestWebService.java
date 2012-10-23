package org.jboss.soa.esb.samples.chapter8.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TestWebService {

	public static void main(String[] args) throws Exception {
		String serviceURL = "http://localhost:8080/Chapter8/ebws/Chapter8Sample/Chapter8Service";
		String input = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:chap=\"http://chapter8.samples.esb.soa.jboss.org/\">"
						+ "<soapenv:Header/>"
						+ "   <soapenv:Body>"
						+ "      <chap:getAuthors/>"
						+ "   </soapenv:Body>"
						+ "</soapenv:Envelope>";
		
		String response = invokeWebService(serviceURL, input);
		
        Source source = new StreamSource(new StringReader(response));
        Result result = new StreamResult(new OutputStreamWriter(System.out));
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute("indent-number", 4);
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
	}
	

    public static String invokeWebService(String serviceURL, String input) {
        String output = null;

        try {
            URL _serviceURL = new URL(serviceURL);
            HttpURLConnection con = (HttpURLConnection) _serviceURL.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestProperty("Content-type", "text/xml; charset=utf-8");
            OutputStream outStream = con.getOutputStream();
            outStream.write(input.getBytes());
            InputStream inStream = con.getInputStream();
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            byte[] byteBuf = new byte[256];
            int len = inStream.read(byteBuf);
            while (len > -1) {
                byteStream.write(byteBuf, 0, len);
                len = inStream.read(byteBuf);
            }
            outStream.close();
            inStream.close();
            byteStream.close();
            output =  byteStream.toString();

        } catch (IOException ioe) {
        	ioe.printStackTrace();
            output = "<error>" + ioe + "</error>";
        }
        return output;
    }
}
