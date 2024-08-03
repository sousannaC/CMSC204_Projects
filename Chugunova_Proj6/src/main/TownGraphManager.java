// Sousanna Chugunova
// CMSC204
// Dr. Thai

package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TownGraphManager implements TownGraphManagerInterface {
    private Graph graph; // Instance of the graph to manage towns and roads

    /**
     * Constructs a TownGraphManager with an empty graph.
     */
    public TownGraphManager() {
        graph = new Graph();
    }

    /**
     * Adds a road between two towns.
     * 
     * @param town1      Name of the source town.
     * @param town2      Name of the destination town.
     * @param weight     Weight of the road.
     * @param roadName   Name of the road.
     * @return true if the road was added successfully, false if it already exists.
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town sourceTown = new Town(town1);
        Town destinationTown = new Town(town2);
        
        // Check if the road already exists
        if (graph.containsEdge(sourceTown, destinationTown)) {
            return false; // Road already exists
        }

        return graph.addEdge(sourceTown, destinationTown, weight, roadName) != null;
    }

    /**
     * Retrieves the description of a road between two towns.
     * 
     * @param town1      Name of the source town.
     * @param town2      Name of the destination town.
     * @return Description of the road, or null if no road exists.
     */
    @Override
    public String getRoad(String town1, String town2) {
        Town sourceTown = new Town(town1);
        Town destinationTown = new Town(town2);
        Road road = graph.getEdge(sourceTown, destinationTown);
        return road != null ? road.getDescription() : null;
    }

    /**
     * Adds a new town to the graph.
     * 
     * @param v Name of the town.
     * @return true if the town was added successfully, false if the name is invalid or the town already exists.
     */
    @Override
    public boolean addTown(String v) {
        if (v == null || v.isEmpty()) {
            System.err.println("Town name cannot be null or empty.");
            return false;
        }
        Town town = new Town(v);
        return graph.addVertex(town);
    }

    /**
     * Retrieves a town by its name.
     * 
     * @param name Name of the town.
     * @return The Town object if found, otherwise null.
     */
    @Override
    public Town getTown(String name) {
        return graph.vertexSet().stream()
                .filter(town -> town.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if a town exists in the graph.
     * 
     * @param v Name of the town.
     * @return true if the town exists, false otherwise.
     */
    @Override
    public boolean containsTown(String v) {
        return graph.containsVertex(new Town(v));
    }

    /**
     * Checks if a road connection exists between two towns.
     * 
     * @param town1 Name of the source town.
     * @param town2 Name of the destination town.
     * @return true if the road connection exists, false otherwise.
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return graph.containsEdge(new Town(town1), new Town(town2));
    }

    /**
     * Retrieves a list of all roads in the graph, sorted by description.
     * 
     * @return List of road descriptions.
     */
    @Override
    public ArrayList<String> allRoads() {
        return graph.edgeSet().stream()
                .map(Road::getDescription)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Deletes a road connection between two towns.
     * 
     * @param town1    Name of the source town.
     * @param town2    Name of the destination town.
     * @param roadName Name of the road.
     * @return true if the road was deleted successfully, false otherwise.
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String roadName) {
        Town sourceTown = new Town(town1);
        Town destinationTown = new Town(town2);
        Road roadToRemove = graph.getEdge(sourceTown, destinationTown);
        if (roadToRemove != null && roadToRemove.getDescription().equals(roadName)) {
            return graph.removeEdge(sourceTown, destinationTown, roadToRemove.getWeight(), roadName) != null;
        }
        return false;
    }

    /**
     * Deletes a town from the graph.
     * 
     * @param v Name of the town to be deleted.
     * @return true if the town was deleted successfully, false otherwise.
     */
    @Override
    public boolean deleteTown(String v) {
        Town town = new Town(v);
        if (!graph.containsVertex(town)) return false;

        // Remove all edges associated with the town
        graph.edgesOf(town).forEach(road -> 
            graph.removeEdge(town, road.getDestination(), road.getWeight(), road.getName())
        );

        return graph.removeVertex(town);
    }

    /**
     * Retrieves a list of all towns in the graph, sorted by name.
     * 
     * @return List of town names.
     */
    public ArrayList<String> allTowns() {
        return graph.vertexSet().stream()
                .map(Town::getName)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retrieves the path between two towns using the shortest path algorithm.
     * 
     * @param town1 Name of the source town.
     * @param town2 Name of the destination town.
     * @return List of road descriptions representing the path from town1 to town2.
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town sourceTown = new Town(town1);
        Town destinationTown = new Town(town2);
        List<String> pathDescriptions = graph.shortestPath(sourceTown, destinationTown);
        return pathDescriptions != null 
            ? new ArrayList<>(pathDescriptions) 
            : new ArrayList<>();
    }

    /**
     * Retrieves the shortest path between two towns. 
     * This method is marked as unused and may be removed if not needed.
     * 
     * @param source      Name of the source town.
     * @param destination Name of the destination town.
     * @return List of road descriptions or a message if no path is found.
     */
    @SuppressWarnings("unused")
    public List<String> shortestPath(String source, String destination) {
        Town sourceTown = new Town(source);
        Town destinationTown = new Town(destination);
        ArrayList<String> path = getPath(source, destination);
        return !path.isEmpty() 
            ? path 
            : Collections.singletonList("No path found");
    }

    /**
     * Reads and parses graph data from a file.
     * 
     * @param filename Path to the file containing graph data.
     * @throws IOException If an error occurs while reading the file.
     */
    public void readGraphFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filename);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                parseLine(line);
            }
        }
    }

    /**
     * Parses a line of graph data and updates the graph.
     * 
     * @param line A line of data containing town and road information.
     */
    private void parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            System.err.println("Skipping invalid line (wrong format): " + line);
            return;
        }

        String source = parts[0].trim();
        String destination = parts[1].trim();
        int distance;
        String roadName = parts[3].trim();

        try {
            distance = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            System.err.println("Skipping line with invalid distance: " + parts[2]);
            return;
        }

        addTown(source);
        addTown(destination);
        addRoad(source, destination, distance, roadName);
    }

    /**
     * Populates the graph from a file with town and road data.
     * 
     * @param selectedFile File containing town and road information.
     */
    public void populateTownGraph(File selectedFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by semicolons
                String[] parts = line.split(";");
                if (parts.length < 2) {
                    System.err.println("Skipping invalid line (less than two parts): " + line);
                    continue;
                }

                // Process the first part, which is expected to be in the format "roadName,distance"
                String[] roadDetails = parts[0].split(",");
                if (roadDetails.length != 2) {
                    System.err.println("Skipping invalid road details format: " + parts[0]);
                    continue;
                }

                String roadName = roadDetails[0].trim();
                int distance;
                try {
                    distance = Integer.parseInt(roadDetails[1].trim());
                } catch (NumberFormatException e) {
                    System.err.println("Invalid distance format: " + roadDetails[1]);
                    continue;
                }

                // Process the remaining parts
                String town1Name = parts[1].trim();
                String town2Name = parts[2].trim();

                // Add towns and road
                addTown(town1Name);
                addTown(town2Name);
                if (getTown(town1Name) != null && getTown(town2Name) != null) {
                    addRoad(town1Name, town2Name, distance, roadName);
                } else {
                    System.err.println("Town(s) not found for road: " + roadName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}