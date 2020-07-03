package de.check24.teosuper.projects;

import java.util.Optional;
import java.util.Scanner;

public class App {

    public static final String ERROR_MESSAGE = "Sie können nur 1 oder 2 eingeben";
    public static final String INFORMATION_MESSAGE_CHOICE = "1 (Datei einlesen) oder 2 (Text eingabe) eingeben: ";

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print(INFORMATION_MESSAGE_CHOICE);
        Optional<UserInput> choice = UserInput.of(scanner.nextLine());
        //wenn choice überhaupt eine gültige zahl gespeichert hat dann nimm diese zahl (also den userinput und mache folgendes -->{...})
        choice.ifPresent(userInput -> {
            //hier ist das Strategy pattern implementiert
            switch (userInput.choice) {
                case CHOICE_FILE_INPUT:
                    workWithFile();
                    break;
                case CHOICE_COMMANDLINE_INPUT:
                    workWithCommandLineInput();
                    break;
            }
        });

        if (!choice.isPresent()) {
            System.out.print(ERROR_MESSAGE);
        }

        scanner.close();
    }

    public static void workWithFile() {
        Reader.getInput(new FileInputStrategy());
    }

    public static void workWithCommandLineInput() {
        Reader.getInput(new CommandLineInputStrategy());
    }

}
