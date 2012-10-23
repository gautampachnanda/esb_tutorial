package org.jboss.soa.esb.samples.chapter5.test;

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

        esbMessage.getBody().add("Chapter 5 says Hello!");

        new ServiceInvoker("Chapter5Sample", "Chapter5Service").deliverAsync(esbMessage);
	}

}
