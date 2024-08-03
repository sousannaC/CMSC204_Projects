package main;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TownGraphManagerTest {
    private Graph graph;
    private TownGraphManager townGraphManager; // Instance of TownGraphManager to be tested
    private String[] town;

    /**
     * Initializes the test environment by setting up a graph and a TownGraphManager instance,
     * and adding a set of towns and roads for testing.
     */
    @BeforeEach
    void setUp() {
        graph = new Graph();
        townGraphManager = new TownGraphManager(); // Create a new TownGraphManager instance
        town = new String[12];

        // Add towns to the TownGraphManager
        for (int i = 1; i < 12; i++) {
            town[i] = "Town_" + i;
            townGraphManager.addTown(town[i]);
        }

        // Add roads between towns
        townGraphManager.addRoad(town[1], town[2], 2, "Road_1");
        townGraphManager.addRoad(town[1], town[3], 4, "Road_2");
        townGraphManager.addRoad(town[1], town[5], 6, "Road_3");
        townGraphManager.addRoad(town[3], town[7], 1, "Road_4");
        townGraphManager.addRoad(town[3], town[8], 2, "Road_5");
        townGraphManager.addRoad(town[4], town[8], 3, "Road_6");
        townGraphManager.addRoad(town[6], town[9], 3, "Road_7");
        townGraphManager.addRoad(town[9], town[10], 4, "Road_8");
        townGraphManager.addRoad(town[8], town[10], 2, "Road_9");
        townGraphManager.addRoad(town[5], town[10], 5, "Road_10");
        townGraphManager.addRoad(town[10], town[11], 3, "Road_11");
        townGraphManager.addRoad(town[2], town[11], 6, "Road_12");
    }

    /**
     * Cleans up the test environment by resetting graph and TownGraphManager instances.
     */
    @AfterEach
    void tearDown() {
        graph = null;
        townGraphManager = null;
    }

    // Graph tests
    /**
     * Tests adding vertices (towns) to the graph.
     */
    @Test
    void testAddVertex() {
        Town town1 = new Town("Town_1");
        Town town2 = new Town("Town_2");

        assertTrue(graph.addVertex(town1), "Vertex should be added successfully.");
        assertFalse(graph.addVertex(town1), "Adding an existing vertex should return false.");
        assertTrue(graph.containsVertex(town1), "Graph should contain the vertex.");
        assertFalse(graph.containsVertex(town2), "Graph should not contain a non-existent vertex.");
    }

    /**
     * Tests adding edges (roads) between vertices (towns) in the graph.
     */
    @Test
    void testAddEdge() {
        Town town1 = new Town("Town_1");
        Town town2 = new Town("Town_2");
        graph.addVertex(town1);
        graph.addVertex(town2);

        Road road = graph.addEdge(town1, town2, 5, "Road_1");
        assertNotNull(road, "Edge should be added successfully.");
        assertTrue(graph.containsEdge(town1, town2), "Graph should contain the edge.");
        assertEquals(road, graph.getEdge(town1, town2), "Edge retrieved should match the added edge.");
    }

    /**
     * Tests finding the shortest path between two towns when a path exists.
     */
    @Test
    void testShortestPathWithPath() {
        Town town1 = new Town("Town_1");
        Town town2 = new Town("Town_2");
        Town town3 = new Town("Town_3");

        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addVertex(town3);

        graph.addEdge(town1, town2, 1, "Road_1");
        graph.addEdge(town2, town3, 1, "Road_2");

        List<String> path = graph.shortestPath(town1, town3);

        assertNotNull(path, "Path should not be null.");
        assertEquals(2, path.size(), "Path size should be 2.");
        assertEquals("Town_1 via Road_1 to Town_2 1 mi", path.get(0), "Path should start with Town_1.");
        assertEquals("Town_2 via Road_2 to Town_3 1 mi", path.get(1), "Path should end with Town_3.");
    }

    /**
     * Tests removing an edge (road) between two towns.
     */
    @Test
    void testRemoveEdge() {
        Town town1 = new Town("Town_1");
        Town town2 = new Town("Town_2");
        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addEdge(town1, town2, 5, "Road_1");

        Road removedRoad = graph.removeEdge(town1, town2, 5, "Road_1");

        assertNotNull(removedRoad, "Removed road should not be null.");
        assertFalse(graph.containsEdge(town1, town2), "Edge should be removed.");
        assertNull(graph.getEdge(town1, town2), "No edge should exist between town1 and town2.");
    }

    /**
     * Tests removing a vertex (town) from the graph and ensuring all related edges are removed.
     */
    @Test
    void testRemoveVertex() {
        Town town1 = new Town("Town_1");
        Town town2 = new Town("Town_2");
        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addEdge(town1, town2, 5, "Road_1");

        graph.removeVertex(town1);

        assertFalse(graph.containsVertex(town1), "Vertex should be removed.");
        assertFalse(graph.containsEdge(town1, town2), "Edge connected to removed vertex should also be removed.");
    }

    /**
     * Tests retrieving the set of vertices (towns) from the graph.
     */
    @Test
    void testVertexSet() {
        Town town1 = new Town("Town_1");
        Town town2 = new Town("Town_2");
        graph.addVertex(town1);
        graph.addVertex(town2);

        Set<Town> vertices = graph.vertexSet();

        assertTrue(vertices.contains(town1), "Vertex set should contain town1.");
        assertTrue(vertices.contains(town2), "Vertex set should contain town2.");
    }

    /**
     * Tests retrieving the set of edges (roads) from the graph.
     */
    @Test
    void testEdgeSet() {
        Town town1 = new Town("Town_1");
        Town town2 = new Town("Town_2");
        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addEdge(town1, town2, 5, "Road_1");

        Set<Road> edges = graph.edgeSet();

        assertTrue(edges.contains(new Road(town1, town2, 5, "Road_1")), "Edge set should contain the added road.");
    }

    // TownGraphManagerInterface tests
    /**
     * Tests adding roads to the TownGraphManager and checking the list of all roads.
     */
    @Test
    public void testAddRoad() {
        ArrayList<String> roads = townGraphManager.allRoads();
        assertEquals("Road_1", roads.get(0));
        assertEquals("Road_10", roads.get(1));
        assertEquals("Road_11", roads.get(2));
        assertEquals("Road_12", roads.get(3));
        townGraphManager.addRoad(town[4], town[11], 1, "Road_13");
        roads = townGraphManager.allRoads();
        assertEquals("Road_1", roads.get(0));
        assertEquals("Road_10", roads.get(1));
        assertEquals("Road_11", roads.get(2));
        assertEquals("Road_12", roads.get(3));
        assertEquals("Road_13", roads.get(4));
    }

    /**
     * Tests retrieving the description of a road between two towns.
     */
    @Test
    public void testGetRoad() {
        assertEquals("Road_12", townGraphManager.getRoad(town[2], town[11]));
        assertEquals("Road_4", townGraphManager.getRoad(town[3], town[7]));
    }

    /**
     * Tests adding a town to the TownGraphManager and checking if it exists.
     */
    @Test
    public void testAddTown() {
        assertFalse(townGraphManager.containsTown("Town_12"));
        townGraphManager.addTown("Town_12");
        assertTrue(townGraphManager.containsTown("Town_12"));
    }

    /**
     * Tests the scenario where a path is requested between towns that are not connected.
     */
    @Test
    public void testDisjointGraph() {
        assertFalse(townGraphManager.containsTown("Town_12"));
        townGraphManager.addTown("Town_12");
        ArrayList<String> path = townGraphManager.getPath(town[1], "Town_12");
        assertTrue(path.isEmpty(), "Path should be empty for disjoint towns.");
    }

    /**
     * Tests checking the existence of a town in the TownGraphManager.
     */
    @Test
    public void testContainsTown() {
        assertTrue(townGraphManager.containsTown("Town_2"));
        assertFalse(townGraphManager.containsTown("Town_12"));
    }

    /**
     * Tests checking the existence of a road connection between two towns.
     */
    @Test
    public void testContainsRoadConnection() {
        assertTrue(townGraphManager.containsRoadConnection(town[2], town[11]));
        assertFalse(townGraphManager.containsRoadConnection(town[3], town[5]));
    }

    /**
     * Tests retrieving all roads from the TownGraphManager.
     */
    @Test
    public void testAllRoads() {
        ArrayList<String> roads = townGraphManager.allRoads();
        assertTrue(roads.contains("Road_1"));
        assertTrue(roads.contains("Road_10"));
        assertTrue(roads.contains("Road_11"));
        assertTrue(roads.contains("Road_8"));
        assertTrue(roads.contains("Road_9"));
    }

    /**
     * Tests deleting a road connection between two towns.
     */
    @Test
    public void testDeleteRoadConnection() {
        assertTrue(townGraphManager.containsRoadConnection(town[2], town[11]));
        townGraphManager.deleteRoadConnection(town[2], town[11], "Road_12");
        assertFalse(townGraphManager.containsRoadConnection(town[2], town[11]));
    }

    /**
     * Tests deleting a town from the TownGraphManager and ensuring its absence.
     */
    @Test
    public void testDeleteTown() {
        assertTrue(townGraphManager.containsTown("Town_2"));
        townGraphManager.deleteTown("Town_2");
        assertFalse(townGraphManager.containsTown("Town_2"));
    }

    /**
     * Tests retrieving all towns from the TownGraphManager.
     */
    @Test
    public void testAllTowns() {
        ArrayList<String> towns = townGraphManager.allTowns();
        assertTrue(towns.contains("Town_1"));
        assertTrue(towns.contains("Town_2"));
        assertTrue(towns.contains("Town_10"));
        assertTrue(towns.contains("Town_11"));
        assertTrue(towns.contains("Town_8"));
    }

    /**
     * Tests retrieving the path between two towns and checks path content.
     */
    @Test
    public void testGetPath() {
        ArrayList<String> path = townGraphManager.getPath(town[1], town[11]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("Town_1 via Road_1 to Town_2 2 mi", path.get(0).trim());
        assertEquals("Town_2 via Road_12 to Town_11 6 mi", path.get(1).trim());
    }

    /**
     * Tests retrieving a path between two towns with multiple segments.
     */
    @Test
    public void testGetPathA() {
        ArrayList<String> path = townGraphManager.getPath(town[1], town[10]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("Town_1 via Road_2 to Town_3 4 mi", path.get(0).trim());
        assertEquals("Town_3 via Road_5 to Town_8 2 mi", path.get(1).trim());
        assertEquals("Town_8 via Road_9 to Town_10 2 mi", path.get(2).trim());
    }

    /**
     * Tests retrieving a path with multiple segments including intermediate towns.
     */
    @Test
    public void testGetPathB() {
        ArrayList<String> path = townGraphManager.getPath(town[1], town[6]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("Town_1 via Road_2 to Town_3 4 mi", path.get(0).trim());
        assertEquals("Town_3 via Road_5 to Town_8 2 mi", path.get(1).trim());
        assertEquals("Town_8 via Road_9 to Town_10 2 mi", path.get(2).trim());
        assertEquals("Town_10 via Road_8 to Town_9 4 mi", path.get(3).trim());
        assertEquals("Town_9 via Road_7 to Town_6 3 mi", path.get(4).trim());
    }

    // Additional Tests
    /**
     * Tests updating the distance of a road and checking if the update is reflected.
     */
    @Test
    public void testUpdateRoadDistance() {
        townGraphManager.addRoad(town[1], town[4], 10, "Road_13");
        townGraphManager.deleteRoadConnection(town[1], town[4], "Road_13");
        townGraphManager.addRoad(town[1], town[4], 15, "Road_13");
        ArrayList<String> roads = townGraphManager.allRoads();
        assertTrue(roads.contains("Road_13"));
    }

    /**
     * Tests finding a path when multiple roads have the same distance.
     */
    @Test
    public void testPathWithMultipleSameDistanceRoads() {
        townGraphManager.addRoad(town[3], town[4], 2, "Road_14");
        ArrayList<String> path = townGraphManager.getPath(town[1], town[4]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("Town_1 via Road_2 to Town_3 4 mi", path.get(0).trim());
        assertEquals("Town_3 via Road_14 to Town_4 2 mi", path.get(1).trim());
    }
}
