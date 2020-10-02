package ro.sda.first.beans.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class FileWriter implements Writer {

    private String fileName;

    public void setFilename(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeText(String text) {
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            writer.append(text);
        } catch (IOException ioe) {
            System.out.println("File writer encountered exception: " + ioe.getMessage());
        }
    }
}
