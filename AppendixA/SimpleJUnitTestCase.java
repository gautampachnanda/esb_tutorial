package org.jboss.soa.esb.samples.quickstart.helloworld;

import junit.framework.TestCase;
import org.jboss.soa.esb.actions.SystemPrintln;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;
import org.jboss.soa.esb.message.format.MessageFactory;

public class SimpleJUnitTestCase extends TestCase {
	private static final String MESSAGE_STRING = "helloworld";
	
	public void testSystemPrintln() throws Exception {
            	Message message = MessageFactory.getInstance().getMessage();
            	message.getBody().add(MESSAGE_STRING);
	
		ConfigTree config = new ConfigTree("config");	
		SystemPrintln spl = new SystemPrintln(config);
		Message result = spl.process(message);
		String resultString = (String) result.getBody().get();

		assertEquals(MESSAGE_STRING, resultString);
	}
}
