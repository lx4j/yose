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
        List<Result> compositions = new ArrayList<Result>();
        for (String parameter : request.getParameterValues("number")) {
            Result result = PrimeFactors.decompose(parameter);
            compositions.add(result);
        }
        json(compositions.size() == 1 ? compositions.get(0) : compositions).render(request, response);
    }

}
