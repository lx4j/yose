package yose.challenge.primefactors;

import yose.core.HttpRequest;
import yose.core.HttpResponse;
import yose.core.View;

import java.io.IOException;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;

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
            if (number > 1000000) {
                throw new IllegalArgumentException("too big number (>1e6)");
            }
            HashMap<String, Object> result = new HashMap<String, Object>() {{
                put("number", number);
                put("decomposition", PrimeFactors.decompose(number));
            }};
            json(result).render(request, response);
        } catch (NumberFormatException ex) {
            HashMap<String, Object> result = new HashMap<String, Object>() {{
                put("number", parameter);
                put("error", "not a number");
            }};
            json(result).render(request, response);
        }catch (final IllegalArgumentException ex) {
            HashMap<String, Object> result = new HashMap<String, Object>() {{
                put("number", parameter);
                put("error", ex.getMessage());
            }};
            json(result).render(request, response);
        }
    }

}
