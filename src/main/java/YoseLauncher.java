import yose.YoseServer;
import yose.YoseViewResolver;

import java.io.IOException;

/**
 * Created by L.x on 15-5-27.
 */
public class YoseLauncher {
    public static void main(String[] args) throws IOException {
        YoseServer server = new YoseServer(3000);
        server.setViewResolver(new YoseViewResolver());
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
