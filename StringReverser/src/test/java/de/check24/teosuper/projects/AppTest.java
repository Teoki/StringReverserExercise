package de.check24.teosuper.projects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static de.check24.teosuper.projects.App.ERROR_MESSAGE;
import static de.check24.teosuper.projects.App.INFORMATION_MESSAGE_CHOICE;
import static org.junit.Assert.*;

public class AppTest {
    private static final String FILE_CONTENT = "Hallo, das ist ein kurzer Satz.\n";
    private static final String FILE_CONTENT_REVERSED = "Eingabe umgekehrt: .ztaS rezruk nie tsi sad ,ollaH\n";
    private static final String USER_INPUT = "Hallo";
    private static final String USER_INPUT_REVERSED = "ollaH";
    private static final String USER_INPUT_2 = "Test";
    private static final String USER_INPUT_2_REVERSED = "tseT";
    private static final String INCORRECT_CHOICE = "3";
    private static final String INCORRECT_CHOICE_2 = "Blub";

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    //erster aufruf wegen dem @Before
    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    //zweiter aufruf, weil es in der @Test Methode (public void mainMethodTest()) aufgerufen wird
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    //dritter aufruf, weil von mainMethodTest() aufgerufen
    private String getOutput() {
        return testOut.toString();
    }

    //4. aufruf, weil @After Annotation
    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    //Wenn diese Methode gestestet wird, werden die helper Methoden aufgerufen
    //TODO Problem war, dass es kein findByValue gab. Er hat nur nach einem Enum gesucht was "1" heißt und nicht nach dem Wert "1" von CHOICE_FILE_INPUT
    @Test
    public void positiveFileInputTest() {
        runFileTest(EnumInputChoices.CHOICE_FILE_INPUT.getValue(), FILE_CONTENT + FILE_CONTENT_REVERSED);
    }

    @Test
    public void negativeFileInputTest() {
        runFileTest(INCORRECT_CHOICE, ERROR_MESSAGE);
    }

    @Test
    public void negativeFileInputTest2() {
        runFileTest(INCORRECT_CHOICE_2, ERROR_MESSAGE);
    }


    @Test
    public void positiveCommandLineInputTest() {
        runCommandLineTest(USER_INPUT, USER_INPUT_REVERSED);
    }

    @Test
    public void positiveCommandlineInputTest2() {
        runCommandLineTest(USER_INPUT_2, USER_INPUT_2_REVERSED);
    }

    private void runFileTest(final String choice, String expectedResult) {
        provideInput(choice);
        App.main(new String[0]);
        final String expected = getExpectedFileString(expectedResult);
        assertEquals(expected, getOutput());
    }

    private void runCommandLineTest(String userInput, String expectedResult) {
        provideInput(userInput);
        App.workWithCommandLineInput();
        final String expected = getExpectedString(expectedResult);
        assertEquals(expected, getOutput());
    }

    private String getExpectedString(String data) {
        return "Geben Sie ein Wort ein: " +
                "Eingabe umgekehrt: " + data + "\n";
    }

    private String getExpectedFileString(String data) {
        return INFORMATION_MESSAGE_CHOICE + data;
    }

}
