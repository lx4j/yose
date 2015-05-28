package yose.challenge.primefactors;

import yose.core.HttpRequest;
import yose.core.HttpResponse;
import yose.core.View;

import java.io.IOException;

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

            json(PrimeFactors.decompose(number)).render(request, response);
        } catch (NumberFormatException ex) {
            json(new Error(parameter, "not a number")).render(request, response);
        } catch (final IllegalArgumentException ex) {
            json(new Error(parameter, ex.getMessage())).render(request, response);
        }
    }

}
