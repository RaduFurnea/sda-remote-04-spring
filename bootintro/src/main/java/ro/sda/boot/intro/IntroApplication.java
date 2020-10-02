package ro.sda.boot.intro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.sda.boot.intro.entities.Book;

@SpringBootApplication
public class IntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntroApplication.class, args);
    }

}
