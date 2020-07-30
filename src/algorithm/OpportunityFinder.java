package algorithm;

import model.Edge;
import model.Graph;
import model.Vertex;

import java.text.DecimalFormat;
import java.util.*;

public class OpportunityFinder {

    Graph graph;

    public OpportunityFinder(Graph graph) {
        this.graph = graph;
    }

    public Map<List<Vertex>, Double> findAllOpportunities(List<List<Vertex>> cycles) {
        Map<List<Vertex>, Double> opportunities = new HashMap<>();
        for (List<Vertex> allCycle : cycles) {
            double totalArb = 0;
            Collections.reverse(allCycle);
            for (int j = 1; j < allCycle.size(); j++) {
                Vertex u = allCycle.get(j - 1);
                Vertex v = allCycle.get(j);
                List<Edge> adjVertex = graph.getAdjacentVertexMap().get(u);
                for (Edge e : adjVertex) {
                    if (e.getStartVertex() == u && e.getEndVertex() == v) {
                        totalArb += e.getWeight();
                        break;
                    }
                }
            }
            totalArb = Math.pow(2.0, (totalArb * -1)) - 1;
            DecimalFormat df = new DecimalFormat("###.###");
            String temp = df.format(totalArb);
            totalArb = Double.parseDouble(temp);
            opportunities.put(allCycle, totalArb);
        }
        return opportunities;
    }

}
