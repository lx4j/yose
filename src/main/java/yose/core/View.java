package yose.core;

import java.io.IOException;

/**
 * Created by L.x on 15-5-28.
 */
public interface View {
    void render(HttpRequest request, HttpResponse response) throws IOException;
}
