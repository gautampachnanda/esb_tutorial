package org.jboss.soa.esb.samples.chapter6.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SendFileMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String tmpDir = System.getProperty("java.io.tmpdir");
		File file = new File(tmpDir, "file.msg");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		writer.write("Hello File Gateway!");
		writer.close();
		Thread.sleep(300);
		File result = new File(tmpDir, "results.log");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(result)));
		String line = null;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
		
	}

}
