package de.check24.teosuper.projects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInputStrategy implements StringInputStrategy {
    @Override
    public void readAndReverse() {
        try {
            readAndReverseFile("resources/Text");
        } catch (IOException e) {
            System.out.println("Datei konnte nicht geöffnet werden");
        } finally {
            System.out.println("I've tried");
        }
    }

    private void readAndReverseFile(String dateiname) throws IOException {
        FileReader input = new FileReader(dateiname);
        try(BufferedReader bufferedInput = new BufferedReader(input)) {

            String inputLine = bufferedInput.readLine();

            System.out.println(inputLine);

            while (inputLine != null) {
                reverseString(inputLine);
                inputLine = bufferedInput.readLine();
            }
        }
    }

}
