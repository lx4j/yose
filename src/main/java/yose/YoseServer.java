package yose;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by L.x on 15-5-27.
 */
public class YoseServer {

    private HttpServer server;
    private Map<String, View> views = new HashMap<String, View>() {{
        put("/", new View("text/html", "Hello Yose"));
        put("/ping", new View("application/json", "{\"alive\":true}"));
    }};

    public YoseServer(int port) throws IOException {
        server = HttpServer.create(listenOn(port), 0);
        for (String path : views.keySet()) {
            final View view = views.get(path);
            server.createContext(path, new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    respond(view, exchange);
                }
            });
        }
    }

    private void respond(View view, HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", view.contentType);
        exchange.sendResponseHeaders(200, 0);
        exchange.getResponseBody().write(view.body.getBytes());
        exchange.close();
    }

    private InetSocketAddress listenOn(int serverPort) {
        return new InetSocketAddress(serverPort);
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}
