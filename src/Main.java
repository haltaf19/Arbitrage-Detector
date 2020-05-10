import BellmanFordAlgo.BellmanFord;
import BellmanFordAlgo.VertexCreator;
import Model.CurrencyData;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
        public static void main(String[] args) throws IOException, ParseException {
            CurrencyData currencyData = new CurrencyData();
            currencyData.getData();
            VertexCreator vertexCreator = new VertexCreator(currencyData);
            vertexCreator.initializeVertexList();
            vertexCreator.initializeEdgeList();
            BellmanFord bellmanFord = new BellmanFord(vertexCreator.getVertexList(), vertexCreator.getEdgeList());
            bellmanFord.bellmanFord(vertexCreator.getVertexList().get(0));
            bellmanFord.printCycle();
            System.out.println(vertexCreator.getCurrencyData());


        }
    }


