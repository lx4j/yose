package test.yose;

import org.json.JSONObject;
import org.junit.Test;
import test.support.HttpRequest;
import test.support.HttpResponse;
import test.support.JSON;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-5-27.
 */
public class PrimeFactorsChallenge extends YoseChallenge {

    @Test
    public void decomposite() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/primeFactors?number=16");

        assertThat(response.contentType(), equalTo("application/json"));
        JSONObject result = response.asJsonObject();
        assertThat(result.getInt("number"), equalTo(16));
        assertThat(JSON.toList(result.getJSONArray("decomposition")), equalTo(Arrays.<Object>asList(2, 2, 2, 2)));
    }


}
