package test.support;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import yose.utils.IO;
import yose.utils.JSON;

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

    public InputStream body() throws IOException {
        return statusCode() == 404 ? connection.getErrorStream() : connection.getInputStream();
    }

    public String asString() throws IOException {
        return IO.toString(body());
    }


    public <T> T as(Class<T> type) throws IOException {
        return JSON.parse(asString(), type);
    }

    public Element asHtml() throws SAXException, IOException {
        return HTMLDocument.toElement(body());
    }
}
