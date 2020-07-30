package graph;

import model.Graph;
import model.Vertex;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GraphBuilder {

    private URL url;
    private CurrencyClient currencyClient;

    public Graph build() {
        try {
            url = new URL("https://financialmodelingprep.com/api/v3/forex?apikey=791fbaa14b74b8e3bffaaccac74c92d0");
            currencyClient = new CurrencyClient(url, initializeConnection());
        } catch (Exception e) {
            System.out.println("Exception thrown while constructing graph.");
        }

        JSONArray currencyData = currencyClient.getCurrencyData();

        Graph graph = new Graph();
        for (Object o : currencyData) {
            JSONObject jsonObject = (JSONObject) o;
            String ticker = jsonObject.get("ticker").toString();
            String exchange1 = ticker.substring(0, 3);
            String exchange2 = ticker.substring(4, 7);

            double bid = Double.parseDouble(jsonObject.get("bid").toString());
            double ask = Double.parseDouble(jsonObject.get("ask").toString());

            graph.addEdge(new Vertex(exchange1), new Vertex(exchange2), bid);
            graph.addEdge(new Vertex(exchange2), new Vertex(exchange1), (1 / ask));
        }

        return graph;
    }

    // TODO: Refactor using Guice (Dependency Injection)
    private HttpURLConnection initializeConnection() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        return conn;
    }
}
