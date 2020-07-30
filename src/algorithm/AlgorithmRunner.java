package algorithm;

import graph.GraphBuilder;
import model.Graph;
import model.Vertex;

import java.util.List;
import java.util.Map;

public class AlgorithmRunner {
    private final Graph graph;

    public AlgorithmRunner() {
        graph = new GraphBuilder().build();
    }

    public void run() {
        List<List<Vertex>> cycles = new CycleFinder(graph).findCycles();
        Map<List<Vertex>, Double> opportunities = new OpportunityFinder(graph).findAllOpportunities(cycles);
        new OpportunityPrinter().print(opportunities);
    }
}
