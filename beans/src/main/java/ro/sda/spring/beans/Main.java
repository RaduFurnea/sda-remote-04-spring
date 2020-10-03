package ro.sda.spring.beans;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.sda.spring.beans.person.Person;
import ro.sda.spring.beans.speaker.EnglishSpeaker;
import ro.sda.spring.beans.speaker.FrenchSpeaker;
import ro.sda.spring.beans.speaker.Speaker;
import ro.sda.spring.beans.writer.ConsoleWriter;
import ro.sda.spring.beans.writer.FileWriter;
import ro.sda.spring.beans.writer.Writer;

public class Main {

    public static void main(String[] args) {
        // Create IoC context given the @Configuration class
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BeansConfiguration.class);

        System.out.println("----------------------------- Context initialized ----------------------------");

        // Retrieve beans from defined context
        Speaker englishSpeaker = context.getBean(EnglishSpeaker.class);
        Speaker frenchSpeaker = context.getBean(FrenchSpeaker.class);
        Writer consoleWriter = context.getBean(ConsoleWriter.class);
        Writer fileWriter = context.getBean(FileWriter.class);
        Person englishPerson = context.getBean("englishPerson", Person.class);
        Person frenchPerson = context.getBean("frenchPerson", Person.class);
        Person multiLinguistPerson = context.getBean("multiLinguistPerson", Person.class);

        // do stuff
        englishSpeaker.sayHello();
        englishSpeaker.sayRandomWord();
        System.out.println("----------------------------- English Speaker said hello and random ----------------------------");

        frenchSpeaker.sayHello();
        frenchSpeaker.sayRandomWord();
        System.out.println("----------------------------- French Speaker said hello and random ----------------------------");

        englishPerson.sayRandomWord();
        System.out.println("----------------------------- English Person random word ----------------------------");

        frenchPerson.sayRandomWord();
        System.out.println("----------------------------- French Person random word ----------------------------");

        multiLinguistPerson.sayRandomWord();
        System.out.println("----------------------------- MultiLinguistPerson Person random word ----------------------------");

        System.out.println("----------------------------- Context will be closing ----------------------------");
        // Close context
        context.close();
    }
}
