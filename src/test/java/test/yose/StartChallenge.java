package test.yose;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.support.HttpRequest;
import test.support.HttpResponse;
import yose.YoseServer;

import java.net.URLConnection;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-5-27.
 */
public class StartChallenge {
    private YoseServer server;

    @Before
    public void setUp() throws Exception {
        server = new YoseServer(3000);
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void serveAHtmlPageContainingTextHelloYoseInTheRootOfYoseServer() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000");

        assertThat(response.contentType(), equalTo("text/html"));
        assertThat(response.body(), containsString("Hello Yose"));
    }

    @Test
    public void pingRespondWithAliveJSONResponse() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/ping");

        assertThat(response.contentType(), equalTo("application/json"));
        assertThat(response.body(), equalTo("{\"alive\":true}"));
    }

}
