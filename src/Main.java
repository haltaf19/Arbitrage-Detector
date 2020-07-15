import BellmanFordAlgo.BellmanFordAlgorithm;
import Model.CurrencyData;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
        public static void main(String[] args) throws IOException, ParseException {
            CurrencyData currencyData = new CurrencyData();
            currencyData.getData();
            BellmanFordAlgorithm bellmanFordAlgorithm = new BellmanFordAlgorithm(currencyData.getGraph(),
                    currencyData.getGraph().getVertices().get(0));
            bellmanFordAlgorithm.arbToString();


        }
    }


