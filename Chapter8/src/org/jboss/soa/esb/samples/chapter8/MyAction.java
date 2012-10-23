package org.jboss.soa.esb.samples.chapter8;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

public class MyAction extends AbstractActionLifecycle {

	protected ConfigTree _config;

	private String _books[] = new String[] { "Great Expectations",
			"Hound Of The Baskervilles", "The Da Vinci Code",
			"The Immortals Of Meluha" };
	private String _authors[] = new String[] { "Charles Dickens",
			"Sir Arthur Conan Doyle", "Dan Brown", "Amish Tripathi" };

	public MyAction(ConfigTree config) {
		_config = config;
	}

	public Message process(Message message) throws Exception {
		String req = (String) message.getBody().get();
		StringBuffer resp = new StringBuffer();
		if (req.indexOf("getAuthors") > 0) {
			resp.append("<ns2:getAuthorsResponse xmlns:ns2=\"http://chapter8.samples.esb.soa.jboss.org/\">");
			for (String author : _authors) {
				resp.append("<return>").append(author).append("</return>");
			}
			resp.append("</ns2:getAuthorsResponse>");
		} else if (req.indexOf("getBooks") > 0) {
			resp.append("<ns2:getBooksResponse xmlns:ns2=\"http://chapter8.samples.esb.soa.jboss.org/\">");
			for (String book : _books) {
				resp.append("<return>").append(book).append("</return>");
			}
			resp.append("</ns2:getBooksResponse>");
		} else {
			resp.append("Unknown request!");
		}
		message.getBody().add(resp);
		return message;
	}
}
