//programmer:Sousanna Chugunova
//CMSC204 
//Dr.Thai
package abc;

import java.util.ArrayList;

public class PasswordCheckerUtility {

    /**
     * Checks if a password is valid according to various criteria.
     *
     * @param password The password to validate.
     * @return true if the password is valid.
     * @throws LengthException           If the password length is less than 6 characters.
     * @throws NoUpperAlphaException     If the password does not contain an uppercase alphabetic character.
     * @throws NoLowerAlphaException     If the password does not contain a lowercase alphabetic character.
     * @throws NoDigitException          If the password does not contain a numeric character.
     * @throws InvalidSequenceException  If the password contains more than two of the same character in sequence.
     * @throws NoSpecialCharacterException If the password does not contain a special character.
     */
    public static boolean isValidPassword(String password) throws
            LengthException,
            NoUpperAlphaException,
            NoLowerAlphaException,
            NoDigitException,
            InvalidSequenceException,
            NoSpecialCharacterException {
        validateLength(password);
        validateUpperCase(password);
        validateLowerCase(password);
        validateDigit(password);
        validateSequence(password);
        validateSpecialCharacter(password);
        return true;
    }

    /**
     * Validates the length of the password.
     *
     * @param password The password to validate.
     * @throws LengthException If the password length is less than 6 characters.
     */
    private static void validateLength(String password) throws LengthException {
        if (password.length() < 6) {
            throw new LengthException("The password must be at least 6 characters long");
        }
    }

    /**
     * Validates the presence of an uppercase alphabetic character in the password.
     *
     * @param password The password to validate.
     * @throws NoUpperAlphaException If the password does not contain an uppercase alphabetic character.
     */
    private static void validateUpperCase(String password) throws NoUpperAlphaException {
        if (!password.matches(".*[A-Z].*")) {
            throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
        }
    }

    /**
     * Validates the presence of a lowercase alphabetic character in the password.
     *
     * @param password The password to validate.
     * @throws NoLowerAlphaException If the password does not contain a lowercase alphabetic character.
     */
    private static void validateLowerCase(String password) throws NoLowerAlphaException {
        if (!password.matches(".*[a-z].*")) {
            throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
        }
    }

    /**
     * Validates the presence of a numeric character in the password.
     *
     * @param password The password to validate.
     * @throws NoDigitException If the password does not contain a numeric character.
     */
    private static void validateDigit(String password) throws NoDigitException {
        if (!password.matches(".*\\d.*")) {
            throw new NoDigitException("The password must contain at least one digit");
        }
    }

    /**
     * Validates that the password does not contain more than two of the same character in sequence.
     *
     * @param password The password to validate.
     * @throws InvalidSequenceException If the password contains more than two of the same character in sequence.
     */
    private static void validateSequence(String password) throws InvalidSequenceException {
        if (password.matches(".*(.)\\1{2,}.*")) {
            throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
        }
    }
    
    
    /**
     * Validates the presence of a special character in the password.
     *
     * @param password The password to validate.
     * @throws NoSpecialCharacterException If the password does not contain a special character.
     */
    private static void validateSpecialCharacter(String password) throws NoSpecialCharacterException {
        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            throw new NoSpecialCharacterException("The password must contain at least one special character");
        }
    }

    /**
     * Gets the list of invalid passwords from the provided list.
     *
     * @param passwords The list of passwords to check.
     * @return A list of invalid passwords along with the corresponding exception messages.
     */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalid = new ArrayList<>();
        for (String password : passwords) {
            try {
                isValidPassword(password);
            } catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException | InvalidSequenceException | NoSpecialCharacterException e) {
                invalid.add(password + " " + e.getMessage());
            }
        }
        return invalid;
    }

    /**
     * Checks if the password is weak (i.e., its length is between 6 and 9 characters).
     * 
     * @param password The password to check.
     * @return true if the password is weak, false otherwise.
     */
    public static boolean isWeakPassword(String password) {
        int length = password.length();
        return length >= 6 && length <= 9;
    }

    /**
     * Compares two passwords for equality.
     *
     * @param password The first password.
     * @param passwordConfirm The second password.
     * @throws IllegalArgumentException If the passwords do not match.
     */
    public static void comparePasswords(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    /**
     * Compares two passwords for equality and returns a boolean value indicating whether they match or not.
     *
     * @param password The first password.
     * @param passwordConfirm The second password.
     * @return true if the passwords match, false otherwise.
     */
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    /**
     * Checks if the password contains between 6 and 9 characters.
     *
     * @param password The password to check.
     * @return true if the password contains between 6 and 9 characters, false otherwise.
     */
    public static boolean hasBetweenSixAndNineChars(String password) {
        int length = password.length();
        return length >= 6 && length <= 9;
    }

    /**
     * Checks if the password contains at least one digit.
     *
     * @param password The password to check.
     * @return true if the password contains at least one digit, false otherwise.
     */
    public static boolean hasDigit(String password) {
        return password.matches(".*\\d.*");
    }

    /**
     * Checks if the password contains at least one lowercase alphabetic character.
     *
     * @param password The password to check.
     * @return true if the password contains at least one lowercase alphabetic character, false otherwise.
     */
    public static boolean hasLowerAlpha(String password) {
        return password.matches(".*[a-z].*");
    }

    /**
     * Checks if the password contains at least one special character.
     *
     * @param password The password to check.
     * @return true if the password contains at least one special character, false otherwise.
     */
    public static boolean hasSpecialChar(String password) {
        return password.matches(".*[^a-zA-Z0-9].*");
    }

    /**
     * Checks if the password contains at least one uppercase alphabetic character.
     *
     * @param password The password to check.
     * @return true if the password contains at least one uppercase alphabetic character, false otherwise.
     */
    public static boolean hasUpperAlpha(String password) {
        return password.matches(".*[A-Z].*");
    }

    /**
     * Checks if the password has a valid length of at least 6 characters.
     *
     * @param password The password to check.
     * @return true if the password has a valid length, false otherwise.
     */
    public static boolean isValidLength(String password) {
        return password.length() >= 6;
    }

    /**
     * Checks if the password does not contain more than two of the same character in sequence.
     *
     * @param password The password to check.
     * @return true if the password does not contain more than two of the same character in sequence, false otherwise.
     */
    public static boolean noSameCharInSequence(String password) {
        return !password.matches(".*(.)\\1{2,}.*");
    }

    /**
     * Gets the list of invalid passwords from the provided list.
     *
     * @param invalidPasswords The list of passwords to check.
     * @return A list of invalid passwords along with the corresponding exception messages.
     */
    public static ArrayList<String> invalidPasswords(ArrayList<String> invalidPasswords) {
        ArrayList<String> invalid = new ArrayList<>();
        for (String password : invalidPasswords) {
            try {
                isValidPassword(password);
            } catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException | InvalidSequenceException | NoSpecialCharacterException e) {
                invalid.add(password + " " + e.getMessage());
            }
        }
        return invalid;
    }
}
