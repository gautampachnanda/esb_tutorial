package org.jboss.soa.esb;

import java.io.File;
import java.util.zip.ZipFile;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianBinaryTest {
   private static final String ESB_LOCATION = "/src/jbossesb/product/samples/quickstarts/helloworld_book/build/QuickStartHelloWorld.esb";
	
   @Deployment(testable=false)
   public static JavaArchive createTestArchive() throws Exception {
      File file;
      ZipFile existingZipArchive = null;
      file = new File(ESB_LOCATION);
      existingZipArchive = new ZipFile(file);
      return ShrinkWrap.create(JavaArchive.class, "test.esb")
		.as(ZipImporter.class)
		.importZip(existingZipArchive)
		.as(JavaArchive.class); 
   }
  
   @Test
   public void testMethod() throws Exception {
	   // Insert your test logic and execution here
   }
}
