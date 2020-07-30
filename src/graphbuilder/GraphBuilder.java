package graphbuilder;

import com.google.inject.Inject;
import datasource.CurrencyClient;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import model.Edge;
import model.Graph;
import model.Vertex;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(onConstructor = @__({@Inject}))
public class GraphBuilder {

    @NonNull
    private final CurrencyClient currencyClient;

    @NonNull
    private final VertexAdjacencyMapBuilder vertexAdjacencyMapBuilder;

    public Graph build() {
        JSONArray currencyData = currencyClient.getCurrencyData();
        Map<Vertex, List<Edge>> vertexAdjacencyMap = vertexAdjacencyMapBuilder.build(currencyData);

        return Graph.builder()
                .vertexAdjacencyMap(vertexAdjacencyMap)
                .vertices(createVertexList(vertexAdjacencyMap))
                .edges(createEdgeList(vertexAdjacencyMap))
                .build();
    }

    private List<Vertex> createVertexList(Map<Vertex, List<Edge>> vertexAdjacencyMap) {
        return new ArrayList<>(vertexAdjacencyMap.keySet());
    }

    private List<Edge> createEdgeList(Map<Vertex, List<Edge>> vertexAdjacencyMap) {
        List<Edge> allEdges = new ArrayList<>();
        for (Map.Entry<Vertex, List<Edge>> list : vertexAdjacencyMap.entrySet()) {
            allEdges.addAll(list.getValue());
        }
        return allEdges;
    }
}
