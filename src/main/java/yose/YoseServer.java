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
    private ViewResolver viewResolver;

    public YoseServer(int port) throws IOException {
        server = HttpServer.create(listenOn(port), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                View view = viewResolver.resolve(exchange.getRequestURI().getPath());
                respond(view, exchange);
            }
        });
    }

    private void respond(View view, HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", view.contentType);
        exchange.sendResponseHeaders(view.status, 0);
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

    public void setViewResolver(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }
}
