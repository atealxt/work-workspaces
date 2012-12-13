package pdms.components.dao;

import java.util.List;
import pdms.components.pojo.Example;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface ExampleDao {

    public Example findByName(String name);

    public List<Example> getExamples();

}
