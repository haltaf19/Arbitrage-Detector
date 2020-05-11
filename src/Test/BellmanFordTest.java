package Test;

import BellmanFordAlgo.BellmanFord;
import BellmanFordAlgo.VertexCreator;
import Model.CurrencyData;

public class BellmanFordTest {


    public static void main(String[] args) {
        CurrencyData currencyData  = new CurrencyData();
       VertexCreator g2 = new VertexCreator(currencyData);
       g2.addEdge("E", "C", -5.0);
       g2.addEdge("C", "D", 2.0);
       g2.addEdge("D", "E", 1.0);


        BellmanFord bellmanFord = new BellmanFord(g2.getVertexList(), g2.getEdgeList());
        bellmanFord.bellmanFord(g2.getVertexList().get(0));
        bellmanFord.printCycle();
    }

}
