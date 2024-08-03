//Sousanna Chugunova
//CMSC204
//Dr.Thai

package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    private Graph graph; // The graph instance to be tested
    private Town[] town; // Array of towns for use in tests

    @BeforeEach
    void setUp() {
        // Initialize the graph and create towns
        graph = new Graph();
        town = new Town[12];

        for (int i = 1; i < 12; i++) {
            town[i] = new Town("Town_" + i);
            graph.addVertex(town[i]);
        }

        // Add edges between towns
        graph.addEdge(town[1], town[2], 2, "Road_1");
        graph.addEdge(town[1], town[3], 4, "Road_2");
        graph.addEdge(town[1], town[5], 6, "Road_3");
        graph.addEdge(town[3], town[7], 1, "Road_4");
        graph.addEdge(town[3], town[8], 2, "Road_5");
        graph.addEdge(town[4], town[8], 3, "Road_6");
        graph.addEdge(town[6], town[9], 3, "Road_7");
        graph.addEdge(town[9], town[10], 4, "Road_8");
        graph.addEdge(town[8], town[10], 2, "Road_9");
        graph.addEdge(town[5], town[10], 5, "Road_10");
        graph.addEdge(town[10], town[11], 3, "Road_11");
        graph.addEdge(town[2], town[11], 6, "Road_12");
    }

    @Test
    void testAddVertex() {
        // Test adding a new vertex
        Town newTown = new Town("Town_12");
        assertFalse(graph.containsVertex(newTown)); // Ensure town is not in the graph
        graph.addVertex(newTown);
        assertTrue(graph.containsVertex(newTown)); // Ensure town has been added
    }

    @Test
    void testAddEdge() {
        // Test adding a new edge
        Town town1 = new Town("Town_1");
        Town town2 = new Town("Town_2");
        graph.addVertex(town1);
        graph.addVertex(town2);

        Road road = graph.addEdge(town1, town2, 10, "Road 1");
        assertNotNull(road); // Ensure the road was created
        assertTrue(graph.containsEdge(town1, town2)); // Ensure edge is in the graph
    }

    @Test
    void testRemoveEdge() {
        // Test removing an existing edge
        assertTrue(graph.containsEdge(town[2], town[11])); // Ensure edge exists before removal
        graph.removeEdge(town[2], town[11], 6, "Road_12");
        assertFalse(graph.containsEdge(town[2], town[11])); // Ensure edge is removed
    }

    @Test
    void testRemoveVertex() {
        // Test removing an existing vertex
        Town town1 = new Town("Town_1");
        Town town2 = new Town("Town_2");
        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addEdge(town1, town2, 10, "Road 1");

        assertTrue(graph.removeVertex(town1)); // Ensure vertex removal was successful
        assertFalse(graph.containsVertex(town1)); // Ensure vertex is removed
        assertFalse(graph.containsEdge(town1, town2)); // Ensure associated edges are removed
    }

    @Test
    void testShortestPathSameTown() {
        // Test shortest path where source and destination are the same
        List<String> path = graph.shortestPath(town[1], town[1]);

        assertNotNull(path, "Path should not be null.");
        assertTrue(path.isEmpty(), "Path should be empty when source and destination are the same.");
    }

    @Test
    void testEdgeSet() {
        // Test retrieval of all edges in the graph
        Set<Road> roads = graph.edgeSet();
        ArrayList<String> roadArrayList = new ArrayList<>();
        for (Road road : roads) {
            roadArrayList.add(road.getName());
        }
        Collections.sort(roadArrayList); // Sort to compare expected order
        // Verify edges are sorted and match expected names
        assertEquals("Road_1", roadArrayList.get(0));
        assertEquals("Road_10", roadArrayList.get(1));
        assertEquals("Road_11", roadArrayList.get(2));
        assertEquals("Road_12", roadArrayList.get(3));
        assertEquals("Road_2", roadArrayList.get(4));
        assertEquals("Road_3", roadArrayList.get(5));
        assertEquals("Road_4", roadArrayList.get(6));
        assertEquals("Road_5", roadArrayList.get(7));
        assertEquals("Road_6", roadArrayList.get(8));
        assertEquals("Road_7", roadArrayList.get(9));
        assertEquals("Road_8", roadArrayList.get(10));
        assertEquals("Road_9", roadArrayList.get(11));
    }

    @Test
    void testEdgesOf() {
        // Test retrieval of edges for a specific vertex
        Set<Road> roads = graph.edgesOf(town[1]);
        ArrayList<String> roadArrayList = new ArrayList<>();
        for (Road road : roads) {
            roadArrayList.add(road.getName());
        }
        Collections.sort(roadArrayList); // Sort to compare expected order
        // Verify edges are sorted and match expected names
        assertEquals("Road_1", roadArrayList.get(0));
        assertEquals("Road_2", roadArrayList.get(1));
        assertEquals("Road_3", roadArrayList.get(2));
    }

    @Test
    void testGetEdge() {
        // Test retrieval of specific edges
        assertEquals(new Road(town[2], town[11], 6, "Road_12"), graph.getEdge(town[2], town[11]));
        assertEquals(new Road(town[3], town[7], 1, "Road_4"), graph.getEdge(town[3], town[7]));
    }

    @Test
    void testContainsEdge() {
        // Test checking for existence of edges
        assertTrue(graph.containsEdge(town[2], town[11]));
        assertFalse(graph.containsEdge(town[3], town[5]));
    }

    @Test
    void testContainsVertex() {
        // Test checking for existence of vertices
        assertTrue(graph.containsVertex(new Town("Town_2")));
        assertFalse(graph.containsVertex(new Town("Town_12")));
    }

    @Test
    void testVertexSet() {
        // Test retrieval of all vertices in the graph
        Set<Town> towns = graph.vertexSet();
        assertTrue(towns.contains(town[1]));
        assertTrue(towns.contains(town[10]));
        assertTrue(towns.contains(town[11]));
        assertTrue(towns.contains(town[2]));
        assertTrue(towns.contains(town[3]));
    }

    @Test
    void testTown_1ToTown_11() {
        // Test shortest path from Town_1 to Town_11
        String[] expectedPath = {
            "Town_1 via Road_1 to Town_2 2 mi",
            "Town_2 via Road_12 to Town_11 6 mi"
        };
        testSpecificShortestPath("Town_1", "Town_11", expectedPath);
    }

    @Test
    void testTown_1ToTown_10() {
        // Test shortest path from Town_1 to Town_10
        String[] expectedPath = {
            "Town_1 via Road_2 to Town_3 4 mi",
            "Town_3 via Road_5 to Town_8 2 mi",
            "Town_8 via Road_9 to Town_10 2 mi"
        };
        testSpecificShortestPath("Town_1", "Town_10", expectedPath);
    }

    @Test
    void testTown_4ToTown_11() {
        // Test shortest path from Town_4 to Town_11
        String[] expectedPath = {
            "Town_4 via Road_6 to Town_8 3 mi",
            "Town_8 via Road_9 to Town_10 2 mi",
            "Town_10 via Road_11 to Town_11 3 mi"
        };
        testSpecificShortestPath("Town_4", "Town_11", expectedPath);
    }

    // Helper method to test shortest path for specific start and end towns
    private void testSpecificShortestPath(String beginTown, String endTown, String[] expectedPath) {
        Town beginIndex = null, endIndex = null;
        Set<Town> towns = graph.vertexSet();
        for (Town town : towns) {
            if (town.getName().equals(beginTown)) beginIndex = town;
            if (town.getName().equals(endTown)) endIndex = town;
        }
        assertNotNull(beginIndex, "Starting town should not be null.");
        assertNotNull(endIndex, "Ending town should not be null.");

        List<String> path = graph.shortestPath(beginIndex, endIndex);
        assertNotNull(path, "Path should not be null.");
        assertEquals(expectedPath.length, path.size(), "Path length should match expected length.");
        for (int i = 0; i < expectedPath.length; i++) {
            assertEquals(expectedPath[i], path.get(i), "Path step " + i + " should match expected.");
        }
    }
}
