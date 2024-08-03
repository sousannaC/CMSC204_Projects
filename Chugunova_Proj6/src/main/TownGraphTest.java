package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TownGraphTest {
    private GraphInterface<Town, Road> graph; // Interface for the graph
    private Town[] towns; // Array to hold town instances

    // Set up the graph and towns before each test
    @BeforeEach
    public void setUp() {
        graph = (GraphInterface<Town, Road>) new Graph(); // Initialize the graph
        towns = new Town[12];

        // Create and add towns to the graph
        for (int i = 1; i < 12; i++) {
            towns[i] = new Town("Town_" + i);
            graph.addVertex(towns[i]);
        }

        // Add roads (edges) between towns
        graph.addEdge(towns[1], towns[2], 2, "Road_1");
        graph.addEdge(towns[1], towns[3], 4, "Road_2");
        graph.addEdge(towns[1], towns[5], 6, "Road_3");
        graph.addEdge(towns[3], towns[7], 1, "Road_4");
        graph.addEdge(towns[3], towns[8], 2, "Road_5");
        graph.addEdge(towns[4], towns[8], 3, "Road_6");
        graph.addEdge(towns[6], towns[9], 3, "Road_7");
        graph.addEdge(towns[9], towns[10], 4, "Road_8");
        graph.addEdge(towns[8], towns[10], 2, "Road_9");
        graph.addEdge(towns[5], towns[10], 5, "Road_10");
        graph.addEdge(towns[10], towns[11], 3, "Road_11");
        graph.addEdge(towns[2], towns[11], 6, "Road_12");
    }

    // Clean up after each test
    @AfterEach
    public void tearDown() {
        graph = null; // Clear the graph reference
    }

    // Test case to check the retrieval of an edge
    @Test
    public void testGetEdge() {
        // Check if the edges are correctly retrieved from the graph
        assertEquals(new Road(towns[2], towns[11], 6, "Road_12"), graph.getEdge(towns[2], towns[11]));
        assertEquals(new Road(towns[3], towns[7], 1, "Road_4"), graph.getEdge(towns[3], towns[7]));
    }

    // Test case to check if adding an edge works
    @Test
    public void testAddEdge() {
        // Ensure the edge does not exist before adding
        assertFalse(graph.containsEdge(towns[3], towns[5]));
        // Add a new edge and check if it is present
        graph.addEdge(towns[3], towns[5], 1, "Road_13");
        assertTrue(graph.containsEdge(towns[3], towns[5]));
    }

    // Test case to check if adding a vertex works
    @Test
    public void testAddVertex() {
        Town newTown = new Town("Town_12");
        // Ensure the vertex does not exist before adding
        assertFalse(graph.containsVertex(newTown));
        // Add a new vertex and check if it is present
        graph.addVertex(newTown);
        assertTrue(graph.containsVertex(newTown));
    }

    // Test case to check if an edge exists
    @Test
    public void testContainsEdge() {
        // Check for existence of edges in the graph
        assertTrue(graph.containsEdge(towns[2], towns[11]));
        assertFalse(graph.containsEdge(towns[3], towns[5]));
    }

    // Test case to check if a vertex exists
    @Test
    public void testContainsVertex() {
        // Check for existence of vertices in the graph
        assertTrue(graph.containsVertex(new Town("Town_2")));
        assertFalse(graph.containsVertex(new Town("Town_12")));
    }

    // Test case to check the retrieval of all edges
    @Test
    public void testEdgeSet() {
        Set<Road> roads = graph.edgeSet();
        ArrayList<String> roadArrayList = new ArrayList<>();
        // Collect and sort the names of all edges
        for (Road road : roads) {
            roadArrayList.add(road.getName());
        }
        Collections.sort(roadArrayList);
        // Verify that the edges are as expected
        assertEquals("Road_1", roadArrayList.get(0));
        assertEquals("Road_10", roadArrayList.get(1));
        assertEquals("Road_11", roadArrayList.get(2));
        assertEquals("Road_12", roadArrayList.get(3));
        assertEquals("Road_2", roadArrayList.get(4));
        assertEquals("Road_8", roadArrayList.get(10));
    }

    // Test case to check the retrieval of edges connected to a specific town
    @Test
    public void testEdgesOf() {
        Set<Road> roads = graph.edgesOf(towns[1]);
        ArrayList<String> roadArrayList = new ArrayList<>();
        // Collect and sort the names of all edges connected to the town
        for (Road road : roads) {
            roadArrayList.add(road.getName());
        }
        Collections.sort(roadArrayList);
        // Verify the edges are as expected
        assertEquals("Road_1", roadArrayList.get(0));
        assertEquals("Road_2", roadArrayList.get(1));
        assertEquals("Road_3", roadArrayList.get(2));
    }

    // Test case to check if removing an edge works
    @Test
    public void testRemoveEdge() {
        // Ensure the edge exists before removing
        assertTrue(graph.containsEdge(towns[2], towns[11]));
        // Remove the edge and verify it is no longer present
        graph.removeEdge(towns[2], towns[11], 6, "Road_12");
        assertFalse(graph.containsEdge(towns[2], towns[11]));
    }

    // Test case to check if removing a vertex works
    @Test
    public void testRemoveVertex() {
        // Ensure the vertex exists before removing
        assertTrue(graph.containsVertex(towns[2]));
        // Remove the vertex and verify it is no longer present
        graph.removeVertex(towns[2]);
        assertFalse(graph.containsVertex(towns[2]));
    }

    // Test case to check the retrieval of all vertices
    @Test
    public void testVertexSet() {
        Set<Town> vertices = graph.vertexSet();
        // Verify that the set of vertices includes the expected towns
        assertTrue(vertices.contains(towns[1]));
        assertTrue(vertices.contains(towns[10]));
        assertTrue(vertices.contains(towns[11]));
        assertTrue(vertices.contains(towns[2]));
        assertTrue(vertices.contains(towns[3]));
    }

    // Test case to check the shortest path between two towns
    @Test
    public void testTown_1ToTown_11() {
        Town start = getTownByName("Town_1");
        Town end = getTownByName("Town_11");
        ArrayList<String> path = graph.shortestPath(start, end);

        // Ensure the path is not null and has a valid length
        assertNotNull(path);
        assertTrue(path.size() > 0);
        // Verify the path matches the expected sequence
        assertEquals("Town_1 via Road_1 to Town_2 2 mi", path.get(0).trim());
        assertEquals("Town_2 via Road_12 to Town_11 6 mi", path.get(1).trim());
    }

    // Test case to check the shortest path between two different towns
    @Test
    public void testTown_1ToTown_10() {
        Town start = getTownByName("Town_1");
        Town end = getTownByName("Town_10");
        ArrayList<String> path = graph.shortestPath(start, end);

        // Ensure the path is not null and has a valid length
        assertNotNull(path);
        assertTrue(path.size() > 0);
        // Verify the path matches the expected sequence
        assertEquals("Town_1 via Road_2 to Town_3 4 mi", path.get(0).trim());
        assertEquals("Town_3 via Road_5 to Town_8 2 mi", path.get(1).trim());
        assertEquals("Town_8 via Road_9 to Town_10 2 mi", path.get(2).trim());
    }

    // Test case to check the shortest path from a town to another town
    @Test
    public void testTown_4ToTown_11() {
        Town start = getTownByName("Town_4");
        Town end = getTownByName("Town_11");
        ArrayList<String> path = graph.shortestPath(start, end);

        // Ensure the path is not null and has a valid length
        assertNotNull(path);
        assertTrue(path.size() > 0);
        // Verify the path matches the expected sequence
        assertEquals("Town_4 via Road_6 to Town_8 3 mi", path.get(0).trim());
        assertEquals("Town_8 via Road_9 to Town_10 2 mi", path.get(1).trim());
        assertEquals("Town_10 via Road_11 to Town_11 3 mi", path.get(2).trim());
    }

    // Helper method to get a Town object by its name
    private Town getTownByName(String name) {
        for (Town town : towns) {
            if (town != null && town.getName().equals(name)) {
                return town;
            }
        }
        // Fail the test if the town name is not found
        fail("Town name is not valid");
        return null;
    }
}
