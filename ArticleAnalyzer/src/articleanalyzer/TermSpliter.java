package articleanalyzer;

import java.io.Reader;
import java.util.List;

public interface TermSpliter {

    List<String> term(Reader reader);
}
