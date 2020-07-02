package de.check24.teosuper.projects;

import java.util.Optional;
import java.util.Scanner;

public class App {

    public static final String ERROR_MESSAGE = "Sie können ja nur 1 oder 2 eingeben";

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("1 (Datei einlesen) oder 2 (Text eingabe) eingeben: ");
        Optional<UserInput> choice = UserInput.of(scanner.nextLine());
        //wenn choice überhaupt eine gültige zahl gespeichert hat dann nimm diese zahl (also den userinput und mache folgendes -->{...})
        choice.ifPresent(userInput -> {
            //hier ist das Strategy pattern implementiert
            switch (userInput.choice) {
                //TODO ENUMS BENUTZEN per enum klasse
                case "1":
                    choice1();
                    break;
                case "2":
                    choice2();
                    break;
            }
        });

        if (!choice.isPresent()) {
            System.out.print(ERROR_MESSAGE);
        }

        scanner.close();
    }

    public static void choice2() {
        Reader.getInput(new CommandLineInputStrategy());
    }

    public static void choice1() {
        Reader.getInput(new FileInputStrategy());
    }

}
