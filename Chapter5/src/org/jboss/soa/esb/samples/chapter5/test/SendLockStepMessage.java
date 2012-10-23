package org.jboss.soa.esb.samples.chapter5.test;

import org.jboss.soa.esb.message.Message;
import org.jboss.soa.esb.message.format.MessageFactory;
import org.jboss.soa.esb.client.ServiceInvoker;

public class SendLockStepMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");
        Message esbMessage = MessageFactory.getInstance().getMessage();

        esbMessage.getBody().add("Chapter 5 says Hello!");

        ServiceInvoker invoker = new ServiceInvoker("Chapter5Sample", "Chapter5Service");
        for (int i = 0; i < 10; i++) {
        	invoker.deliverAsync(esbMessage);
        }
	}

}
