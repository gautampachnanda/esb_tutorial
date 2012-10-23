package org.jboss.soa.esb.samples.quickstart.helloworld;

import junit.framework.TestCase;
import org.jboss.soa.esb.testutils.AbstractTestRunner;
import org.jboss.soa.esb.client.ServiceInvoker;
import org.jboss.soa.esb.message.Message;
import org.jboss.soa.esb.message.format.MessageFactory;
import org.jboss.soa.esb.actions.ActionProcessingException;

public class HelloWorldTest extends TestCase {
	public void test_async() throws Exception {
        AbstractTestRunner testRunner = new AbstractTestRunner() {
            public void test() throws Exception {
            	ServiceInvoker invoker = new ServiceInvoker("FirstServiceESB", "SimpleListener");
            	Message message = MessageFactory.getInstance().getMessage();
            	message.getBody().add("helloworld");

            	message = invoker.deliverSync(message, 10000);
	
		// Insert code here to verify your results           	
 		assertEquals("helloworld", message.getBody().get());
           }
        }.setServiceConfig("helloworld-jboss-esb.xml");

        testRunner.run();
	}
}
