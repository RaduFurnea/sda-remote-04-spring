package ro.sda.spring.beans.person;

import org.springframework.beans.factory.annotation.Autowired;
import ro.sda.spring.beans.speaker.Speaker;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Person implements PersonIF {

    private List<Speaker> languages;

    @Autowired
    public Person(Speaker... languages) {
        this.languages = Arrays.asList(languages);
    }
    
    public void sayRandomWord() {
        languages.get(new Random().nextInt(languages.size())).sayRandomWord();
    }
}
