//programmer:Sousanna Chugunova
//CMSC204 
//Dr.Thai
package abc;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PasswordCheckerTest_STUDENT {

    private ArrayList<String> validPasswords;
    private ArrayList<String> invalidPasswords;

    @Before
    public void setUp() throws Exception {
        // Set up valid passwords
        validPasswords = new ArrayList<>();
        validPasswords.add("ValidPassword1@");
        validPasswords.add("AnotherValidPassword2#");

        // Set up invalid passwords
        invalidPasswords = new ArrayList<>();
        invalidPasswords.add("short");
        invalidPasswords.add("invalidPass");
    }

    @After
    public void tearDown() throws Exception {
        // Clean up
        validPasswords = null;
        invalidPasswords = null;
    }

    /**
     * Test if the password is less than 6 characters long.
     * This test should throw a LengthException for second case.
     */
    @Test
    public void testIsValidPasswordTooShort() {
        try {
            PasswordCheckerUtility.isValidPassword("short");
            fail("Expected LengthException was not thrown");
        } catch (LengthException e) {
            // Expected
            assertTrue(true);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test if the password has at least one uppercase alpha character
     * This test should throw a NoUpperAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha() {
        try {
            PasswordCheckerUtility.isValidPassword("password");
            fail("Expected NoUpperAlphaException was not thrown");
        } catch (NoUpperAlphaException e) {
            // Expected
            assertTrue(true);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test if the password has at least one lowercase alpha character
     * This test should throw a NoLowerAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha() {
        try {
            PasswordCheckerUtility.isValidPassword("PASSWORD");
            fail("Expected NoLowerAlphaException was not thrown");
        } catch (NoLowerAlphaException e) {
            // Expected
            assertTrue(true);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test if the password is weak (i.e., its length is between 6 and 9 characters).
     */
    @Test
    public void testIsWeakPassword() {
        assertTrue(PasswordCheckerUtility.isWeakPassword("weak123"));
        assertFalse(PasswordCheckerUtility.isWeakPassword("strongPassword123@"));
    }

    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw an InvalidSequenceException for the given case
     */
    @Test
    public void testIsValidPasswordInvalidSequence() {
        try {
            PasswordCheckerUtility.isValidPassword("Aa1@@@");
            fail("Expected InvalidSequenceException was not thrown");
        } catch (InvalidSequenceException e) {
            // Expected InvalidSequenceException
            assertTrue(true);
        } catch (Exception e) {
            // Unexpected exception, fail the test
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }


    /**
     * Test if the password has at least one digit
     * This test should throw a NoDigitException for second case
     */
    @Test
    public void testIsValidPasswordNoDigit() {
        try {
            PasswordCheckerUtility.isValidPassword("NoDigitPassword");
            fail("Expected NoDigitException was not thrown");
        } catch (NoDigitException e) {
            // Expected
            assertTrue(true);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test correct passwords
     * This test should not throw an exception
     */
    @Test
    public void testIsValidPasswordSuccessful() {
        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("ValidPassword1@"));
            assertTrue(PasswordCheckerUtility.isValidPassword("AnotherValidPassword2#"));
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test the invalidPasswords method
     * Check the results of the ArrayList of Strings returned by the invalidPasswords method
     */
    @Test
    public void testInvalidPasswords() {
        ArrayList<String> invalidPasswords = PasswordCheckerUtility.invalidPasswords(this.invalidPasswords);
        assertEquals(2, invalidPasswords.size());
    }
}
