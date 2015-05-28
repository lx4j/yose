package yose.challenge.primefactors;

import yose.core.HttpRequest;
import yose.core.HttpResponse;
import yose.core.View;

import java.io.IOException;
import java.util.HashMap;

import static yose.core.StaticView.json;

/**
 * Created by L.x on 15-5-28.
 */
public class PrimeFactorsView implements View {
    @Override
    public void render(final HttpRequest request, HttpResponse response) throws IOException {
        final String parameter = request.getParameterValues("number")[0];
        try {
            final Integer number = Integer.parseInt(parameter);
            HashMap<String, Object> result = new HashMap<String, Object>() {{
                put("number", number);
                put("decomposition", PrimeFactors.decompose(number));
            }};
            json(result).render(request, response);
        } catch (NumberFormatException e) {
            HashMap<String, Object> result = new HashMap<String, Object>() {{
                put("number", parameter);
                put("error", "not a number");
            }};
            json(result).render(request, response);
        }
    }

}
