package BellmanFordAlgo;

import java.text.DecimalFormat;
import java.util.*;

public class BellmanFordAlgorithm {
    private Graph graph;
    private Integer numOfNodes;
    private Vertex sourceVertex;
    private HashMap<Vertex, Double> distances;
    private HashMap<Vertex, Vertex> predecessor;
    private List<Edge> edges;
    private ArrayList<List<Vertex>> cycles;
    private ArrayList<List<Vertex>> allCycles;
    private HashMap<List<Vertex>, Double> opportunities;



    public BellmanFordAlgorithm(Graph graph, Vertex sourceVertex){
        this.graph = graph;
        this.sourceVertex = sourceVertex;
        edges = new ArrayList<>();
        distances = new HashMap<>();
        predecessor = new HashMap<>();
        allCycles = new ArrayList<>();
        opportunities = new HashMap<>();
    }

    public void arbToString(){
        findAllOpportunities();
        if (opportunities.isEmpty()){
            System.out.println("ARBITRAGE OPPORTUNITY NOT FOUND");
        } else{
            System.out.println("ARBITRAGE OPPORTUNITY DETECTED");
            System.out.println("==================================");
            String s= "Path: [ ";
            for(Map.Entry<List<Vertex>, Double> entry: opportunities.entrySet()) {
                for(int i = 0; i < entry.getKey().size(); i++){
                    s = s + entry.getKey().get(i).getId() + ", ";
                }
                s= s.substring(0, s.length() - 2);
                s+= " ]";
                System.out.println(s);
                System.out.println("Return: " + entry.getValue() * 100 + "%");
                System.out.println();
                s = "Path: [ ";
            }
        }
    }



    private void findAllOpportunities(){
        findAllPaths();
        for(int i = 0; i < allCycles.size(); i++){
            double totalArb = 0;
            List<Vertex> path = allCycles.get(i);
            Collections.reverse(path);
            for(int j = 1; j < path.size(); j++){
                Vertex u = path.get(j-1);
                Vertex v = path.get(j);
                List<Edge> adjVertex = graph.getAdjVertices().get(u);
                for(Edge e: adjVertex){
                    if (e.startVertex == u && e.endVertex == v){
                        totalArb+= e.weight;
                        break;
                    }
                }
            }
            totalArb = Math.pow(2.0, (totalArb * -1)) - 1;
            DecimalFormat df = new DecimalFormat("###.###");
            String temp = df.format(totalArb);
            totalArb = Double.parseDouble(temp);
            opportunities.put(path, totalArb);
        }

    }

    private void findAllPaths(){
        for (Vertex v: graph.getVertices()){
            sourceVertex = v;
            ArrayList<List<Vertex>> retVal = bellmanReturnCycles();
            for(int i = 0; i < retVal.size(); i++){
                allCycles.add(retVal.get(i));
            }
        }
        removeDuplicates();
    }

    private void removeDuplicates() {
        ArrayList<List<Vertex>> newList = new ArrayList<>();
        for(int i = 0; i < allCycles.size(); i++){
            if(!newList.contains(allCycles.get(i))) newList.add(allCycles.get(i));
        }
        allCycles = newList;
    }


    private ArrayList<List<Vertex>> bellmanReturnCycles(){
        for(Vertex v: graph.getVertices()){
            distances.put(v, Double.POSITIVE_INFINITY);
            predecessor.put(v, v);
        }
        edges = graph.getEdges();
        numOfNodes = distances.keySet().size();
        distances.put(sourceVertex, 0.0);
        relaxEdges();
        cycles = new ArrayList<>();
        findNegativeWeightCycle();
        return cycles;
    }



    private void relaxEdges(){
        for (int i = 0; i < numOfNodes - 1; i++){
            for(Edge e: edges){
                if(distances.get(e.endVertex) > distances.get(e.startVertex) + e.getWeight()){
                    distances.put(e.endVertex, distances.get(e.startVertex) + e.getWeight());
                    predecessor.put(e.endVertex, e.startVertex);
                }
            }
        }
    }

    public ArrayList<List<Vertex>> getCycles() {
        return cycles;
    }

    private void findNegativeWeightCycle() {
        HashMap<Vertex, Boolean> seen = new HashMap<>();
        for(Edge e: edges){
            if(seen.containsKey(e.endVertex)) continue;

            if(distances.get(e.endVertex) > distances.get(e.startVertex) + e.getWeight()){
                ArrayList<Vertex> newCycle = new ArrayList<>();
                Vertex x = e.getEndVertex();
                while (true){
                    seen.put(x, true);
                    newCycle.add(x);
                    x = predecessor.get(x);
                    if (x == e.endVertex || newCycle.contains(x)) break;
                }
                int index = newCycle.indexOf(x);
                newCycle.add(x);
                cycles.add(newCycle.subList(index, newCycle.size()));
            }
        }
    }

    public ArrayList<List<Vertex>> getAllCycles() {
        return allCycles;
    }

    public HashMap<List<Vertex>, Double> getOpportunities() {
        return opportunities;
    }
}
