package BellmanFordAlgo;

public class Edge {
    private Vertex startVertex;
    private Vertex targetVertex;
    private double weight;

    public Edge(Vertex startVertex, Vertex targetVertex, Double weight){
        this.weight = weight;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }



    public Vertex getTargetVertex() {
        return targetVertex;
    }



    public double getWeight() {
        return weight;
    }


}
