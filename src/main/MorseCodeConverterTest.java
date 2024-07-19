//Sousanna Chugunova
//CMSC204
//Dr.Thai
package main;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class MorseCodeConverterTest {

    @Test
    public void testConvertToEnglishString() {
        // Test for converting Morse code to English string
        String morseCode = ".... . .-.. .-.. --- / .-- --- .-. .-.. -..";
        String expected = "hello world";
        String result = MorseCodeConverter.convertToEnglish(morseCode);
        assertEquals(expected, result, "The Morse code translation to English is incorrect.");
    }

    @Test
    public void testConvertToEnglishFile() {
        // Adjust the path as per your setup
        File inputFile = new File("src/howDoILoveThee.txt");
        String expected = "how do i love thee let me count the ways";
        
        if (!inputFile.exists()) {
            fail("File not found: " + inputFile.getPath());
        } else {
            try {
                String result = MorseCodeConverter.convertToEnglish(inputFile);
                assertEquals(expected, result, "The Morse code conversion from file to English is incorrect.");
            } catch (IOException e) {
                e.printStackTrace();
                fail("IOException occurred: " + e.getMessage());
            }
        }
    }

    @Test
    public void testConvertSingleLetter() {
        // Test for converting a single Morse code letter to English
        String morseCode = "....";
        String expected = "h";
        String result = MorseCodeConverter.convertToEnglish(morseCode);
        assertEquals(expected, result, "The Morse code translation for '....' is incorrect.");
    }

    @Test
    public void testConvertSingleWord() {
        // Test for converting a single Morse code word to English
        String morseCode = ".... . .-.. .-.. ---";
        String expected = "hello";
        String result = MorseCodeConverter.convertToEnglish(morseCode);
        assertEquals(expected, result, "The Morse code translation for '.... . .-.. .-.. ---' is incorrect.");
    }

    @Test
    public void testConvertSingleSentence() {
        // Test for converting a Morse code sentence to English
        String morseCode = ".... . .-.. .-.. --- / .-- --- .-. .-.. -..";
        String expected = "hello world";
        String result = MorseCodeConverter.convertToEnglish(morseCode);
        assertEquals(expected, result, "The Morse code translation for '.... . .-.. .-.. --- / .-- --- .-. .-.. -..' is incorrect.");
    }

    @Test
    public void testConvertAllMorseLetters() {
        // Test for converting all Morse code letters (A-Z) to English
        String morseCode = ".- / -... / -.-. / -.. / . / ..-. / --. / .... / .. / .--- / -.- / .-.. / -- / -. / --- / .--. / --.- / .-. / ... / - / ..- / ...- / .-- / -..- / -.-- / --..";
        String expected = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
        String result = MorseCodeConverter.convertToEnglish(morseCode);
        assertEquals(expected, result, "The Morse code translation for all letters is incorrect.");
    }


    @Test
    public void testConvertComplexMorseCode() {
        // Test for a more complex Morse code sequence
        String morseCode = "- .... . / --.- ..- .. -.-. -.- / -... .-. --- .-- -. / ..-. --- -..- / .--- ..- -- .--. ... / --- ...- . .-. / - .... . / .-.. .- --.. -.-- / -.. --- --.";
        String expected = "the quick brown fox jumps over the lazy dog";
        String result = MorseCodeConverter.convertToEnglish(morseCode);
        assertEquals(expected, result, "The Morse code translation for 'the quick brown fox jumps over the lazy dog' is incorrect.");
    }

    @Test
    public void testConvertEmptyString() {
        // Test for an empty Morse code string
        String morseCode = "";
        String expected = "";
        String result = MorseCodeConverter.convertToEnglish(morseCode);
        assertEquals(expected, result, "The Morse code translation for an empty string should be empty.");
    }
}


