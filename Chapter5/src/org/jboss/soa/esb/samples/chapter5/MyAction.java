package org.jboss.soa.esb.samples.chapter5;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.actions.AbstractActionPipelineProcessor;
import org.jboss.soa.esb.actions.ActionProcessingException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

public class MyAction extends AbstractActionLifecycle {

    protected ConfigTree  _config;

    public MyAction(ConfigTree config) {
        _config = config;      
    } 

    public Message process(Message message) throws Exception {
        System.out.println("Body: " + message.getBody().get());
        return message;
    }

    public Message causesException(Message message) throws ActionProcessingException {
        System.out.println("About to cause an exception");
        throw new ActionProcessingException("BAD STUFF HAPPENED");
    }

    public Message lockStepAction(Message message) throws Exception {
        Thread.sleep(2000);
        return message;
    }

    public Message printMessage(Message message) throws Exception {
        System.out.println("Routing to B");
        return message;
    }
}
