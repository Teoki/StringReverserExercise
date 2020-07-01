import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReverser implements Reverser {
    @Override
    public void readAndReverse() {
        try {
            readAndReverseFile("resources/Text");
        } catch (IOException e) {
            System.out.println("Datei konnte nicht geöffnet werden");
        }
    }

    @Override
    public void reverseString(String input) {
        StringBuilder reversedInput = new StringBuilder();
        reversedInput.append(input);
        reversedInput.reverse();
        System.out.println("Eingabe umgekehrt: " + reversedInput);
    }

    private void readAndReverseFile(String dateiname) throws IOException {
        FileReader input = new FileReader(dateiname);
        BufferedReader bufferedInput = new BufferedReader(input);

        String inputLine = bufferedInput.readLine();

        System.out.println(inputLine);

        while (inputLine != null) {
            reverseString(inputLine);
            inputLine = bufferedInput.readLine();
        }
    }

}
