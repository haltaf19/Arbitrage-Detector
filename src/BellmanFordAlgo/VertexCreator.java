package BellmanFordAlgo;

import Model.CurrencyData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VertexCreator {

    private CurrencyData currencyData;
    private List<Vertex> vertexList;
    private List<Edge> edgeList;
    //private Map data;
    List<String> tickers;

    public VertexCreator(CurrencyData currencyData) {
        this.currencyData = currencyData;
        tickers = currencyData.getTickers();
        vertexList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }

    public void addEdge(String s, String c, Double d){
        Vertex a = new Vertex(s);
        Vertex b = new Vertex(c);
        vertexList.add(a);
        vertexList.add(b);
        edgeList.add(new Edge(a,b,d));
    }

    public void initializeEdgeList() {
        Map innerData = currencyData.getMap();
        List<String> keys = new ArrayList<>();

        for (Object o : innerData.keySet()) {
            String key = String.valueOf(o);
            keys.add(key);
        }


        List<String> iteratedKey = new ArrayList<>();
        for (String t : tickers) {
            for (String key : keys) {
                if(iteratedKey.contains(key)){
                    continue;
                }
                String exch1 = key.substring(0, 3);
                String exch2 = key.substring(4, 7);
                ArrayList<Double> d = (ArrayList<Double>) innerData.get(key);
                if (exch1.contains(t)) {
                    edgeList.add(new Edge(findVertexById(exch1), findVertexById(exch2), (-1 * Math.log(d.get(0)))));
                    edgeList.add(new Edge(findVertexById(exch2), findVertexById(exch1), (1 / (-1 * Math.log(d.get(1))))));
                    innerData.remove(key);
                    iteratedKey.add(key);
                }
                if (exch2.contains(t)) {
                    edgeList.add(new Edge(findVertexById(exch2), findVertexById(exch1), (-1 * Math.log(d.get(1)))));
                    edgeList.add(new Edge(findVertexById(exch1), findVertexById(exch2), (1 / (-1 * Math.log(d.get(0))))));
                    innerData.remove(key);
                    iteratedKey.add(key);
                }
            }
        }
    }


    private Vertex findVertexById(String t) {
        for (Vertex v : vertexList) {
            if (v.getId().equals(t)) {
                return v;
            }
        }
        return null;
    }

    public void initializeVertexList() {
        for (String t : tickers) {
            vertexList.add(new Vertex(t));
        }
    }

    public CurrencyData getCurrencyData() {
        return currencyData;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public Map getData() {
        return currencyData.getMap();
    }

    public List<String> getTickers() {
        return tickers;
    }
}
