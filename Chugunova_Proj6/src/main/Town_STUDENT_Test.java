//Sousanna Chugunova
//CMSC204
//Dr.Thai


package main;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {
    // Town objects used in the tests
    private Town town1, town2, town3;

    /**
     * Initializes the test environment before each test.
     * Creates instances of Town for testing.
     */
    @Before
    public void setUp() {
        town1 = new Town("Town1"); // Create a Town object with name "Town1"
        town2 = new Town("Town2"); // Create a Town object with name "Town2"
        town3 = new Town("Town1"); // Create another Town object with the same name as town1
    }

    /**
     * Tests the getName method of the Town class.
     * Verifies that the name of town1 is correctly returned.
     */
    @Test
    public void testGetName() {
        assertEquals("Town1", town1.getName()); // Check that town1's name is "Town1"
    }

    /**
     * Tests the compareTo method of the Town class.
     * Compares town1 and town2, and verifies the correct ordering and equality.
     */
    @Test
    public void testCompareTo() {
        assertTrue(town1.compareTo(town2) < 0); // town1 should be less than town2 lexicographically
        assertTrue(town2.compareTo(town1) > 0); // town2 should be greater than town1 lexicographically
        assertEquals(0, town1.compareTo(town3)); // town1 should be equal to town3
    }

    /**
     * Tests the equals method of the Town class.
     * Checks whether town1 is equal to town3 (same name) and not equal to town2 (different name).
     */
    @Test
    public void testEquals() {
        assertTrue(town1.equals(town3)); // town1 should be equal to town3
        assertFalse(town1.equals(town2)); // town1 should not be equal to town2
    }

    /**
     * Tests the hashCode method of the Town class.
     * Ensures that town1 and town3 have the same hash code since they are equal.
     */
    @Test
    public void testHashCode() {
        assertEquals(town1.hashCode(), town3.hashCode()); // hashCode should be the same for equal towns
    }
}
