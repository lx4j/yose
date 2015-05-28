package yose;

import yose.challenge.primefactors.PrimeFactorsView;
import yose.core.*;

import java.util.Collections;

import static yose.core.StaticView.html;
import static yose.core.StaticView.json;

/**
 * Created by L.x on 15-5-28.
 */
public class YoseViewResolver extends ViewMappingResolver {
    public YoseViewResolver() {
        respond("/").with(html("Hello Yose<a id='repository-link' href='https://github.com/lx4j/yose'>github</a>"));
        respond("/ping").with(json(Collections.singletonMap("alive", true)));
        respond("/primeFactors").with(new PrimeFactorsView());
    }


}
