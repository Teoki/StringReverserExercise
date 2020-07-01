import java.util.Optional;
import java.util.Scanner;

//TODO git-ignore file konfigurieren
public class App {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("1 (Datei einlesen) oder 2 (Text eingabe) eingeben: ");
        Optional<UserInput> choice = UserInput.of(scanner.nextLine());
        //wenn choice überhaupt eine gültige zahl gespeichert hat dann nimm diese zahl (also den userinput und mache folgendes -->{...})
        choice.ifPresent(userInput -> {
            //hier ist das Strategy pattern implementiert
            switch (userInput.choice) {
                case 1:
                    Reader.getInput(new FileInputStrategy());
                    break;
                case 2:
                    Reader.getInput(new CommandLineInputStrategy());
                    break;
            }
        });

        if (!choice.isPresent()) {
            System.out.print("Sie können nur 1 oder 2 eingeben");
        }

        scanner.close();
    }

}
