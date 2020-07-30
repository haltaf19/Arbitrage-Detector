package algorithm;

import model.Edge;
import model.Graph;
import model.Vertex;

import java.util.Map;

public class EdgeRelaxer {

    public void relaxEdges(Graph graph, Map<Vertex, Double> distances, Map<Vertex, Vertex> predecessor) {
        for (int i = 0; i < distances.size() - 1; i++) {
            for (Edge e : graph.getEdges()) {
                if (distances.get(e.getEndVertex()) > distances.get(e.getStartVertex()) + e.getWeight()) {
                    distances.put(e.getEndVertex(), distances.get(e.getStartVertex()) + e.getWeight());
                    predecessor.put(e.getEndVertex(), e.getStartVertex());
                }
            }
        }
    }
}
