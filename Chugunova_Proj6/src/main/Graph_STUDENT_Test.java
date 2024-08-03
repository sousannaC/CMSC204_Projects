//Sousanna Chugunova
//CMSC204
//Dr.Thai

package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class Graph_STUDENT_Test {
    private Graph graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph();
    }

    @Test
    public void testAddVertex() {
        // Test adding a vertex to the graph
        Town town = new Town("Town1");
        assertTrue(graph.addVertex(town)); // Ensure the vertex was added successfully
        assertTrue(graph.containsVertex(town)); // Ensure the vertex is now in the graph
    }

    @Test
    public void testAddEdge() {
        // Test adding an edge to the graph
        Town town1 = new Town("Town1");
        Town town2 = new Town("Town2");
        graph.addVertex(town1);
        graph.addVertex(town2);
        Road road = graph.addEdge(town1, town2, 10, "Road1");
        assertNotNull(road); // Ensure the edge was created
        assertTrue(graph.containsEdge(town1, town2)); // Ensure the edge exists in the graph
        assertTrue(graph.containsEdge(town2, town1)); // For undirected graphs, check the reverse connection as well
    }

    @Test
    public void testEdgeSet() {
        // Test retrieving the set of edges from the graph
        Town town1 = new Town("Town1");
        Town town2 = new Town("Town2");
        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addEdge(town1, town2, 10, "Road1");
        
        Set<Road> edges = graph.edgeSet();
        
        // Verify the number of edges and their details
        assertEquals(1, edges.size(), "There should be 1 edge in the graph.");
        
        // Check if the edge is present in the set
        assertTrue(edges.contains(new Road(town1, town2, 10, "Road1")), "Edge set should contain the added road.");
    }

    @Test
    public void testVertexSet() {
        // Test retrieving the set of vertices from the graph
        Town town1 = new Town("Town1");
        Town town2 = new Town("Town2");
        graph.addVertex(town1);
        graph.addVertex(town2);
        Set<Town> vertices = graph.vertexSet();
        assertEquals(2, vertices.size()); // Ensure the number of vertices is correct
        assertTrue(vertices.contains(town1)); // Ensure the vertex is in the set
        assertTrue(vertices.contains(town2)); // Ensure the vertex is in the set
    }
}
