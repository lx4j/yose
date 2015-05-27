package test.yose;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.support.HttpRequest;
import test.support.HttpResponse;
import yose.YoseServer;
import yose.YoseViewResolver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static yose.View.html;
import static yose.View.json;

/**
 * Created by L.x on 15-5-27.
 */
public class YoseServerTest extends YoseChallenge {
    @Test
    public void return404WhenFileNotFound() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/unknown");

        assertThat(response.contentType(), equalTo("text/html"));
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.body(), equalTo("File Not Found"));
    }

}
