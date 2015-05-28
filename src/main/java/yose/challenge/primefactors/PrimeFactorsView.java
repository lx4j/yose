package yose.challenge.primefactors;

import yose.core.HttpRequest;
import yose.core.HttpResponse;
import yose.core.View;

import java.io.IOException;
import java.util.List;

import static yose.core.StaticView.json;

/**
 * Created by L.x on 15-5-28.
 */
public class PrimeFactorsView implements View {
    @Override
    public void render(final HttpRequest request, HttpResponse response) throws IOException {
        String[] numbers = request.getParameterValues("number");
        json(PrimeFactors.decompose(numbers)).render(request, response);
    }

}
