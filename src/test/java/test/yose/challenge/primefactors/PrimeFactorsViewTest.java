package test.yose.challenge.primefactors;

import org.junit.Test;
import yose.utils.JSON;
import yose.challenge.primefactors.Decomposition;
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

        Decomposition result = response.asJson(Decomposition.class);
        assertThat(result.number, equalTo("16"));
        assertThat(result.decomposition, equalTo(Arrays.asList(2, 2, 2, 2)));
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


    private static class MockHttpResponse implements HttpResponse {
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

        public <T> T asJson(Class<T> type) throws IOException {
            return JSON.parse(content.toString(), type);
        }

    }
}