package org.jboss.soa.esb.samples.chapter6.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jboss.soa.esb.samples.chapter6.GatewayRecord;

public class SendHibernateMessage {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

        String serverURL = "http://localhost:8080/Chapter6/http/Chapter6Sample/HibernateService";
        HttpURLConnection connection = (HttpURLConnection)new URL(serverURL).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
        out.println("Hello Hibernate Gateway!");
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while (in.readLine() != null) {
        }
        in.close();

		Thread.sleep(3000);
	    Configuration config = new Configuration();
	    config.configure(new File(System.getProperty("user.dir") + "/hibernate.cfg.xml"));
		config.addFile(new File(System.getProperty("user.dir") + "/esbcontent/esb-mappings.hbm.xml"));
		SessionFactory sf = config.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		List<GatewayRecord> records = sess.createQuery("select o from GatewayRecord as o").list();
		for (GatewayRecord record : records) {
			System.out.println(record.getData());
			sess.delete(record);
		}
		tx.commit();
		sess.flush();
		sess.close();
	}

}
