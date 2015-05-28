package yose.sun;

import com.sun.net.httpserver.HttpExchange;
import yose.core.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by L.x on 15-5-28.
 */
public class SunHttpResponse implements HttpResponse {
    private String contentType;
    private int status;
    private ByteArrayOutputStream content = new ByteArrayOutputStream();
    private HttpExchange exchange;

    public SunHttpResponse(HttpExchange exchange) {
        this.exchange = exchange;
    }

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
        this.content.write(content.getBytes());
    }

    public void commit() throws IOException {
        exchange.getResponseHeaders().add("Content-Type", contentType);
        exchange.sendResponseHeaders(status, 0);
        exchange.getResponseBody().write(content.toByteArray());
        exchange.close();
    }
}
