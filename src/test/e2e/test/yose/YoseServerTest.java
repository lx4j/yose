package test.yose;

import org.junit.Test;
import test.support.HttpRequest;
import test.support.HttpResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-5-27.
 */
public class YoseServerTest extends YoseChallenge {
    @Test
    public void return404WhenFileNotFound() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/unknown");

        assertThat(response.contentType(), equalTo("text/html"));
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.asString(), equalTo("File Not Found"));
    }

}
