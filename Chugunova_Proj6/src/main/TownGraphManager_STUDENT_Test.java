//Sousanna Chugunova
//CMSC204
//Dr.Thai

package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TownGraphManager_STUDENT_Test {
    private TownGraphManager manager; // Instance of TownGraphManager for testing

    /**
     * Sets up the test environment before each test method is run.
     * Initializes a new TownGraphManager instance.
     */
    @BeforeEach
    public void setUp() {
        manager = new TownGraphManager();
    }

    /**
     * Tests the addition of towns to the manager.
     * Verifies that towns are added successfully and prevents duplicate additions.
     */
    @Test
    public void testAddTown() {
        assertTrue(manager.addTown("Town1"), "Town1 should be added successfully.");
        assertTrue(manager.addTown("Town2"), "Town2 should be added successfully.");
        assertTrue(manager.addTown("Town3"), "Town3 should be added successfully.");
        assertFalse(manager.addTown("Town1"), "Adding Town1 again should return false.");
    }

    /**
     * Tests the addition of roads between towns.
     * Verifies that roads are added successfully and prevents duplicate additions.
     */
    @Test
    public void testAddRoad() {
        manager.addTown("Town1");
        manager.addTown("Town2");
        assertTrue(manager.addRoad("Town1", "Town2", 10, "Road1"), "Road1 should be added successfully.");
        assertFalse(manager.addRoad("Town1", "Town2", 10, "Road1"), "Adding the same road again should return false.");
    }

    /**
     * Tests the retrieval of the path between two towns.
     * Verifies that the path contains the correct number of steps and is accurate.
     */
    @Test
    public void testGetPath() {
        // Add towns
        manager.addTown("Town1");
        manager.addTown("Town2");
        manager.addTown("Town3");
        manager.addTown("Town4");
        
        // Add roads
        manager.addRoad("Town1", "Town2", 10, "Road1");
        manager.addRoad("Town2", "Town3", 20, "Road2");
        manager.addRoad("Town3", "Town4", 5, "Road3");
        
        // Retrieve path from Town1 to Town4
        ArrayList<String> path = manager.getPath("Town1", "Town4");
        
        // Verify the path is not null
        assertNotNull(path, "The path should not be null.");
        
        // Verify the path contains the correct number of towns
        assertEquals(3, path.size(), "The path should contain 3 roads.");
        
        // Verify the towns are in the correct order
        assertEquals("Town1 via Road1 to Town2 10 mi", path.get(0).trim(), "The first step should be 'Town1 via Road1 to Town2 10 mi'.");
        assertEquals("Town2 via Road2 to Town3 20 mi", path.get(1).trim(), "The second step should be 'Town2 via Road2 to Town3 20 mi'.");
        assertEquals("Town3 via Road3 to Town4 5 mi", path.get(2).trim(), "The third step should be 'Town3 via Road3 to Town4 5 mi'.");
    }

    /**
     * Tests the retrieval of all towns in the manager.
     * Verifies that the correct number of towns is returned and their names are accurate.
     */
    @Test
    public void testAllTowns() {
        manager.addTown("Town1");
        manager.addTown("Town2");
        manager.addTown("Town3");
        ArrayList<String> towns = manager.allTowns();
        
        // Verify the number of towns
        assertEquals(3, towns.size(), "There should be exactly 3 towns.");
        
        // Verify the contents of the town list
        assertTrue(towns.contains("Town1"), "The list should contain 'Town1'.");
        assertTrue(towns.contains("Town2"), "The list should contain 'Town2'.");
        assertTrue(towns.contains("Town3"), "The list should contain 'Town3'.");
    }
    
    /**
     * Tests the retrieval of all roads in the manager.
     * Verifies that the correct number of roads is returned and their names are accurate.
     */
    @Test
    public void testAllRoads() {
        // Add towns
        manager.addTown("Town1");
        manager.addTown("Town2");
        manager.addTown("Town3");
        
        // Add roads
        manager.addRoad("Town1", "Town2", 10, "Road1");
        manager.addRoad("Town2", "Town3", 20, "Road2");
        
        // Retrieve all roads
        ArrayList<String> roads = manager.allRoads();
        
        // Verify the size and contents of the road list
        assertNotNull(roads, "Road list should not be null.");
        assertEquals(2, roads.size(), "There should be exactly two roads.");
        assertTrue(roads.contains("Road1"), "The list should contain 'Road1'.");
        assertTrue(roads.contains("Road2"), "The list should contain 'Road2'.");
    }

    /**
     * Tests the deletion of towns from the manager.
     * Verifies that towns are deleted successfully and cannot be deleted again.
     */
    @Test
    public void testDeleteTown() {
        manager.addTown("Town1");
        manager.addTown("Town2");
        assertTrue(manager.deleteTown("Town1"), "Town1 should be deleted successfully.");
        assertFalse(manager.containsTown("Town1"), "Town1 should not be present in the manager.");
        assertFalse(manager.deleteTown("Town1"), "Deleting Town1 again should return false.");
    }

    /**
     * Tests the deletion of road connections between towns.
     * Verifies that roads are deleted successfully and cannot be deleted again.
     */
    @Test
    public void testDeleteRoad() {
        manager.addTown("Town1");
        manager.addTown("Town2");
        manager.addRoad("Town1", "Town2", 10, "Road1");
        assertTrue(manager.deleteRoadConnection("Town1", "Town2", "Road1"), "Road1 should be deleted successfully.");
        assertFalse(manager.containsRoadConnection("Town1", "Town2"), "Road1 should not be present in the manager.");
        assertFalse(manager.deleteRoadConnection("Town1", "Town2", "Road1"), "Deleting Road1 again should return false.");
    }

    /**
     * Tests whether a town exists in the manager.
     * Verifies that the manager correctly identifies the presence or absence of a town.
     */
    @Test
    public void testContainsTown() {
        manager.addTown("Town1");
        assertTrue(manager.containsTown("Town1"), "Manager should contain 'Town1'.");
        assertFalse(manager.containsTown("Town2"), "Manager should not contain 'Town2' initially.");
    }

    /**
     * Tests whether a road connection exists between towns in the manager.
     * Verifies that the manager correctly identifies the presence of road connections.
     */
    @Test
    public void testContainsRoadConnection() {
        manager.addTown("Town1");
        manager.addTown("Town2");
        manager.addRoad("Town1", "Town2", 10, "Road1");
        assertTrue(manager.containsRoadConnection("Town1", "Town2"), "Manager should contain the road connection between Town1 and Town2.");
    }
}
