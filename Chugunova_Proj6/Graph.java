package main;

import java.util.*;

public class Graph implements GraphInterface<Town, Road> {
    private Map<Town, List<Edge>> adjacencyList;
    private Map<Town, Double> distances;
    private Map<Town, Town> previous;

    public Graph() {
        adjacencyList = new HashMap<>();
        distances = new HashMap<>();
        previous = new HashMap<>();
    }

    @Override
    public boolean addVertex(Town town) {
        if (town == null) throw new NullPointerException("Town cannot be null");
        if (adjacencyList.containsKey(town)) return false;
        adjacencyList.put(town, new ArrayList<>());
        return true;
    }

    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (sourceVertex == null || destinationVertex == null) 
            throw new NullPointerException("Vertices cannot be null");
        if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) 
            throw new IllegalArgumentException("Both vertices must be in the graph");

        Road road = new Road(sourceVertex, destinationVertex, weight, description);
        addEdge(road);
        return road;
    }

    public void addEdge(Road road) {
        Town source = road.getSource();
        Town destination = road.getDestination();
        int weight = road.getWeight();

        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());

        adjacencyList.get(source).add(new Edge(destination, weight, road.getDescription()));
        adjacencyList.get(destination).add(new Edge(source, weight, road.getDescription())); // Assuming undirected graph
    }

    @Override
    public Road getEdge(Town source, Town destination) {
        if (source == null || destination == null) return null;
        if (!adjacencyList.containsKey(source)) return null;

        for (Edge edge : adjacencyList.get(source)) {
            if (edge.getDestination().equals(destination)) {
                return new Road(source, destination, edge.getWeight(), edge.getRoadName());
            }
        }
        return null;
    }

    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        if (sourceVertex == null || destinationVertex == null) return false;
        if (!adjacencyList.containsKey(sourceVertex)) return false;

        for (Edge edge : adjacencyList.get(sourceVertex)) {
            if (edge.getDestination().equals(destinationVertex)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsVertex(Town v) {
        return v != null && adjacencyList.containsKey(v);
    }

    @Override
    public Set<Road> edgeSet() {
        Set<Road> roads = new HashSet<>();
        for (Town vertex : adjacencyList.keySet()) {
            for (Edge edge : adjacencyList.get(vertex)) {
                // Add each road only once
                if (vertex.hashCode() < edge.getDestination().hashCode()) {
                    roads.add(new Road(vertex, edge.getDestination(), edge.getWeight(), edge.getRoadName()));
                }
            }
        }
        return roads;
    }

    @Override
    public Set<Road> edgesOf(Town vertex) {
        if (vertex == null) throw new NullPointerException("Vertex cannot be null");
        if (!containsVertex(vertex)) throw new IllegalArgumentException("Vertex not found");

        Set<Road> roads = new HashSet<>();
        for (Edge edge : adjacencyList.get(vertex)) {
            roads.add(new Road(vertex, edge.getDestination(), edge.getWeight(), edge.getRoadName()));
        }
        return roads;
    }

    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (sourceVertex == null || destinationVertex == null) return null;
        if (!containsVertex(sourceVertex)) return null;

        List<Edge> edges = adjacencyList.get(sourceVertex);
        Edge toRemove = null;

        for (Edge edge : edges) {
            if (edge.getDestination().equals(destinationVertex) && edge.getWeight() == weight && edge.getRoadName().equals(description)) {
                toRemove = edge;
                break;
            }
        }

        if (toRemove != null) {
            edges.remove(toRemove);
            adjacencyList.get(destinationVertex).removeIf(edge -> edge.getDestination().equals(sourceVertex) &&
                    edge.getWeight() == weight && edge.getRoadName().equals(description));
            return new Road(sourceVertex, destinationVertex, weight, description);
        }
        return null;
    }

    @Override
    public boolean removeVertex(Town v) {
        if (v == null) return false;
        if (!containsVertex(v)) return false;

        adjacencyList.remove(v);
        for (List<Edge> edges : adjacencyList.values()) {
            edges.removeIf(edge -> edge.getDestination().equals(v));
        }
        return true;
    }

    @Override
    public Set<Town> vertexSet() {
        return adjacencyList.keySet();
    }

    @Override
    public List<Town> shortestPath(Town source, Town destination) {
        if (source == null || destination == null) throw new NullPointerException("Source and destination cannot be null");
        if (!containsVertex(source) || !containsVertex(destination)) throw new IllegalArgumentException("Vertices not found");

        dijkstraShortestPath(source);

        List<Town> path = new ArrayList<>();
        for (Town at = destination; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    @Override
    public void dijkstraShortestPath(Town source) {
        PriorityQueue<Town> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        distances.clear();
        previous.clear();

        for (Town vertex : adjacencyList.keySet()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
            previous.put(vertex, null);
        }
        distances.put(source, 0.0);
        queue.add(source);

        while (!queue.isEmpty()) {
            Town u = queue.poll();
            for (Edge edge : adjacencyList.get(u)) {
                Town v = edge.getDestination();
                double alt = distances.get(u) + edge.getWeight();
                if (alt < distances.get(v)) {
                    distances.put(v, alt);
                    previous.put(v, u);
                    queue.add(v);
                }
            }
        }
    }

    // Inner class for Edge representation
    public static class Edge {
        private final Town destination;
        private final int weight;
        private final String roadName;

        public Edge(Town destination, int weight, String roadName) {
            this.destination = destination;
            this.weight = weight;
            this.roadName = roadName;
        }

        public Town getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }

        public String getRoadName() {
            return roadName;
        }
    }
}
