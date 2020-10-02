package ro.sda.boot.intro.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.boot.intro.entities.Book;
import ro.sda.boot.intro.repository.BookRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        bookRepository.save(new Book("", "", BigDecimal.valueOf(123) ,"" ));
        logger.info("Book service initialized");
    }

}
