package substitute;

import java.util.List;
import java.util.Map.Entry;

import substitute.model.CommandArguments;
import substitute.model.HttpMethod;
import substitute.model.Parameter;

public final class ParameterManager {

    private ParameterManager() {}

    public static Parameter genParameter(final String args[]) {
        if (args.length == 0) {
            throw new RuntimeException("invalid parameter");
        }
        Parameter p = new Parameter();
        p.setUrl(args[args.length - 1]);
        p = addParameter(p, args);
        return p;
    }

    private static Parameter addParameter(final Parameter p, final String args[]) {
        CommandArguments cmdArgs = new CommandArguments();
        cmdArgs.initOpt(args);
        for (Entry<Character, List<String>> o : cmdArgs.getArgs().entrySet()) {
            switch (o.getKey()) {
                case 'c':
                    p.setConcurrency(Integer.valueOf(o.getValue().get(0)));
                    break;
                case 'n':
                    p.setRequests(Integer.valueOf(o.getValue().get(0)));
                    break;
                case 'b':
                    p.setBufferSize(Integer.valueOf(o.getValue().get(0)));
                    break;
                case 'H':
                    for (String s : o.getValue()) {
                        p.getHeaders().put(s.substring(0, s.indexOf(":")), s.substring(s.indexOf(":") + 1).trim());
                    }
                    break;
                case 'C':
                    for (String s : o.getValue()) {
                        p.getCookies().put(s.substring(0, s.indexOf("=")), s.substring(s.indexOf("=") + 1).trim());
                    }
                    break;
                case 'k':
                    p.setKeepAlive(true);
                    break;
                case 'i':
                    p.setHttpMethod(HttpMethod.HEAD);
                    break;
                case 'm':
                    int m = Integer.valueOf(o.getValue().get(0));
                    for (HttpMethod method : HttpMethod.values()) {
                        if (m != method.ordinal()) {
                            continue;
                        }
                        p.setHttpMethod(method);
                    }
                    break;
                case 'p':
                    p.setPostFile(o.getValue().get(0));
                    break;
                case 't':
                    p.setTimeLimit(Integer.valueOf(o.getValue().get(0)));
                    break;
                case 'h':
                    return null;
                default:
                    break;
            }
        }
        return p;
    }

}
