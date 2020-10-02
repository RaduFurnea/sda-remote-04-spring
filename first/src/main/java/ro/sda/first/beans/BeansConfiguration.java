package ro.sda.first.beans;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.sda.first.entities.Book;

@Configuration
public class BeansConfiguration {

    @Bean
    public Book myBook() {
        Book book = new Book("Game of Thrones", "George R.R. Martin");
        return book;
    }

}
