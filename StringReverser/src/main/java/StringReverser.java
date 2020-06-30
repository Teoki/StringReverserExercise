import java.io.*;
import java.util.Optional;
import java.util.Scanner;

//TODO github repo erstellen und pushen UND maven einbinden

public class StringReverser {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("1 (Datei einlesen) oder 2 (Text eingabe) eingeben: ");

        //TODO UserInput einbinden
        Optional<UserInput> choice = UserInput.of(scanner.next());

        if (choice.equals(1)) {
            try {
                readAndReverseFile("resources/Text");
            } catch (IOException e) {
                System.out.println("Datei konnte nicht geöffnet werden");
            }
        }

        if (choice.equals(2)) {
            System.out.print("Gebe ein Wort ein: ");
            String input = scanner.next();
            reverseString(input);
        }
        scanner.close();
    }

    private static void readAndReverseFile(String dateiname) throws IOException {
        FileReader input = new FileReader(dateiname);
        BufferedReader bufferedInput = new BufferedReader(input);

        String inputLine = bufferedInput.readLine();

        System.out.println(inputLine);

        while (inputLine != null) {
            reverseString(inputLine);
            inputLine = bufferedInput.readLine();
        }
    }

    private static void reverseString(String input) {
        StringBuilder reversedInput = new StringBuilder();
        reversedInput.append(input);
        reversedInput.reverse();
        System.out.println("Eingabe umgekehrt: " + reversedInput);
    }

}
