package org.jboss.soa.esb.samples.chapter8;

import java.util.Map;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

public class MyResponseAction extends AbstractActionLifecycle {

	protected ConfigTree _config;

	public MyResponseAction(ConfigTree config) {
		_config = config;
	}

	@SuppressWarnings("unchecked")
	public Message process(Message message) throws Exception {
		Map<String, String> responseMsg = (Map<String, String>) message
				.getBody().get();
		System.out.println("Response Map is: " + responseMsg);
		return message;
	}

}
