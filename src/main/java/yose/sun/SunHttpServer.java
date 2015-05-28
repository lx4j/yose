package yose.sun;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import yose.core.HttpRequest;
import yose.core.ViewResolver;
import yose.core.YoseServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by L.x on 15-5-27.
 */
public class SunHttpServer implements YoseServer {

    private HttpServer server;
    private ViewResolver viewResolver;

    public SunHttpServer(int port) throws IOException {
        server = HttpServer.create(listenOn(port), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                SunHttpResponse response = new SunHttpResponse(exchange);
                HttpRequest request = new SunHttpRequest(exchange);
                viewResolver.resolveView(exchange.getRequestURI().getPath()).render(request,response);
                response.commit();
            }
        });
    }

    private InetSocketAddress listenOn(int serverPort) {
        return new InetSocketAddress(serverPort);
    }

    @Override
    public void start() {
        server.start();
    }

    @Override
    public void stop() {
        server.stop(0);
    }

    @Override
    public void setViewResolver(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }
}
