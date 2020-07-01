import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class StringReverser {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("1 (Datei einlesen) oder 2 (Text eingabe) eingeben: ");
        Optional<UserInput> choice = UserInput.of(scanner.nextLine());
        //wenn choice überhaupt eine gültige zahl gespeichert hat dann nimm diese zahl (also den userinput und mache folgendes -->{...})
        choice.ifPresent(userInput -> {
            //TODO hier das Strategy pattern implementieren case 1 ist ein strategy und case 2 ist ein strategy
            switch (userInput.choice) {
                case 1:
                    new FileReverser().readAndReverse();
                    break;
                case 2:
                    new UserInputReverser().readAndReverse();
                    break;
            }
        });

        if (!choice.isPresent()) {
            System.out.print("Sie können nur 1 oder 2 eingeben");
        }

        scanner.close();
    }

    private static void reverseString(String input) {
        StringBuilder reversedInput = new StringBuilder();
        reversedInput.append(input);
        reversedInput.reverse();
        System.out.println("Eingabe umgekehrt: " + reversedInput);
    }

}
