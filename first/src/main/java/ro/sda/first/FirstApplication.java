package ro.sda.first;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.sda.first.beans.person.Person;
import ro.sda.first.beans.speaker.EnglishSpeaker;
import ro.sda.first.beans.speaker.FrenchSpeaker;
import ro.sda.first.beans.speaker.Speaker;
import ro.sda.first.beans.writer.ConsoleWriter;
import ro.sda.first.beans.writer.Writer;

import java.io.Console;

public class FirstApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BeansConfiguration.class);
        Speaker englishSpeaker = context.getBean(EnglishSpeaker.class);
        Speaker frenchSpeaker = context.getBean(FrenchSpeaker.class);

        Writer consoleWriter = context.getBean(ConsoleWriter.class);
        consoleWriter.writeText("test");

//        englishSpeaker.sayHello();
//        frenchSpeaker.sayHello();
//
//        englishSpeaker.sayRandomWord();
//        frenchSpeaker.sayRandomWord();

        Person multiLinguistPerson = context.getBean("multiLinguist", Person.class);
        multiLinguistPerson.saySomething();

//        Person frenchPerson = context.getBean("frenchPerson", Person.class);
//        frenchPerson.saySomething();
//
//        Person englishPerson = context.getBean("englishPerson", Person.class);
//        englishPerson.saySomething();

        context.close();
    }
}
