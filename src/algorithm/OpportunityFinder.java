package algorithm;

import lombok.AllArgsConstructor;
import model.Edge;
import model.Graph;
import model.Vertex;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@AllArgsConstructor
public class OpportunityFinder {

    private static final DecimalFormat decimalFormat = new DecimalFormat("###.###");

    public Map<List<Vertex>, Double> find(Graph graph, List<List<Vertex>> cycles) {
        Map<List<Vertex>, Double> opportunities = new HashMap<>();
        for (List<Vertex> allCycle : cycles) {
            double totalArbitrage = 0;
            Collections.reverse(allCycle);
            for (int j = 1; j < allCycle.size(); j++) {
                Vertex u = allCycle.get(j - 1);
                Vertex v = allCycle.get(j);
                List<Edge> adjVertex = graph.getVertexAdjacencyMap().get(u);
                for (Edge e : adjVertex) {
                    if (e.getStartVertex() == u && e.getEndVertex() == v) {
                        totalArbitrage += e.getWeight();
                        break;
                    }
                }
            }

            opportunities.put(allCycle, transformArbitrageValue(totalArbitrage));
        }
        return opportunities;
    }

    private double transformArbitrageValue(double totalArbitrage) {
        double unLoggedArbitrage = Math.pow(2.0, (totalArbitrage * -1)) - 1;
        return Double.parseDouble(decimalFormat.format(unLoggedArbitrage));
    }
}
