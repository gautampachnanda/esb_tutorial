package org.jboss.soa.esb.samples.chapter6;

import java.util.Map;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

public class MyAction extends AbstractActionLifecycle {
    protected ConfigTree  _config;

    public MyAction(ConfigTree config) {
        _config = config;
    }

    @SuppressWarnings("unchecked")
	public Message process(Message message) throws Exception {
    	Object obj = message.getBody().get();
        String request = "";
        if (obj instanceof String) {
        	request = (String) obj;
        } else if (obj instanceof byte[]) {
        	request = new String((byte[]) obj);
        } else if (obj instanceof Map) {
        	Map<String, Object> rowData =(Map<String, Object>)obj;
        	for (Map.Entry<String, Object> curr : rowData.entrySet()) {
        		Object value = curr.getValue();
            	if (value instanceof String) {
            		request = (String) value;
            	}
  		    }
        } else if (obj instanceof GatewayRecord) {
        	request = ((GatewayRecord) obj).getData();
        }
        String response = request.replace("Hello", "").replace("!", " says Hello!");
        message.getBody().add(response);
        return message;
    }
}
