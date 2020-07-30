package algorithm;

import model.Vertex;

import java.util.List;
import java.util.Map;

public class OpportunityPrinter {

    public void print(Map<List<Vertex>, Double> opportunities) {
        if (opportunities.isEmpty()) {
            System.out.println("ARBITRAGE OPPORTUNITY NOT FOUND");
        } else {
            System.out.println("ARBITRAGE OPPORTUNITY DETECTED");
            System.out.println("==================================");
            StringBuilder resultsString = new StringBuilder("Path: [ ");
            for (Map.Entry<List<Vertex>, Double> entry : opportunities.entrySet()) {
                resultsString.append(entry.getKey().get(0).getId());
                for (int i = 1; i < entry.getKey().size(); i++) {
                    resultsString.append(", ").append(entry.getKey().get(i).getId());
                }

                resultsString.append(" ]");
                System.out.println(resultsString);
                System.out.println("Return: " + entry.getValue() * 100 + "%");
                System.out.println();
                resultsString = new StringBuilder("Path: [ ");
            }
        }
    }
}
