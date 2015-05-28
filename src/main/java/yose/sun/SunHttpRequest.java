package yose.sun;

import com.sun.net.httpserver.HttpExchange;
import yose.core.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by L.x on 15-5-28.
 */
public class SunHttpRequest implements HttpRequest {
    public static final int NAME_POS = 0;
    public static final int VALUE_POS = 1;
    public static final String[] EMPTY_STRINGS = new String[0];
    private HttpExchange exchange;
    private Map<String, List<String>> parameters;

    public SunHttpRequest(HttpExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String[] getParameterValues(String name) {
        if (parameters == null) {
            parameters = parseParameters();
        }
        List<String> result = parameters.get(name);
        return result == null ? EMPTY_STRINGS : result.toArray(new String[result.size()]);
    }

    private Map<String, List<String>> parseParameters() {
        Map<String, List<String>> parameters = new HashMap<String, List<String>>();
        for (String parameter : from(exchange.getRequestURI().getQuery())) {
            String[] parts = parameter.split("=");
            String name = parts[NAME_POS];
            String value = parts[VALUE_POS];
            List<String> values = valuesIn(parameters, name);
            parameters.put(name, values);
            values.add(value);
        }
        return parameters;
    }

    private List<String> valuesIn(Map<String, List<String>> parameters, String name) {
        return parameters.containsKey(name) ? parameters.get(name) : new ArrayList<String>();
    }

    private String[] from(String query) {
        return query == null ? EMPTY_STRINGS : query.split("&");
    }
}
