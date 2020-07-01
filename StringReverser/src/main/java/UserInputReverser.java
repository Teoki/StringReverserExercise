import java.util.Scanner;

public class UserInputReverser implements Reverser {
    @Override
    public void readAndReverse() {
        System.out.print("Geben Sie ein Wort ein: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        reverseString(input);
    }

    @Override
    public void reverseString(String input) {
        StringBuilder reversedInput = new StringBuilder();
        reversedInput.append(input);
        reversedInput.reverse();
        System.out.println("Eingabe umgekehrt: " + reversedInput);
    }

}
