package substitute;

import java.io.IOException;

import substitute.model.Parameter;

public interface Analyzer {

    void showHelp();

    void analyze(final Parameter p) throws IOException;
}
