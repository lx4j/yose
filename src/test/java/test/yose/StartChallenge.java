package test.yose;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import yose.YoseServer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
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
        URL url = new URL("http://localhost:3000");
        URLConnection connection = url.openConnection();
        connection.setReadTimeout(1000);
        connection.connect();

        assertThat(connection.getContentType(), equalTo("text/html"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = connection.getInputStream();
        byte[] buff = new byte[1024];
        while (true) {
            int reads = in.read(buff);
            if (reads == -1) {
                break;
            }
            out.write(buff, 0, reads);
        }
        String body = out.toString();

        assertThat(body, containsString("Hello Yose"));
    }

    @Test
    public void pingRespondWithAliveJSONResponse() throws Exception {
        URL url = new URL("http://localhost:3000/ping");
        URLConnection connection = url.openConnection();
        connection.setReadTimeout(1000);
        connection.connect();

        assertThat(connection.getContentType(), equalTo("application/json"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = connection.getInputStream();
        byte[] buff = new byte[1024];
        while (true) {
            int reads = in.read(buff);
            if (reads == -1) {
                break;
            }
            out.write(buff, 0, reads);
        }
        String body = out.toString();

        assertThat(body, equalTo("{\"alive\":true}"));
    }
}
