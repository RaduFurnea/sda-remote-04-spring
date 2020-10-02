package ro.sda.first;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.sda.first.beans.BeansConfiguration;
import ro.sda.first.beans.TestClass;

public class FirstApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeansConfiguration.class);
        context.register(TestClass.class);
        context.refresh();
    }

}
