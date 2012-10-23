package org.jboss.soa.esb.samples.chapter8.test;

import org.jboss.soa.esb.message.Message;
import org.jboss.soa.esb.message.format.MessageFactory;
import org.jboss.soa.esb.client.ServiceInvoker;

public class SendEsbMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");
        Message esbMessage = MessageFactory.getInstance().getMessage();

        esbMessage.getBody().add("getAuthors");
        Message response = new ServiceInvoker("Chapter8Sample", "Chapter8WSClient").deliverSync(esbMessage, 1200);

        System.out.println(response.getBody().get());
	}

}
