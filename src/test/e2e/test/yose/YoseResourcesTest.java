package test.yose;

import org.junit.Test;
import test.support.HttpRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-5-28.
 */
public class YoseResourcesTest extends YoseChallenge {
    @Test
    public void javascript() throws Exception {
        assertThat(HttpRequest.get("http://localhost:3000/primeFactors/primefactors.js").statusCode(), equalTo(200));
    }
}
