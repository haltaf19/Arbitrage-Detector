package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class CurrencyData {
    private HashMap data;

    // Hashmap data <ticker, [bid, ask]>
    public CurrencyData() {
        data = new HashMap();
    }

    // Parse the data from the api and store it in hashmap
    public void getData() throws IOException, org.json.simple.parser.ParseException {
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
            Double bid = Double.parseDouble((String) jsonobj_1.get("bid"));
            Double ask = Double.parseDouble((String) jsonobj_1.get("ask"));

            ArrayList prices = new ArrayList();
            prices.add(bid);
            prices.add(ask);
            data.put(ticker, prices);
        }
        System.out.println(data);


    }

    public HashMap<String, ArrayList<Double>> getMap() {
        return this.data;
    }

}






