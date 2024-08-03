
package main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Graph_GFA_Test {
    private GraphInterface<Town, Road> graph;
    private Town[] town;

    @Before
    public void setUp() throws Exception {
        // Initialize the graph and towns before each test
        graph = new Graph();
        town = new Town[3];

        for (int i = 0; i < 3; i++) {
            town[i] = new Town("Town_" + i);
            graph.addVertex(town[i]);
        }

        // Add an edge between Town_0 and Town_1
        graph.addEdge(town[0], town[1], 2, "Road_1");
    }

    @After
    public void tearDown() throws Exception {
        // Cleanup after each test
        graph = null;
    }

    @Test
    public void testGetEdge() {
        // Test that the edge between Town_1 and Town_0 with weight 2 and name "Road_1" is correctly retrieved
        Road expectedRoad = new Road(town[1], town[0], 2, "Road_1");
        assertEquals(expectedRoad, graph.getEdge(town[1], town[0]));
    }
}
