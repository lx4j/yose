package yose.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

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

    private static Gson gson = new GsonBuilder().create();

    public static View json(Object object) {
        return json(gson.toJson(object));
    }

}
