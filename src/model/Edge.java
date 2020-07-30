package model;

import java.util.Objects;

public class Edge {

    private final Vertex startVertex;
    private final Vertex endVertex;
    private final Double weight;

    public Edge(Vertex start, Vertex end, Double weight){
        this.startVertex = start;
        this.endVertex = end;
        this.weight = weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public Double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(startVertex, edge.startVertex) &&
                Objects.equals(endVertex, edge.endVertex) &&
                Objects.equals(weight, edge.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startVertex, endVertex, weight);
    }

    @Override
    public String toString() {
        return "From the Start Vertex " + startVertex + " to the end Vertex " +
                endVertex.getId() + " with edge weight " + weight;
    }
}
