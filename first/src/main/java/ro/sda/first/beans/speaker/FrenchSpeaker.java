package ro.sda.first.beans.speaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ro.sda.first.beans.writer.Writer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Random;

public class FrenchSpeaker implements Speaker {

    private final Writer writer;

    List<String> words;

    @Autowired
    public FrenchSpeaker(Writer writer, List<String> words) {
        this.writer = writer;
        this.words = words;
    }

    public void sayHello() {
        writer.writeText("Bonjour from Paris");
    }

    @Override
    public void sayRandomWord() {
        writer.writeText(words.get(new Random().nextInt(words.size())));
    }


    @PostConstruct
    public void init() {
        System.out.println("French Speaker initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("French Speaker destroyed");
    }
}
