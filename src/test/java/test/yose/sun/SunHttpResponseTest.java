package test.yose.sun;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import yose.sun.SunHttpResponse;

import java.io.ByteArrayOutputStream;

/**
 * Created by L.x on 15-5-28.
 */
public class SunHttpResponseTest {
    private final Headers headers = new Headers();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    @Mock
    private HttpExchange exchange;

    @Before
    public void setUp() throws Exception {
        context.checking(new Expectations() {{
            allowing(exchange).getResponseHeaders();
            will(returnValue(headers));
            allowing(exchange).getResponseBody();
            will(returnValue(out));
        }});
    }

    @Test
    public void respondWhenCommit() throws Exception {
        SunHttpResponse response = new SunHttpResponse(exchange);
        response.setContentType("text/html");
        response.setStatus(200);
        response.write("Hello Yose");

        context.checking(new Expectations() {{
            oneOf(exchange).sendResponseHeaders(with(200), with(any(long.class)));
            oneOf(exchange).close();
        }});
        response.commit();

    }
}
