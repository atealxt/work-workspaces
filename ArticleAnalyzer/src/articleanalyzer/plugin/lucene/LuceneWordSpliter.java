package articleanalyzer.plugin.lucene;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

import articleanalyzer.TermSpliter;

public class LuceneWordSpliter implements TermSpliter {

    @Override
    public List<String> term(final Reader reader) {
        List<String> terms = new ArrayList<String>();
        TokenStream stream = new StandardTokenizer(Version.LUCENE_29, reader);
        TermAttribute termAttr = (TermAttribute) stream.getAttribute(TermAttribute.class);
        try {
            while (stream.incrementToken()) {
                terms.add(termAttr.term().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return terms;
    }
}
