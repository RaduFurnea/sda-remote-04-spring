package ro.sda.spring.beans.writer;

import java.io.BufferedInputStream;
import java.io.IOException;

public class FileWriter implements Writer {

    private String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeText(String text) {
        // HINT! try-with-resources, which is another type of try-catch that auto-closes resources
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName, true)) {
            writer.append(text);
            writer.append("\n");
        } catch (IOException ioe) {
            System.out.println("File writer encountered exception: " + ioe.getMessage());
        } finally {
            System.out.println("[FileWriter - finally] This will always execute.");
        }
    }
}
