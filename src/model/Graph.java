package model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.List;

@Data
@Builder
public class Graph {

    private final Map<Vertex, List<Edge>> vertexAdjacencyMap;
    private final List<Vertex> vertices;
    private final List<Edge> edges;
}
