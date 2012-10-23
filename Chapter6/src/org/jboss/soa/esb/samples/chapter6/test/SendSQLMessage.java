package org.jboss.soa.esb.samples.chapter6.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SendSQLMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:1704", "sa", "");
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("insert into gateway_table(gwdata, gwstatus) values('Hello SQL Gateway!','P')");
		stmt.close();
		connection.commit();
		connection.close();
		Thread.sleep(3000);
		connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:1704", "sa", "");
		stmt = connection.createStatement();
		ResultSet results = stmt.executeQuery("select gwdata from gateway_table where gwstatus like 'R%'");
		while (results.next()) {
			System.out.println(results.getString("gwdata"));
		}
		stmt.executeUpdate("delete from gateway_table where gwstatus like 'R%'");
		connection.commit();
		connection.close();
	}

}
