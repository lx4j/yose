import yose.YoseViewResolver;
import yose.View;
import yose.YoseServer;

import java.io.IOException;

import static yose.View.html;

/**
 * Created by L.x on 15-5-27.
 */
public class YoseLauncher {
    public static void main(String[] args) throws IOException {
        YoseServer server = new YoseServer(3000);
        server.setViewResolver(new YoseViewResolver() {{
            respond("/").with(html("Hello Yose"));
            respond("/ping").with(View.json("{\"alive\":true}"));
        }});
        stopServerWhenShutdown(server);
        server.start();
    }

    private static void stopServerWhenShutdown(final YoseServer server) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                server.stop();
            }
        });
    }
}
