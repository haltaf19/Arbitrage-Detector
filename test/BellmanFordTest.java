import algorithm.AlgorithmRunner;
import model.Graph;
import model.Vertex;
import datasource.CurrencyClient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BellmanFordTest {
    CurrencyClient CurrencyClient;
    Graph g1;
    Graph g2;
    Vertex a;
    Vertex b;
    Vertex c;
    Vertex d;
    Vertex e;

    Vertex USD;
    Vertex EUR;
    Vertex GBP;
    Vertex CHF;
    Vertex CAD;


    AlgorithmRunner bfa;

    @Before
    public void setUp() {
//        g1 = new Graph();
//        g2 = new Graph();
//        a = new Vertex("A");
//        b = new Vertex("B");
//        c = new Vertex("C");
//        d = new Vertex("D");
//        e = new Vertex("E");
//
//        g1.addEdge(a, b, 5.0);
//        g1.addEdge(b, c, 2.1);
//        g1.addEdge(c, a, 1.9);
//
//
//        USD = new Vertex("USD");
//        EUR = new Vertex("EUR");
//        GBP = new Vertex("GBP");
//        CHF = new Vertex("CHF");
//        CAD = new Vertex("CAD");
//
//        g2.addEdge(USD, EUR, 0.741);
//        g2.addEdge(USD, GBP, 0.657);
//        g2.addEdge(USD, CHF, 1.061);
//        g2.addEdge(USD, CAD, 1.011);
//
//        g2.addEdge(EUR, USD, 1.35);
//        g2.addEdge(EUR, GBP, 0.889);
//        g2.addEdge(EUR, CHF, 1.433);
//        g2.addEdge(EUR, CAD, 1.366);
//
//
//        g2.addEdge(GBP, EUR, 1.126);
//        g2.addEdge(GBP, USD, 1.521);
//        g2.addEdge(GBP, CHF, 1.641);
//        g2.addEdge(GBP, CAD, 1.538);
//
//        g2.addEdge(CHF, USD, 0.943);
//        g2.addEdge(CHF, EUR, 0.698);
//        g2.addEdge(CHF, GBP, 0.62);
//        g2.addEdge(CHF, CAD, 0.953);
//
//        g2.addEdge(CAD, USD, 0.955);
//        g2.addEdge(CAD, EUR, 0.732);
//        g2.addEdge(CAD, GBP, 0.65);
//        g2.addEdge(CAD, CHF, 1.049);

    }

    @Test
    public void x() {
//        new AlgorithmRunner(new GraphBuilder()).run();
    }

//    @Test
//    public void testFirstCycle() {
//        bfa = new AlgorithmRunner();
//        bfa.run();
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(b);
//        arrayList.add(c);
//        arrayList.add(a);
//        arrayList.add(b);
//        assertEquals(bfa.getAllCycles().get(0), arrayList);
//        assertEquals((bfa.getOpportunities().get(bfa.getAllCycles().get(0))), 6.963);
//
//    }
//
//    @Test
//    public void testSecondCycle() {
//        bfa = new AlgorithmRunner();
//        bfa.run();
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(GBP);
//        arrayList.add(CHF);
//        arrayList.add(GBP);
//        assertEquals(bfa.getAllCycles().get(0), arrayList);
//        assertEquals((bfa.getOpportunities().get(bfa.getAllCycles().get(0))), 0.012);
//    }

    ;

}
