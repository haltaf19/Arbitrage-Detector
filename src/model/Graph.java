package model;

import java.util.*;

public class Graph {

    private final Map<Vertex, List<Edge>> adjacentVertexMap;

    public Graph() {
        adjacentVertexMap = new HashMap<>();
    }

    public void addEdge(Vertex start, Vertex end, double weight) {
        Edge edge = new Edge(start, end, modifyEdgeWeight(weight));
        adjacentVertexMap.putIfAbsent(start, new LinkedList<>());
        adjacentVertexMap.get(start).add(edge);
    }

    public Map<Vertex, List<Edge>> getAdjacentVertexMap() {
        return adjacentVertexMap;
    }

    public List<Vertex> getVertexList() {
        List<Vertex> vertices = new ArrayList<>();
        for (Map.Entry<Vertex, List<Edge>> entry : adjacentVertexMap.entrySet()) {
            vertices.add(entry.getKey());
        }
        return vertices;
    }

    public List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();
        for (Map.Entry<Vertex, List<Edge>> entry : adjacentVertexMap.entrySet()) {
            List<Edge> edgeList = entry.getValue();
            edges.addAll(edgeList);
        }
        return edges;
    }

    private double modifyEdgeWeight(double weight) {
        return Math.log(weight) * -1;
    }
}
