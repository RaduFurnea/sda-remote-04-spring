package ro.sda.spring.beans.speaker;

import org.springframework.beans.factory.annotation.Autowired;
import ro.sda.spring.beans.writer.Writer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Random;

public class FrenchSpeaker implements Speaker {

    private final Writer writer;

    private List<String> words;

    @Autowired
    public FrenchSpeaker(Writer writer, List<String> words) {
        this.writer = writer;
        this.words = words;
    }

    public void sayHello() {
        writer.writeText("Bonjour from Paris!");
    }
    
    public void sayRandomWord() {
        writer.writeText(words.get(new Random().nextInt(words.size())));
    }

    // Annotation that instructs the context that the method should be called on context initialization
    @PostConstruct
    public void init() {
        System.out.println("French Speaker is here.");
    }

    // Annotation that instructs the context that the method should be called on context destroy/close
    @PreDestroy
    public void destroy() {
        System.out.println("French Speaker left.");
    }
}
