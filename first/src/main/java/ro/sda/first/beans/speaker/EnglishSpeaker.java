package ro.sda.first.beans.speaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.sda.first.beans.writer.Writer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Random;

public class EnglishSpeaker implements Speaker {

    private final Writer writer;

    List<String> words;

    @Autowired
    public EnglishSpeaker(Writer writer, List<String> words) {
        this.words = words;
        this.writer = writer;
    }

    @Override
    public void sayHello() {
        writer.writeText("Hello from London");
    }

    @Override
    public void sayRandomWord() {
        writer.writeText(words.get(new Random().nextInt(words.size())));
    }

    @PostConstruct
    public void init() {
        System.out.println("English Speaker initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("English Speaker destroyed");
    }
}
