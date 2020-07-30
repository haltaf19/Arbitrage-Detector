package algorithm;

import graphbuilder.GraphBuilder;
import lombok.AllArgsConstructor;
import model.Graph;
import model.Vertex;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class AlgorithmRunner {
    private final GraphBuilder graphBuilder;
    private final CycleFinder cycleFinder;
    private final OpportunityFinder opportunityFinder;
    private final ResultsBuilder resultsBuilder;

    public String runAlgorithm() {
        Graph graph = graphBuilder.build();
        List<List<Vertex>> cycles = cycleFinder.findCycles(graph);
        Map<List<Vertex>, Double> opportunities = opportunityFinder.find(graph, cycles);
        return resultsBuilder.buildOutput(opportunities);
    }
}
