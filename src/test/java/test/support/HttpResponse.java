package test.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by L.x on 15-5-27.
 */
public class HttpResponse {
    public HttpURLConnection connection;

    public HttpResponse(HttpURLConnection connection) {
        this.connection = connection;
    }

    public String contentType() {
        return connection.getContentType();
    }

    public int statusCode() throws IOException {
        return connection.getResponseCode();
    }

    public String body() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = content();
        byte[] buff = new byte[1024];
        while (true) {
            int reads = in.read(buff);
            if (reads == -1) {
                break;
            }
            out.write(buff, 0, reads);
        }
        return out.toString();
    }

    private InputStream content() throws IOException {
        return statusCode() == 404 ? connection.getErrorStream() : connection.getInputStream();
    }
}
