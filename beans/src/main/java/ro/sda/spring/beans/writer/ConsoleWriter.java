package ro.sda.spring.beans.writer;

public class ConsoleWriter implements Writer {

    public void writeText(String text) {
        System.out.println("Console writer says: " + text);
    }
}
