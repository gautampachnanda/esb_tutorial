package org.jboss.soa.esb.samples.chapter8;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class MyLogHandler implements SOAPHandler<SOAPMessageContext> {
	private static Set<QName> headers;

	static {
		HashSet<QName> set = new HashSet<QName>();
		headers = Collections.unmodifiableSet(set);
	}

	public Set<QName> getHeaders() {
		return headers;
	}

	public void close(MessageContext messageContext) {
	}

	public boolean handleFault(SOAPMessageContext smc) {
		return true;
	}

	public boolean handleMessage(SOAPMessageContext msgContext) {
		System.out.println(msgContext.getMessage());
		return true;
	}

}
