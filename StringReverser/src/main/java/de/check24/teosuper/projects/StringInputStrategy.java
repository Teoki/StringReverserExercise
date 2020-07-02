package de.check24.teosuper.projects;

public interface StringInputStrategy {

    void readAndReverse();

    default void reverseString(String input) {
        StringBuilder reversedInput = new StringBuilder();
        reversedInput.append(input);
        reversedInput.reverse();
        System.out.println("Eingabe umgekehrt: " + reversedInput);
    }

}
