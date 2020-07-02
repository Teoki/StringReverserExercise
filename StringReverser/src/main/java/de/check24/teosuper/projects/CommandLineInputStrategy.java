package de.check24.teosuper.projects;

import java.util.Scanner;

public class CommandLineInputStrategy implements StringInputStrategy {
    @Override
    public void readAndReverse() {
        System.out.print("Geben Sie ein Wort ein: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        reverseString(input);
    }

}
