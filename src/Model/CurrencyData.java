package Model;

import BellmanFordAlgo.Graph;
import BellmanFordAlgo.Vertex;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CurrencyData {
    private Map data;
    private ArrayList tickers;
    private Graph graph;
    private String key = "791fbaa14b74b8e3bffaaccac74c92d0";

    // Hashmap data <ticker, [bid, ask]>
    public CurrencyData() {
        data = new HashMap();
        tickers = new ArrayList();
        graph = new Graph();
    }

    // Parse the data from the api and store it in hashmap
    public void getData(){
        try {
            URL url = new URL("https://financialmodelingprep.com/api/v3/forex?apikey=791fbaa14b74b8e3bffaaccac74c92d0");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            StringBuilder inline = new StringBuilder();
            if (responseCode != 200) {
                throw new RuntimeException("HTTPResponseCode: " + responseCode);
            } else {
                Scanner sc = new Scanner(url.openStream());

                while (sc.hasNext()) {
                    inline.append(sc.nextLine());
                }
                sc.close();
            }
            JSONParser parse = new JSONParser();
            JSONObject jsonObject = (JSONObject) parse.parse(inline.toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("forexList");

            for (Object o : jsonArray) {
                JSONObject jsonobj_1 = (JSONObject) o;
                String ticker = (String) jsonobj_1.get("ticker");
                String exch1 = ticker.substring(0,3);
                String exch2 = ticker.substring(4,7);
                Double bid = Double.parseDouble((String) jsonobj_1.get("bid"));
                Double ask = Double.parseDouble((String) jsonobj_1.get("ask"));

                graph.addEdge(new Vertex(exch1), new Vertex(exch2), bid);
                graph.addEdge(new Vertex(exch2), new Vertex(exch1), (1 / ask));

                addToList(exch1, exch2);

                ArrayList prices = new ArrayList();
                prices.add(bid);
                prices.add(ask);
                data.put(ticker, prices);
            }


        } catch(Exception e){
            System.out.println("Exception Caught");
        }
    }

    private void addToList(String exch1, String exch2) {
        if(!this.tickers.contains(exch1)){
            this.tickers.add(exch1);
        }
        if(!this.tickers.contains(exch2)){
            this.tickers.add(exch2);
        }
    }

    public Graph getGraph() {
        return graph;
    }

    public Map getMap() {
        return this.data;
    }

    public ArrayList getTickers() {
        return tickers;
    }


}






