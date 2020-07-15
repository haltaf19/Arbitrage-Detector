import BellmanFordAlgo.BellmanFordAlgorithm;
import Model.CurrencyData;

public class Main {
        public static void main(String[] args){
            CurrencyData currencyData = new CurrencyData();
            currencyData.getData();
            BellmanFordAlgorithm bellmanFordAlgorithm = new BellmanFordAlgorithm(currencyData.getGraph(),
                    currencyData.getGraph().getVertices().get(0));
            bellmanFordAlgorithm.arbToString();


        }
    }


