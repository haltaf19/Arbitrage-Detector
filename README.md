# ForexBellmanFord

A console based application that uses the Bellman-Ford algorithm to detect arbitrage opportunities in the FOREX market.

The Bellman-Ford algorithm finds the minimum path from a single source vertex to all other vertices on a weighted
directed graph. This algorithm is different from Dijkstra as it is able to detect negative-weight cycles. These
cycles allow us to detect arbitrage opportunities.

The application assigns currencies to different vertices with the edge weight representing the exchange rate. Since the 
algorithm finds the minimum distance and we are looking for the maximum amount of currency recieved, the exchange rate will be transformed by taking
its logarithm and multiplying it by -1.

Below is a sample output.
![Path](Capture.PNG)

###Future Additions
* Implement JUnit Tests
* Calculate total return of arbitrage
* Have the algorithm continuously updating in real time

 