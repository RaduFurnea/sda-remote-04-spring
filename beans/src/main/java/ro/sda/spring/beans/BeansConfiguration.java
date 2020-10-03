package ro.sda.spring.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.sda.spring.beans.person.Person;
import ro.sda.spring.beans.speaker.EnglishSpeaker;
import ro.sda.spring.beans.speaker.FrenchSpeaker;
import ro.sda.spring.beans.speaker.Speaker;
import ro.sda.spring.beans.writer.ConsoleWriter;
import ro.sda.spring.beans.writer.FileWriter;
import ro.sda.spring.beans.writer.Writer;

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
        fileWriter.setFileName("injectedFileName.txt");
        return fileWriter;
    }

    @Bean
    public Speaker englishSpeaker(@Qualifier("consoleWriter") final Writer writer) {
        return new EnglishSpeaker(writer, Arrays.asList("Hello", "Please", "Thanks"));
    }

    @Bean
    public Speaker frenchSpeaker(@Qualifier("fileWriter") final Writer writer) {
        return new FrenchSpeaker(writer, Arrays.asList("Bonjour", "Merci", "Bonsoir"));
    }

    @Bean
    public Person englishPerson(@Qualifier("englishSpeaker") final Speaker englishSpeaker) {
        return new Person(englishSpeaker);
    }

    @Bean
    public Person frenchPerson(@Qualifier("frenchSpeaker") final Speaker frenchSpeaker) {
        return new Person(frenchSpeaker);
    }

    @Bean
    public Person multiLinguistPerson(@Qualifier("frenchSpeaker") final Speaker frenchSpeaker,
                                      @Qualifier("englishSpeaker") final Speaker englishSpeaker) {
        return new Person(frenchSpeaker, englishSpeaker);
    }

}
