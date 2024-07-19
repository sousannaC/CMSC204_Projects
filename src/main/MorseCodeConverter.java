//Sousanna Chugunova
//CMSC204
//Dr.Thai

package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MorseCodeConverter {

    // Mapping from Morse code to English letters
    private static final Map<String, String> morseToEnglishMap = new HashMap<>();

    static {
        // Populate the map with Morse code to English letter mappings
        morseToEnglishMap.put(".-", "a");
        morseToEnglishMap.put("-...", "b");
        morseToEnglishMap.put("-.-.", "c");
        morseToEnglishMap.put("-..", "d");
        morseToEnglishMap.put(".", "e");
        morseToEnglishMap.put("..-.", "f");
        morseToEnglishMap.put("--.", "g");
        morseToEnglishMap.put("....", "h");
        morseToEnglishMap.put("..", "i");
        morseToEnglishMap.put(".---", "j");
        morseToEnglishMap.put("-.-", "k");
        morseToEnglishMap.put(".-..", "l");
        morseToEnglishMap.put("--", "m");
        morseToEnglishMap.put("-.", "n");
        morseToEnglishMap.put("---", "o");
        morseToEnglishMap.put(".--.", "p");
        morseToEnglishMap.put("--.-", "q");
        morseToEnglishMap.put(".-.", "r");
        morseToEnglishMap.put("...", "s");
        morseToEnglishMap.put("-", "t");
        morseToEnglishMap.put("..-", "u");
        morseToEnglishMap.put("...-", "v");
        morseToEnglishMap.put(".--", "w");
        morseToEnglishMap.put("-..-", "x");
        morseToEnglishMap.put("-.--", "y");
        morseToEnglishMap.put("--..", "z");
        morseToEnglishMap.put("-----", "0");
        morseToEnglishMap.put(".----", "1");
        morseToEnglishMap.put("..---", "2");
        morseToEnglishMap.put("...--", "3");
        morseToEnglishMap.put("....-", "4");
        morseToEnglishMap.put(".....", "5");
        morseToEnglishMap.put("-....", "6");
        morseToEnglishMap.put("--...", "7");
        morseToEnglishMap.put("---..", "8");
        morseToEnglishMap.put("----.", "9");
        morseToEnglishMap.put("/", " "); // Slash for word separation
    }

    /**
     * Convert a Morse code string to English.
     * @param morseCode Morse code string to convert
     * @return English translation of the Morse code
     */
    public static String convertToEnglish(String morseCode) {
        if (morseCode == null || morseCode.trim().isEmpty()) {
            return "";
        }

        StringBuilder englishBuilder = new StringBuilder();
        String[] words = morseCode.split(" / "); // Split words by " / "

        for (String word : words) {
            String[] letters = word.split(" "); // Split letters by " "
            for (String letter : letters) {
                if (morseToEnglishMap.containsKey(letter)) {
                    englishBuilder.append(morseToEnglishMap.get(letter));
                } else {
                    englishBuilder.append("?"); // Handle invalid Morse code
                }
            }
            englishBuilder.append(" "); // Add space between words
        }

        return englishBuilder.toString().trim(); // Trim trailing space
    }



    /**
     * Convert Morse code from a file to English.
     * @param file File containing Morse code
     * @return English translation of the Morse code
     * @throws IOException If an error occurs while reading the file
     */
    public static String convertToEnglish(File file) throws IOException {
        StringBuilder morseCodeBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                morseCodeBuilder.append(line).append(" ");
            }
        }

        return convertToEnglish(morseCodeBuilder.toString().trim());
    }
    
    public static String convertAllMorseLetters() {
        StringBuilder result = new StringBuilder();

        // Iterate through the Morse code map and collect all translated letters
        for (String key : morseToEnglishMap.keySet()) {
            String letter = morseToEnglishMap.get(key);
            if (letter != null) {
                result.append(letter).append(" ");
            }
        }

        return result.toString().trim();
    }

    public static String printTree() {
        StringBuilder sb = new StringBuilder();
        String[] letters = "h s v i f u e l r a p w j b d x n c k y t z g q m o".split(" ");
        for (String letter : letters) {
            sb.append(letter).append(" ");
        }
        return sb.toString().trim();
    
    }
	}

