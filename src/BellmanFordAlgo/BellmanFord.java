package BellmanFordAlgo;

import java.util.ArrayList;
import java.util.List;

public class BellmanFord {
    private List<Vertex> vertexList;
    private List<Edge> edgeList;
    private List<Vertex> cycleList;

    public BellmanFord(List<Vertex> vertexList, List<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }

    public void bellmanFord(Vertex sourceVertex) {
        // Set the distance of the Node you are at to 0
        sourceVertex.setMinDistance(0);
        // Iterate through all vertex and on every iteration relax all edges
        for (int i = 0; i < vertexList.size() - 1; i++) { // V-1 iterations
            for (Edge edge : edgeList) {
                if (edge.getStartVertex().getMinDistance() == Integer.MAX_VALUE) {
                    continue;
                }

                double newDistance = edge.getStartVertex().getMinDistance() + edge.getWeight();

                if (newDistance < edge.getTargetVertex().getMinDistance()) {
                    edge.getTargetVertex().setMinDistance(newDistance);
                    edge.getTargetVertex().setPreviousVertex(edge.getStartVertex());
                }
            }

        }


        for (Edge edge : edgeList) {  // V-Iteration
            if (edge.getStartVertex().getMinDistance() != Integer.MAX_VALUE) {
                if (hasCycle(edge)) {
                    Vertex vertex = edge.getStartVertex();
                    List<Vertex> curCycleList = new ArrayList<>();


             /*       while (!vertex.equals(edge.getTargetVertex())) {
                        curCycleList.add(vertex);
                        //this.cycleList.add(vertex);
                        vertex = vertex.getPreviousVertex();
                    }*/


                    while (!curCycleList.contains(vertex)) {
                        curCycleList.add(vertex);
                        vertex = vertex.getPreviousVertex();
                    }
                    if (!curCycleList.isEmpty()) {
                        this.cycleList = curCycleList;
                    }
                    //this.cycleList.add(edge.getTargetVertex());

                    return;
                }
            }
        }
    }

    private boolean hasCycle(Edge edge) {
        //return edge.getStartVertex().getMinDistance() + edge.getWeight() < edge.getTargetVertex().getMinDistance();
        return edge.getTargetVertex().getMinDistance() > edge.getStartVertex().getMinDistance() + edge.getWeight();
    }

    public void printCycle() {
        if (this.cycleList != null) {
            System.out.println("Arbitrage Opportunity Detected!");
            System.out.println("================================");
            String result = "PATH: [";
            for(int i = 0; i < this.cycleList.size(); i++){

                result = result + " " + cycleList.get(i).getId();
            }
           System.out.println(result + " " + cycleList.get(0).getId() + " ]");
            //System.out.println(calcArb());
        } else {
            System.out.println("Sorry, there were no arbitrage opportunities detected");
        }
    }

//    private String calcArb() {
//        Double total = 0.0;
//        for(Vertex v: cycleList){
//            total +=  v.getMinDistance();
//        }
//        System.out.println(total);
//        System.out.println(-total);
//        Double arb = Math.exp(-total) - 1;
//        String s = "Total Return: " + arb;
//        return s;
//    }

}
