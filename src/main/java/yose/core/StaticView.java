package yose.core;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by L.x on 15-5-27.
 */
public class StaticView implements View {
    private static final int STATUS_OK = 200;
    private static final int STATUS_FILE_NOT_FOUND = 404;
    public String contentType;
    public String body;
    public int status;

    public StaticView(String contentType, int status, String body) {
        this.contentType = contentType;
        this.status = status;
        this.body = body;
    }

    public static View html(String body) {
        return new StaticView("text/html", STATUS_OK, body);
    }

    public static View json(String body) {
        return new StaticView("application/json", STATUS_OK, body);
    }

    public static StaticView fileNotFound() {
        return new StaticView("text/html", STATUS_FILE_NOT_FOUND, "File Not Found");
    }

    @Override
    public void render(HttpRequest request, HttpResponse response) throws IOException {
        response.setContentType(contentType);
        response.setStatus(status);
        response.write(body);
    }

    public static View json(Map map) {
        return json(new JSONObject(map));
    }

    public static View json(Object object) {
        return json(new JSONObject(object, true));
    }

    public static View json(JSONObject object) {
        return json(object.toString());
    }
}
