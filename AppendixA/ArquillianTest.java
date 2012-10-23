package org.jboss.soa.esb;

import java.io.File;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.soa.esb.samples.quickstart.helloworld.MyJMSListenerAction;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianTest {


   @Deployment(testable=false)
   public static JavaArchive createTestArchive() throws Exception {
      return ShrinkWrap.create(JavaArchive.class, "test.esb")
		.add(new FileAsset(new File("jbm-queue-service.xml")), "jbm-queue-service.xml")
		.addClass(MyJMSListenerAction.class)
		.addAsManifestResource("jboss-esb.xml")
		.addAsManifestResource("deployment.xml");
   }

   @Test
   public void testMethod() throws Exception {
	   // Insert your test logic and execution here
   }
}
