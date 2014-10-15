package substitute.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandArguments {

    private Map<Character, List<String>> args = new HashMap<Character, List<String>>();

    public void initOpt(final String args[]) {
        int i = 0, size = args.length - 1;
        for (i = 0; i < size; i++) {

            if (!PATTERN_ARG.matcher(args[i]).matches()) {
                continue;
            }
            List<String> list = this.args.get(args[i].charAt(1));
            if (list == null) {
                list = new ArrayList<String>();
            }
            for (int j = i + 1; j < size; j++) {
                if (PATTERN_ARG.matcher(args[j]).matches()) {
                    break;
                }
                list.add(args[j]);
            }
            this.args.put(args[i].charAt(1), list);
        }
    }

    public Map<Character, List<String>> getArgs() {
        return args;
    }

    private final static Pattern PATTERN_ARG = Pattern.compile("^\\-.*");
}
