# Arbitrage Detector in the Foreign Exchange Market 
## Applications of the Bellman-Ford Algorithm

A console based application that uses the Bellman-Ford algorithm to detect arbitrage opportunities.

The Bellman-Ford algorithm finds the minimum path from a single source vertex to all other vertices on a weighted
directed graph. This algorithm is different from Dijkstra as it is able to detect negative-weight cycles. These negative-weight
cycles represent arbitrage opportunities in the market and in theory allow us to make risk-free profit.

The application assigns currencies to different vertices with the edge weight representing the exchange rate. Since the 
algorithm finds the minimum distance the exchange rate will be transformed by taking
its logarithm and multiplying it by -1. By doing so, we will be able to find the path with the maximum return.

With the help of my friend Miguel, I was able to learn more about Object-Oriented Design Principles through the use of tools such as Lombok and Guice. Lombok was mainly used to reduce verbosity in the code and make it more readable. Guice was used to reduce coupling throughout the application. Furthemore, it was also used to implement the [inversion of control](https://en.wikipedia.org/wiki/Inversion_of_control#:~:text=In%20software%20engineering%2C%20inversion%20of,control%20from%20a%20generic%20framework) design principle and reduce dependencies.

The data is collected with an API from Financial Modelling Prep (FMP). 

The graph is constructed in a manner similar to an adjacency list and has runtime of O(VE) where V is the number of vertices 
and E is the number of edges. 

Below is a sample output. </br>
![Path](Capture.PNG)

### Future Additions
* Have the algorithmRunner continuously updating in real time
