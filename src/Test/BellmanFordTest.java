package Test;

import BellmanFordAlgo.BellmanFordAlgorithm;
import BellmanFordAlgo.Graph;
import BellmanFordAlgo.Vertex;
import Model.CurrencyData;
import org.junit.Before;
import org.junit.Test;

public class BellmanFordTest {
    CurrencyData currencyData;
    Graph g1;
    Graph g2;
    Vertex a;
    Vertex b;
    Vertex c;
    Vertex d;
    Vertex e;
    BellmanFordAlgorithm bfa;


    @Before
    public void setUp(){
        g1 = new Graph();
        g2 = new Graph();
        a = new Vertex("A");
        b = new Vertex("B");
        c = new Vertex("C");
        d = new Vertex("D");
        e = new Vertex("E");
        g1.addEdgeTest(a, b, 5.0);
        g1.addEdgeTest(b, c, 2.1);
        g1.addEdgeTest(c, a, 1.9);

        g2.addEdge(b, c, 1.3);
        g2.addEdge(b, e, 7.5);
        g2.addEdge(e, b, 5.8);
        g2.addEdge(e, d, 3.1);
        g2.addEdge(d, e, 1.9);
        g2.addEdge(e, c, -5.0);
        g2.addEdge(c, d, 2.1);
    }

    @Test
    public void testFirstCycle(){
        bfa = new BellmanFordAlgorithm(g1, b);
        bfa.arbToString();
        System.out.println(bfa.getCycles());
    }

    @Test
    public void testSecondCycle(){
        bfa = new BellmanFordAlgorithm(g2, b);
        bfa.arbToString();
        System.out.println(bfa.getCycles());
    }








}
