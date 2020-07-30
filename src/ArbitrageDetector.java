import algorithm.AlgorithmRunner;

import static com.google.inject.Guice.createInjector;
import com.google.inject.Injector;
import guice.module.ArbitrageDetectorModule;

public class ArbitrageDetector {
    public static void main(String[] args) {
        Injector injector = createInjector(new ArbitrageDetectorModule());
        AlgorithmRunner algorithmRunner = injector.getInstance(AlgorithmRunner.class);
        System.out.println(algorithmRunner.runAlgorithm());
    }
}