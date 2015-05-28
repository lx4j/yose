package test.yose;

import org.junit.After;
import org.junit.Before;
import yose.sun.SunHttpServer;
import yose.core.YoseServer;
import yose.YoseViewResolver;

/**
 * Created by L.x on 15-5-28.
 */
abstract public class YoseChallenge {
    private YoseServer server;

    @Before
    public void setUp() throws Exception {
        server = new SunHttpServer(3000);
        server.setViewResolver(new YoseViewResolver());
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

}
