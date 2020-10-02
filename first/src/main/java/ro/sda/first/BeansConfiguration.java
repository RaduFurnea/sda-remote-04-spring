package ro.sda.first;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.sda.first.beans.person.Person;
import ro.sda.first.beans.speaker.EnglishSpeaker;
import ro.sda.first.beans.speaker.FrenchSpeaker;
import ro.sda.first.beans.speaker.Speaker;
import ro.sda.first.beans.writer.ConsoleWriter;
import ro.sda.first.beans.writer.FileWriter;
import ro.sda.first.beans.writer.Writer;

import java.util.Arrays;

@Configuration
public class BeansConfiguration {

    @Bean
    public Writer consoleWriter() {
        return new ConsoleWriter();
    }

    @Bean
    public Writer fileWriter() {
        FileWriter fileWriter = new FileWriter();
        fileWriter.setFilename("test.txt");
        return fileWriter;
    }

    @Bean
    public Speaker englishSpeaker(@Qualifier("consoleWriter") final Writer writer) {
        return new EnglishSpeaker(writer, Arrays.asList("Hello", "Please", "Thanks"));
    }

    @Bean
    public Speaker frenchSpeaker(@Qualifier("consoleWriter") final Writer writer) {
        return new FrenchSpeaker(writer, Arrays.asList("Bonjour", "Omelette", "Baguette"));
    }

    @Bean
    @Qualifier("multiLinguistPerson")
    public Person multiLinguist(@Qualifier("englishSpeaker") final Speaker englishSpeaker, @Qualifier("frenchSpeaker") final Speaker frenchSpeaker) {
        return new Person(englishSpeaker, frenchSpeaker);
    }

    @Bean
    @Qualifier("frenchPerson")
    public Person frenchPerson(@Qualifier("frenchSpeaker") final Speaker frenchSpeaker) {
        return new Person(frenchSpeaker);
    }

    @Bean
    @Qualifier("frenchPerson")
    public Person englishPerson(@Qualifier("englishSpeaker") final Speaker englishSpeaker) {
        return new Person(englishSpeaker);
    }

}
