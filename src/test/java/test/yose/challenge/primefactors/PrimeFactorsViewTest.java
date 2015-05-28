package test.yose.challenge.primefactors;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import test.support.JSON;
import yose.challenge.primefactors.PrimeFactorsView;
import yose.core.HttpRequest;
import yose.core.HttpResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PrimeFactorsViewTest {

    private final MockHttpResponse response = new MockHttpResponse();
    private final PrimeFactorsView view = new PrimeFactorsView();
    private final MockHttpRequest request = new MockHttpRequest();

    @Test
    public void render() throws Exception {
        request.with("number", "16");

        view.render(request, response);

        JSONObject result = response.asJsonObject();
        assertThat((Integer) result.get("number"), equalTo(16));
        assertThat(JSON.toList(result.getJSONArray("decomposition")), equalTo(Arrays.<Object>asList(2, 2, 2, 2)));
    }

    @Test
    public void reportErrorWithInvalidNumber() throws Exception {
        request.with("number", "hello");

        view.render(request, response);

        JSONObject result = response.asJsonObject();
        assertThat(result.getString("number"), equalTo("hello"));
        assertThat(result.getString("error"), equalTo("not a number"));
    }

    private static class MockHttpRequest implements HttpRequest {
        private Map<String, String[]> parameters = new HashMap<String, String[]>();

        @Override
        public String[] getParameterValues(String name) {
            return parameters.get(name);
        }

        public void with(String name, String... values) {
            parameters.put(name, values);
        }
    }


    private class MockHttpResponse implements HttpResponse {
        private String contentType;
        private int status;
        private StringBuilder content = new StringBuilder();

        @Override
        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        @Override
        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public void write(String content) throws IOException {
            this.content.append(content);
        }

        public JSONObject asJsonObject() throws JSONException {
            return new JSONObject(new JSONTokener(content.toString()));
        }
    }
}