package test.support;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

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

    public JSONObject asJsonObject() throws IOException, JSONException {
        return new JSONObject(new JSONTokener(asString()));
    }

    public Element asHtml() throws SAXException, IOException {
        return HTMLDocument.toElement(body());
    }
}