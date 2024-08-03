//Sousanna Chugunova
//CMSC204
//Dr.Thai

package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Town implements Comparable<Town> {
    private String name; // Name of the town
    private List<Town> adjacentTowns; // List of towns adjacent to this town

    /**
     * Constructs a Town with the specified name.
     * Initializes the list of adjacent towns.
     *
     * @param name the name of the town
     */
    public Town(String name) {
        this.name = name;
        this.adjacentTowns = new ArrayList<>();
    }

    /**
     * Returns the name of the town.
     *
     * @return the name of the town
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the town.
     *
     * @param name the new name of the town
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the list of adjacent towns.
     *
     * @return the list of adjacent towns
     */
    public List<Town> getAdjacentTowns() {
        return adjacentTowns;
    }

    /**
     * Adds a town to the list of adjacent towns.
     *
     * @param town the town to be added
     */
    public void addAdjacentTown(Town town) {
        adjacentTowns.add(town);
    }

    /**
     * Compares this town with another town based on their names.
     *
     * @param other the other town to be compared
     * @return a negative integer, zero, or a positive integer as this town
     *         is less than, equal to, or greater than the specified town
     */
    @Override
    public int compareTo(Town other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Compares this town with the specified object for equality.
     * Two towns are considered equal if their names are equal.
     *
     * @param o the object to be compared
     * @return true if this town is equal to the specified object; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return name.equals(town.name);
    }

    /**
     * Returns the hash code for this town.
     * The hash code is based on the town's name.
     *
     * @return the hash code of this town
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns a string representation of the town.
     *
     * @return a string representation of the town
     */
    @Override
    public String toString() {
        return "Town{name='" + name + '\'' + '}';
    }

    /**
     * Trims leading and trailing whitespace from the town's name.
     */
    public void trim() {
        this.name = this.name.trim();
    }
}
