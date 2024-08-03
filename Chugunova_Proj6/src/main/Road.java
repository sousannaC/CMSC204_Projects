//Sousanna Chugunova
//CMSC204
//Dr.Thai

package main;

import java.util.Objects;

public class Road {
    private final Town source; // The starting town of the road
    private final Town destination; // The ending town of the road
    private final int weight; // The distance or weight of the road
    private final String description; // A description of the road
    private final String name; // The name of the road

    /**
     * Constructs a new Road instance with the specified parameters.
     *
     * @param source The starting town of the road.
     * @param destination The ending town of the road.
     * @param weight The distance or weight of the road.
     * @param name The name of the road.
     * @throws NullPointerException if source or destination is null.
     */
    public Road(Town source, Town destination, int weight, String name) {
        if (source == null || destination == null) {
            throw new NullPointerException("Source and destination cannot be null");
        }
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.name = name;
        this.description = name; // Initialize description with the road's name
    }

    /**
     * Returns the starting town of the road.
     *
     * @return The source town.
     */
    public Town getSource() {
        return source;
    }

    /**
     * Returns the ending town of the road.
     *
     * @return The destination town.
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Returns the distance or weight of the road.
     *
     * @return The weight of the road.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Returns a description of the road.
     *
     * @return The description of the road.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the name of the road.
     *
     * @return The name of the road.
     */
    public String getName() {
        return name;
    }

    /**
     * Compares this road to another object for equality.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return weight == road.weight &&
                Objects.equals(source, road.source) &&
                Objects.equals(destination, road.destination) &&
                Objects.equals(name, road.name);
    }

    /**
     * Returns the hash code value for this road.
     *
     * @return The hash code value for this road.
     */
    @Override
    public int hashCode() {
        return Objects.hash(source, destination, weight, name);
    }

    /**
     * Returns a string representation of the road.
     *
     * @return A string representation of the road in the format "source to destination (weight): name".
     */
    @Override
    public String toString() {
        return source + " to " + destination + " (" + weight + "): " + name;
    }

    /**
     * Returns the distance of the road.
     * 
     * @return The distance of the road, which is the same as the weight.
     */
    public Integer getDistance() {
        // Return the weight of the road, which represents the distance.
        return getWeight();
    }
}
