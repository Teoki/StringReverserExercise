package de.check24.teosuper.projects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static de.check24.teosuper.projects.App.ERROR_MESSAGE;
import static org.junit.Assert.*;

public class AppTest {

    //TODO Test schreiben mit input von der console und test für file auslesen und überprüfen ob es rückwärts ausgegeben wird
    //TODO Testen: 1 eingeben, dann File auslesen, dann Ergebins checken

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
    @Test
    public void positiveFileInputTest() {
        //TODO choice wird auch zu einem enum aus der enum klasse
        runFileTest("1", "Hallo, das ist ein kurzer Satz.\n" +
                "Eingabe umgekehrt: .ztaS rezruk nie tsi sad ,ollaH\n");
    }

    @Test
    public void negativeFileInputTest() {
        runFileTest("3", ERROR_MESSAGE);
    }

    @Test
    public void negativeFileInputTest2() {
        runFileTest("Blub", ERROR_MESSAGE);
    }


    @Test
    public void positiveCommandLineInputTest() {
        runCommandLineTest("Hallo", "ollaH");
    }

    @Test
    public void positiveCommandlineInputTest2() {
        runCommandLineTest("Test", "tseT");
    }

    private void runFileTest(final String choice, String expectedResult) {
        provideInput(choice);
        App.main(new String[0]);
        final String expected = getExpectedFileString(expectedResult);
        assertEquals(expected, getOutput());
    }

    private void runCommandLineTest(String userInput, String expectedResult) {
        provideInput(userInput);
        App.choice2();
        final String expected = getExpectedString(expectedResult);
        assertEquals(expected, getOutput());
    }

    private String getExpectedString(String data) {
        return "Geben Sie ein Wort ein: " +
                "Eingabe umgekehrt: " + data + "\n";
    }

    private String getExpectedFileString(String data) {
        return "1 (Datei einlesen) oder 2 (Text eingabe) eingeben: " + data;
    }

}
