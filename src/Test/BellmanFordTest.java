package Test;

import BellmanFordAlgo.BellmanFord;
import BellmanFordAlgo.VertexCreator;
import Model.CurrencyData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BellmanFordTest {
    CurrencyData currencyData;
    VertexCreator g2;
    BellmanFord bellmanFord;
    List<String> correctList;

    @Before
    public void setUp(){
        currencyData  = new CurrencyData();
        g2 = new VertexCreator(currencyData);
        correctList = new ArrayList<>();
        g2.addEdge("E", "C", -5.0);
        g2.addEdge("C", "D", 2.0);
        g2.addEdge("D", "E", 1.0);
        correctList.add("E");
        correctList.add("D");
        correctList.add("C");

    }

    @Test
    public void testCycle(){
        bellmanFord = new BellmanFord(g2.getVertexList(), g2.getEdgeList());
        bellmanFord.bellmanFord(g2.getVertexList().get(0));
        assertEquals(bellmanFord.getCycleListId(), correctList);


    }







}
