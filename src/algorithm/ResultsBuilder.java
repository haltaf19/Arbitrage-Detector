package algorithm;

import model.Vertex;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ResultsBuilder {

    private static final String NO_OPPORTUNITIES = "ARBITRAGE OPPORTUNITY NOT FOUND";
    private static final String HEADER = "ARBITRAGE OPPORTUNITY DETECTED";
    private static final String HEADER_SEPARATOR = "==================================";
    private static final String PATH_SEPARATOR = ",";
    private static final String PATH_START = "Path: [";
    private static final String PATH_END = "]";


    public String buildOutput(Map<List<Vertex>, Double> opportunities) {
        if (opportunities.isEmpty()) {
            return NO_OPPORTUNITIES;
        }

        StringBuilder results = new StringBuilder(initializePathResults());
        results.append(PATH_START);
        for (Iterator<Entry<List<Vertex>, Double>> pathIterator = opportunities.entrySet().iterator(); pathIterator.hasNext(); ) {
            Entry<List<Vertex>, Double> path = pathIterator.next();
            for (Iterator<Vertex> vertexIterator = path.getKey().iterator(); vertexIterator.hasNext(); ) {
                Vertex vertex = vertexIterator.next();
                results.append(vertex.getId());
                if (vertexIterator.hasNext()) {
                    results.append(String.format("%s ", PATH_SEPARATOR));
                }
            }
            results.append(String.format("%s\n", PATH_END));
            results.append(String.format("Return: %s%%\n", path.getValue()));

            if (pathIterator.hasNext()) {
                results.append(PATH_START);
            }
        }

        return results.toString();
    }

    private String initializePathResults() {
        return String.format("%s\n%s\n", HEADER, HEADER_SEPARATOR);
    }
}
