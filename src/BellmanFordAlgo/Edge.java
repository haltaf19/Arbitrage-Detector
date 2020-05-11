package BellmanFordAlgo;

import java.util.HashMap;

public class Edge {
    private Vertex startVertex;
    private Vertex targetVertex;
    private double weight;
    private HashMap<String, Vertex> seenVertices;

    public Edge( Double weight, HashMap<String, Vertex> seenVertices){
        this.weight = weight;
        //this.startVertex = createNewVertex(startVertex);
        //this.targetVertex = createNewVertex(targetVertex);
        this.seenVertices = seenVertices;
    }


    public void createStartVertex(Vertex v){
        Vertex startVertex;
        if(seenVertices.containsKey(v.getId())){
            startVertex = seenVertices.get(v.getId());
        } else {
            startVertex = v;
            seenVertices.put(v.getId(), v);
        }
       this.startVertex = startVertex;
    }

    public void createTargetVertex(Vertex v){
        Vertex targetVertex;
        if(seenVertices.containsKey(v.getId())){
            targetVertex = seenVertices.get(v.getId());
        } else {
            targetVertex = v;
            seenVertices.put(v.getId(), v);
        }
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
