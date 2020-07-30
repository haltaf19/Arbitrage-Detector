package graphbuilder;

import com.google.inject.Inject;
import lombok.AllArgsConstructor;
import model.Edge;
import model.Vertex;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(onConstructor = @__({@Inject}))
public class VertexAdjacencyMapBuilder {

    public Map<Vertex, List<Edge>> build(JSONArray currencyData) {
        Map<Vertex, List<Edge>> adjacentVertexMap = new HashMap<>();
        for (Object o : currencyData) {
            JSONObject jsonObject = (JSONObject) o;
            String ticker = jsonObject.get("ticker").toString();
            String exchange1 = ticker.substring(0, 3);
            String exchange2 = ticker.substring(4, 7);

            double bid = Double.parseDouble(jsonObject.get("bid").toString());
            double ask = Double.parseDouble(jsonObject.get("ask").toString());

            addEdge(adjacentVertexMap, new Vertex(exchange1), new Vertex(exchange2), bid);
            addEdge(adjacentVertexMap, new Vertex(exchange2), new Vertex(exchange1), 1 / ask);
        }
        return adjacentVertexMap;
    }

    private void addEdge(Map<Vertex, List<Edge>> adjacentVertexMap, Vertex start, Vertex end, double weight) {
        Edge edge = new Edge(start, end, modifyEdgeWeight(weight));
        adjacentVertexMap.putIfAbsent(start, new LinkedList<>());
        adjacentVertexMap.get(start).add(edge);
    }

    private double modifyEdgeWeight(double weight) {
        return Math.log(weight) * -1;
    }
}
