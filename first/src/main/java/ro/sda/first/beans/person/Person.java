package ro.sda.first.beans.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.sda.first.beans.speaker.Speaker;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Person {

    private final List<Speaker> languages;

    @Autowired
    public Person(Speaker... args) {
        this.languages = Arrays.asList(args);
    }

    public void saySomething() {
        Speaker language = languages.get(new Random().nextInt(languages.size()));
        language.sayHello();
        language.sayRandomWord();
    }

}
