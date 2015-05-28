package yose.challenge.primefactors;

import yose.core.HttpRequest;
import yose.core.HttpResponse;
import yose.core.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static yose.core.StaticView.json;

/**
 * Created by L.x on 15-5-28.
 */
public class PrimeFactorsView implements View {
    @Override
    public void render(final HttpRequest request, HttpResponse response) throws IOException {
        String[] parameters = request.getParameterValues("number");
        if (parameters.length == 1) {
            final String parameter = parameters[0];
            Result result = PrimeFactors.decompose(parameter);
            json(result).render(request, response);
        } else {
            List<Result> compositions = new ArrayList<Result>();
            for (String parameter : parameters) {
                Result result = PrimeFactors.decompose(parameter);
                compositions.add(result);
            }
            json(compositions).render(request, response);
        }
    }

}
