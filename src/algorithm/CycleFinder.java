package algorithm;

import model.Edge;
import model.Graph;
import model.Vertex;

import java.util.*;

public class CycleFinder {

    private final Graph graph;
    private final HashMap<Vertex, Double> distances;
    private final HashMap<Vertex, Vertex> predecessor;

    public CycleFinder(Graph graph) {
        this.graph = graph;
        distances = new HashMap<>();
        predecessor = new HashMap<>();
    }

    public List<List<Vertex>> findCycles() {
        List<List<Vertex>> cycles = new ArrayList<>();
        for (Vertex v : graph.getVertexList()) {
            List<List<Vertex>> retVal = findCycleAtVertex(v);
            cycles.addAll(retVal);
        }
        removeDuplicatesInCycle(cycles);
        return cycles;
    }

    private List<List<Vertex>> findCycleAtVertex(Vertex sourceVertex) {
        for (Vertex v : graph.getVertexList()) {
            distances.put(v, Double.POSITIVE_INFINITY);
            predecessor.put(v, v);
        }

        distances.put(sourceVertex, 0.0);
        relaxEdges();
        return findNegativeWeightCycle();
    }

    private void relaxEdges() {
        int numOfNodes = distances.size();
        for (int i = 0; i < numOfNodes - 1; i++) {
            for (Edge e : graph.getAllEdges()) {
                if (distances.get(e.getEndVertex()) > distances.get(e.getStartVertex()) + e.getWeight()) {
                    distances.put(e.getEndVertex(), distances.get(e.getStartVertex()) + e.getWeight());
                    predecessor.put(e.getEndVertex(), e.getStartVertex());
                }
            }
        }
    }

    private List<List<Vertex>> findNegativeWeightCycle() {
        HashMap<Vertex, Boolean> seen = new HashMap<>();
        List<List<Vertex>> cycles = new ArrayList<>();
        for (Edge e : graph.getAllEdges()) {
            if (seen.containsKey(e.getEndVertex())) continue;

            if (distances.get(e.getEndVertex()) > distances.get(e.getStartVertex()) + e.getWeight()) {
                ArrayList<Vertex> newCycle = new ArrayList<>();
                Vertex vertex = e.getEndVertex();
                do {
                    seen.put(vertex, true);
                    newCycle.add(vertex);
                    vertex = predecessor.get(vertex);
                } while (vertex != e.getEndVertex() && !newCycle.contains(vertex));
                int index = newCycle.indexOf(vertex);
                newCycle.add(vertex);
                cycles.add(newCycle.subList(index, newCycle.size()));
            }
        }
        return cycles;
    }

    private void removeDuplicatesInCycle(List<List<Vertex>> cycles) {
        Set<List<Vertex>> seenCycles = new HashSet<>();
        for (List<Vertex> allCycle : cycles) {
            if (seenCycles.contains(allCycle)) {
                cycles.remove(allCycle);
            }
            seenCycles.add(allCycle);
        }
    }
}
