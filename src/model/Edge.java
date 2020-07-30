package model;

import lombok.Data;

@Data
public class Edge {

    private final Vertex startVertex;
    private final Vertex endVertex;
    private final Double weight;
}
