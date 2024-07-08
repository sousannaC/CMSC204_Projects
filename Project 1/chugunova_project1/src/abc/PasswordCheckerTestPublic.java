package abc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class PasswordCheckerTestPublic {
	ArrayList<String> passwordsArray;
	String password = "Hello";
	String passwordConfirm = "hello";
	String allCaps = "HELLO";
	String withDigit = "Hello6";

	@BeforeEach
	void setUp() throws Exception {
		String[] p = {"334455BB", "Im2cool4U",withDigit,};
		passwordsArray = new ArrayList<String>();
		passwordsArray.addAll(Arrays.asList(p));
	}

	@AfterEach
	void tearDown() throws Exception {
		passwordsArray = null;
	}

	  @Test
	    void testComparePasswords() {
	        assertDoesNotThrow(() -> {
	            PasswordCheckerUtility.comparePasswords("password", "password");
	        });

	        assertThrows(IllegalArgumentException.class, () -> {
	            PasswordCheckerUtility.comparePasswords("password", "differentpassword");
	        });
	    }
	
	@Test
    void testComparePasswordsWithReturn() {
        assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn("password", "password"));
        assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn("password", "differentpassword"));
    }
	
	  @Test
	    void testHasUpperAlpha() {
	        assertTrue(PasswordCheckerUtility.hasUpperAlpha("PASSWORD"));
	        assertFalse(PasswordCheckerUtility.hasUpperAlpha("password"));
	    }

	
	  @Test
	    void testIsValidLength() {
	        assertTrue(PasswordCheckerUtility.isValidLength("password"));
	        assertFalse(PasswordCheckerUtility.isValidLength("pass"));
	    }

	
	@Test
	public void testGetInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
		assertEquals(results.size(), 3);
		assertEquals(results.get(0), "334455BB The password must contain at least one lowercase alphabetic character");
		assertEquals(results.get(1), "Im2cool4U The password must contain at least one special character");
	}

}
