package org.jboss.soa.esb.samples.chapter8;

import java.util.HashMap;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

public class MyRequestAction extends AbstractActionLifecycle {

	protected ConfigTree _config;
	
	public MyRequestAction(ConfigTree config) {
		_config = config;
	}

	public Message process(Message message) throws Exception {
		String msgBody = (String) message.getBody().get();
		HashMap<String, String> requestMap = new HashMap<String, String>();
		// add parameters to the web service request map
		requestMap.put(msgBody, null);
		message.getBody().add(requestMap);
		System.out.println("Request map is: " + requestMap.toString());
		return message;
	}
}
