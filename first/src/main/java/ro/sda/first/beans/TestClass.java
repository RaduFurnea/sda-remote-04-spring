package ro.sda.first.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.sda.first.entities.Book;

import javax.annotation.PostConstruct;

@Component
public class TestClass {

    private final Book book;

    @Autowired
    public TestClass(Book book) {
        this.book = book;
    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing Test Class bean");
        System.out.println(book.getName() + " by " + book.getAuthor());
    }
}
