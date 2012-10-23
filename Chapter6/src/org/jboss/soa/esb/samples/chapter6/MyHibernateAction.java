package org.jboss.soa.esb.samples.chapter6;

import javax.naming.InitialContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

public class MyHibernateAction extends AbstractActionLifecycle {
    protected ConfigTree  _config;
    private SessionFactory sf;

    public MyHibernateAction(ConfigTree config) {
        _config = config;
    }

    public Message process(Message message) throws Exception {
    	if (sf == null) {
    		// Initialize it here as the hibernate config is loaded by our config
            try {
            	InitialContext iniCtx = new InitialContext();
            	sf = (SessionFactory) iniCtx.lookup("SessionFactory");
    			System.out.println("Found SessionFactory in JNDI - Interceptors will work!");
            } catch (Exception e) {
    			System.out.println("Could not find SessionFactory in JNDI - Interceptors will not work!");
            	Configuration hconfig = new Configuration();
            	hconfig.configure("hibernate.cfg.xml");
    			sf = hconfig.buildSessionFactory();
            }
    	}
    	
    	if (sf != null) {
	        Transaction tx = null;
			Session sess = sf.openSession();
			try {
				tx = sess.beginTransaction();
				sess.save(new GatewayRecord((String) message.getBody().get(),"P"));
				tx.commit();
				sess.flush();
				message.getBody().add("Success!");
			} catch (Exception e) {
				message.getBody().add(e.getMessage());
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			}
			sess.close();
    	}
        return message;
    }
}
