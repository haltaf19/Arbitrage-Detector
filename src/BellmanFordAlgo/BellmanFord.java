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
        this.cycleList = new ArrayList<>();
    }

    public void bellmanFord(Vertex sourceVertex) {
        // Set the distance of the Node you are at to 0
        sourceVertex.setMinDistance(0);

        // Iterate throw all vertex and on every iteration relax all edges
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

                    while (!vertex.equals(edge.getTargetVertex())) {
                        this.cycleList.add(vertex);
                        vertex = vertex.getPreviousVertex();
                    }
                    this.cycleList.add(edge.getTargetVertex());

                    return;
                }
            }
        }
    }

    private boolean hasCycle(Edge edge) {
        return edge.getTargetVertex().getMinDistance() > edge.getStartVertex().getMinDistance() + edge.getWeight();
    }

    public void printCycle() {
        if (this.cycleList != null) {
            System.out.println("Arbitrage Opportunity Detected!");
            for (Vertex vertex : this.cycleList) {
                System.out.println(vertex.toString());
            }
        } else {
            System.out.println("Sorry, there were no arbitrage opportunities detected");
        }
    }
}
