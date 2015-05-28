package test.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by L.x on 15-5-27.
 */
public class HttpRequest {

    private static final int TIMEOUT_MILLIS = 5000;

    public static HttpResponse get(String uri) throws IOException {
        return new HttpResponse(connect(uri));
    }

    private static HttpURLConnection connect(String uri) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(TIMEOUT_MILLIS);
        connection.connect();
        return connection;
    }

}
