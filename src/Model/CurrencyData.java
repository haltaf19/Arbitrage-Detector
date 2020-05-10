package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CurrencyData {
    private Map data;
    private ArrayList tickers;

    // Hashmap data <ticker, [bid, ask]>
    public CurrencyData() {
        data = new HashMap();
        tickers = new ArrayList();
    }

    // Parse the data from the api and store it in hashmap
    public  void getData() throws IOException, org.json.simple.parser.ParseException {
        URL url = new URL("https://financialmodelingprep.com/api/v3/forex");
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

            addToList(exch1, exch2);

            ArrayList prices = new ArrayList();
            prices.add(bid);
            prices.add(ask);
            data.put(ticker, prices);
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

    public Map getMap() {
        return this.data;
    }

    public ArrayList getTickers() {
        return tickers;
    }


}






