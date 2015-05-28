package yose.core;

import java.io.IOException;

/**
 * Created by L.x on 15-5-28.
 */
public interface HttpResponse {
    void setContentType(String contentType);

    void setStatus(int status);

    void write(String content) throws IOException;
}
