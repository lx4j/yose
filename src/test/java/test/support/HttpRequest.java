package test.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by L.x on 15-5-27.
 */
public class HttpRequest {

    public static HttpResponse get(String uri) throws IOException {
        return new HttpResponse(connect(uri));
    }

    private static URLConnection connect(String uri) throws IOException {
        URL url = new URL(uri);
        URLConnection connection = url.openConnection();
        connection.setReadTimeout(1000);
        connection.connect();
        return connection;
    }

}
