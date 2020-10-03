package ro.sda.spring.beans.speaker;

import org.springframework.beans.factory.annotation.Autowired;
import ro.sda.spring.beans.writer.Writer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.List;

public class EnglishSpeaker implements Speaker {

    private final Writer writer;

    private List<String> words;

    @Autowired
    public EnglishSpeaker(Writer writer, List<String> words) {
        this.writer = writer;
        this.words = words;
    }

    public void sayHello() {
        writer.writeText("Hello from London!");
    }
    
    public void sayRandomWord() {
        Collections.shuffle(words);
        writer.writeText(words.get(0));
    }

    // Annotation that instructs the context that the method should be called on initialization
    @PostConstruct
    public void init() {
        System.out.println("English Speaker is here.");
    }

    // Annotation that instructs the context that the method should be called on context destroy/close
    @PreDestroy
    public void destroy() {
        System.out.println("English Speaker left.");
    }

}
