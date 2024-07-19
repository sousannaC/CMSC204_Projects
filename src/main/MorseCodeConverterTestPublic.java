package main;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class MorseCodeConverterTestPublic {

    @Test
    public void testConvertToEnglishString() {
        String result = MorseCodeConverter.convertToEnglish(".... . .-.. .-.. --- / .-- --- .-. .-.. -.. ");
        assertEquals("hello world", result);
    }

    @Test
    public void testPrintTree() {
        String expectedOutput = "h s v i f u e l r a p w j b d x n c k y t z g q m o";
        assertEquals(expectedOutput, MorseCodeConverter.printTree());
    }

    @Test
    public void testConvertMorseStringToEnglishString() {
        String result = MorseCodeConverter.convertToEnglish("- .... . / --.- ..- .. -.-. -.- / -... .-. --- .-- -. / ..-. --- -..- / .--- ..- -- .--. ... / --- ...- . .-. / - .... . / .-.. .- --.. -.-- / -.. --- --.");
        assertEquals("the quick brown fox jumps over the lazy dog", result);
    }

    @Test
    public void testConvertMorseFileToEnglishString() throws IOException {
        File file = new File("src/howDoILoveThee.txt"); 
        try {
            String result = MorseCodeConverter.convertToEnglish(file);
            assertEquals("how do i love thee let me count the ways", result);
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException was thrown: " + e.getMessage());
        }
    }
}
