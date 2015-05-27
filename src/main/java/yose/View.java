package yose;

/**
 * Created by L.x on 15-5-27.
 */
public class View {
    private static final int STATUS_OK = 200;
    private static final int STATUS_FILE_NOT_FOUND = 404;
    public String contentType;
    public String body;
    public int status;

    public View(String contentType, int status, String body) {
        this.contentType = contentType;
        this.status = status;
        this.body = body;
    }

    public static View html(String body) {
        return new View("text/html", STATUS_OK, body);
    }

    public static View json(String body) {
        return new View("application/json", STATUS_OK, body);
    }

    public static View fileNotFound() {
        return new View("text/html", STATUS_FILE_NOT_FOUND, "File Not Found");
    }
}
