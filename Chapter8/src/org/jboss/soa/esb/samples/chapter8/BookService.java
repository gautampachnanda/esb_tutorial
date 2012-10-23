package org.jboss.soa.esb.samples.chapter8;

import javax.jws.WebService;

@WebService
public class BookService {

    public String[] getBooks() {
        return new String[]{"Great Expectations", "Hound Of The Baskervilles", "The Da Vinci Code", "The Immortals Of Meluha"};
    }

    public String[] getAuthors() {
        return new String[]{"Charles Dickens", "Sir Arthur Conan Doyle", "Dan Brown", "Amish Tripathi"};
    }

}
