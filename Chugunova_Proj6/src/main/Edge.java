//Sousanna Chugunova
//CMSC204
//Dr.Thai

package main;

import java.util.Objects;

public class Edge {
    // Destination town of the edge
    private final Town destination;
    
    // Weight or distance of the edge
    private final int weight;
    
    // Name of the road for this edge
    private final String roadName;

    /**
     * Creates an Edge with a destination, weight, and road name.
     *
     * @param destination The town where the edge points.
     * @param weight The distance or weight of the edge.
     * @param roadName The name of the road.
     */
    public Edge(Town destination, int weight, String roadName) {
        this.destination = destination;
        this.weight = weight;
        this.roadName = roadName;
    }

    /**
     * Gets the destination town.
     *
     * @return The destination town.
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Gets the weight of the edge.
     *
     * @return The weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the name of the road.
     *
     * @return The road name.
     */
    public String getRoadName() {
        return roadName;
    }

    /**
     * Checks if this edge is equal to another edge.
     *
     * @param obj The other edge to compare.
     * @return True if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return weight == edge.weight &&
               Objects.equals(destination, edge.destination) &&
               Objects.equals(roadName, edge.roadName);
    }

    /**
     * Returns the hash code of this edge.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(destination, weight, roadName);
    }
}

