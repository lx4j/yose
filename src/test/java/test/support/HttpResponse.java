package test.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/**
 * Created by L.x on 15-5-27.
 */
public class HttpResponse {
    public URLConnection connection;

    public HttpResponse(URLConnection connection) {
        this.connection = connection;
    }

    public String contentType() {
        return connection.getContentType();
    }

    public String body() throws IOException {
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
        return out.toString();
    }
}
