//Sousanna Chugunova
//CMSC204
//Dr.Thai

package main;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
    private Road road1, road2;

    @Before
    public void setUp() {
        Town town1 = new Town("Town1");
        Town town2 = new Town("Town2");
        road1 = new Road(town1, town2, 10, "Road1");
        road2 = new Road(town1, town2, 15, "Road2");
    }

    @Test
    public void testGetSource() {
        assertEquals("Town1", road1.getSource().getName());
    }

    @Test
    public void testGetDestination() {
        assertEquals("Town2", road1.getDestination().getName());
    }

    @Test
    public void testGetWeight() {  // Updated from getDistance to getWeight
        assertEquals(10, road1.getWeight());
    }

    @Test
    public void testGetDescription() {  // Updated from getName to getDescription
        assertEquals("Road1", road1.getDescription());
    }

    @Test
    public void testComparison() {
        assertTrue(road1.getWeight() < road2.getWeight());
    }
}
