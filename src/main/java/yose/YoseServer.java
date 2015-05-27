package yose;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by L.x on 15-5-27.
 */
public class YoseServer {

    private HttpServer server;

    public YoseServer(int port) throws IOException {
        server = HttpServer.create(listenOn(port), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                exchange.getResponseHeaders().add("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, 0);
                exchange.getResponseBody().write("Hello Yose".getBytes());
                exchange.close();
            }
        });
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
