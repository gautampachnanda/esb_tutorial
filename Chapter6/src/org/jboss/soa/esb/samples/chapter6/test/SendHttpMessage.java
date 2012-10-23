package org.jboss.soa.esb.samples.chapter6.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendHttpMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        String serverURL = "http://localhost:8080/Chapter6/http/Chapter6Sample/Chapter6Service";
        HttpURLConnection connection = (HttpURLConnection)new URL(serverURL).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
        out.println("Hello Http Gateway!");
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
	}

}
