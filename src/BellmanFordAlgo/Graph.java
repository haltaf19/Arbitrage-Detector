package BellmanFordAlgo;

import java.util.*;

public class Graph {
    private Map<Vertex, List<Edge>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<>();
    }

    public void addEdge(Vertex start, Vertex end, Double weight) {
        Double input = Math.log(weight) * -1;
        Edge edge = new Edge(start, end, input);
        if (!adjVertices.containsKey(start)) {
            adjVertices.put(start, new LinkedList<>());
        }
        adjVertices.get(start).add(edge);
    }

    public void addEdgeTest(Vertex start, Vertex end, Double weight) {
        Edge edge = new Edge(start, end, weight);
        if (!adjVertices.containsKey(start)) {
            adjVertices.put(start, new LinkedList<>());
        }
        adjVertices.get(start).add(edge);
    }


    public Map<Vertex, List<Edge>> getAdjVertices() {
        return adjVertices;
    }

    public List<Vertex> getVertices(){
        ArrayList vertices  = new ArrayList();
        for(Map.Entry<Vertex, List<Edge>> entry: adjVertices.entrySet()) {
            vertices.add(entry.getKey());
        }
        return vertices;
    }

    public List<Edge> getEdges(){
        ArrayList edges  = new ArrayList();
        for(Map.Entry<Vertex, List<Edge>> entry: adjVertices.entrySet()) {
            List edgeList = entry.getValue();
            for(Object e: edgeList){
                edges.add(e);
            }
        }
        return edges;
    }

    public void printGraph() {
        for(Map.Entry<Vertex, List<Edge>> entry: adjVertices.entrySet()){
            String v = entry.getKey().getId();
            System.out.println("Vertex " + v + " is connected to the following: ");
            for(int i = 0; i < entry.getValue().size(); i++){
                System.out.println(entry.getValue().get(i).toString());
            }
        }
    }
}
