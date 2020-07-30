package algorithm;

import com.google.inject.Inject;
import lombok.AllArgsConstructor;
import model.Edge;
import model.Graph;
import model.Vertex;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@AllArgsConstructor(onConstructor = @__({@Inject}))
public class CycleFinder {

    private final EdgeRelaxer edgeRelaxer;
    private final Map<Vertex, Double> distances = new HashMap<>();
    private final Map<Vertex, Vertex> predecessor = new HashMap<>();

    public List<List<Vertex>> findCycles(Graph graph) {
        List<List<Vertex>> cycles = new ArrayList<>();
        for (Vertex v : graph.getVertices()) {
            List<List<Vertex>> cycleAtVertex = findCyclesAtVertex(graph, v);
            cycles.addAll(cycleAtVertex);
        }

        return getUniqueCycles(cycles);
    }

    private List<List<Vertex>> findCyclesAtVertex(Graph graph, Vertex sourceVertex) {
        for (Vertex v : graph.getVertices()) {
            distances.put(v, Double.POSITIVE_INFINITY);
            predecessor.put(v, v);
        }

        distances.put(sourceVertex, 0.0);
        edgeRelaxer.relaxEdges(graph, distances, predecessor);
        return findNegativeWeightCycle(graph);
    }

    private List<List<Vertex>> findNegativeWeightCycle(Graph graph) {
        Set<Vertex> seenVertices = new HashSet<>();
        List<List<Vertex>> cycles = new ArrayList<>();
        for (Edge e : graph.getEdges()) {
            if (seenVertices.contains(e.getEndVertex())) continue;

            if (distances.get(e.getEndVertex()) > distances.get(e.getStartVertex()) + e.getWeight()) {
                ArrayList<Vertex> newCycle = new ArrayList<>();
                Vertex vertex = e.getEndVertex();

                do {
                    seenVertices.add(vertex);
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

    private List<List<Vertex>> getUniqueCycles(List<List<Vertex>> cycles) {
        Set<List<Vertex>> uniqueCycles = new HashSet<>(cycles);
        return new ArrayList<>(uniqueCycles);
    }
}
