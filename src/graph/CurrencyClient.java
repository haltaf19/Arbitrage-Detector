package graph;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyClient {

    URL url;
    HttpURLConnection connection;

    public CurrencyClient(URL url, HttpURLConnection connection) {
        this.url = url;
        this.connection = connection;
    }

    public JSONArray getCurrencyData() {
        try {
            StringBuilder currencyData = new StringBuilder();
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                currencyData.append(sc.nextLine());
            }
            sc.close();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(currencyData.toString());
            return (JSONArray) jsonObject.get("forexList");
        } catch (Exception e) {
            return new JSONArray();
        }
    }
}
