package yose;

import yose.challenge.primefactors.PrimeFactorsView;
import yose.core.*;

import java.io.IOException;
import java.util.Collections;

import static yose.core.StaticView.html;
import static yose.core.StaticView.json;
import static yose.utils.Resource.from;

/**
 * Created by L.x on 15-5-28.
 */
public class YoseViewResolver extends ViewMappingResolver {
    public YoseViewResolver() throws IOException {
        respond("/").with(html(from("/assets/index.html")));
        respond("/ping").with(json(Collections.singletonMap("alive", true)));
        respond("/primeFactors").with(new PrimeFactorsView());
        respond("/primeFactors/ui").with(html(from("/assets/primefactors/index.html")));
    }


}
