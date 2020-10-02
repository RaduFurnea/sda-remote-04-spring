package ro.sda.first.beans.writer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class ConsoleWriter implements Writer {
    @Override
    public void writeText(String text) {
        System.out.println(text);
    }
}
