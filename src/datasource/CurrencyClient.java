package datasource;

import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.util.Scanner;

@AllArgsConstructor
public class CurrencyClient {

    URL url;

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
