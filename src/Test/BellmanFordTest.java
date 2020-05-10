package Test;

import BellmanFordAlgo.BellmanFord;
import BellmanFordAlgo.VertexCreator;
import Model.CurrencyData;

public class BellmanFordTest {


    public static void main(String[] args) {
        CurrencyData currencyData  = new CurrencyData();
       VertexCreator g2 = new VertexCreator(currencyData);
//        g2.addEdge("USD", "CAD", 4.0);
//        g2.addEdge("USD", "AUD", 4.0);
//        // "CAD" nowhere
//        g2.addEdge("AUD", "EUR", 4.0);
//        g2.addEdge("AUD", "CHF", -2.0);
//        g2.addEdge("GBP", "USD", 3.0);
//        g2.addEdge("GBP", "AUD", 2.0);
//        g2.addEdge("EUR", "GBP", 1.0);
//        g2.addEdge("EUR", "JPY", -2.0); // *
//        g2.addEdge("CHF", "CAD", 3.0);
//        g2.addEdge("CHF", "EUR", -3.0);
//        g2.addEdge("JPY", "CHF", 2.0);
//        g2.addEdge("JPY", "NZD", 2.0); // *
//        g2.addEdge("NZD", "EUR", -2.0); //

        BellmanFord bellmanFord = new BellmanFord(g2.getVertexList(), g2.getEdgeList());
        bellmanFord.bellmanFord(g2.getVertexList().get(0));
        bellmanFord.printCycle();
    }




    // add Edges

    // *part of a negative-weight circle -2 + 2 - 2 = -2 < "USD"

    // print Graph

}
