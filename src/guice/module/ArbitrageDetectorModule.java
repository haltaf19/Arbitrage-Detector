package guice.module;

import algorithm.AlgorithmRunner;
import algorithm.CycleFinder;
import algorithm.OpportunityFinder;
import algorithm.ResultsBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import datasource.CurrencyClient;
import graphbuilder.GraphBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ArbitrageDetectorModule extends AbstractModule {

    @Provides
    @Named("DataSourceURL")
    public URL provideConnectionURL() throws IOException {
        URL baseURL = new URL("https://financialmodelingprep.com/api/v3/forex?apikey=791fbaa14b74b8e3bffaaccac74c92d0");
        HttpURLConnection httpURLConnection = (HttpURLConnection) baseURL.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        return baseURL;
    }

    @Singleton
    @Provides
    public CurrencyClient provideCurrencyClient(@Named("DataSourceURL") URL connectionURL) {
        return new CurrencyClient(connectionURL);
    }

    @Singleton
    @Provides
    public AlgorithmRunner provideAlgorithmRunner(GraphBuilder graphBuilder,
                                                  CycleFinder cycleFinder,
                                                  OpportunityFinder opportunityFinder,
                                                  ResultsBuilder resultsBuilder) {
        return new AlgorithmRunner(graphBuilder, cycleFinder, opportunityFinder, resultsBuilder);
    }
}
