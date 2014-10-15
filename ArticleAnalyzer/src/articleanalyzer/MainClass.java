package articleanalyzer;

public class MainClass {

    public static void main(final String args[]) {
        ArtileAnalyzer analyzer = new ArtileAnalyzerImpl();
        analyzer.index();
        analyzer.statistics();
        analyzer.analyzeResult();
    }
}
