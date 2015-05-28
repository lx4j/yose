package test.yose.sun;

import com.sun.net.httpserver.HttpExchange;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;
import yose.core.HttpRequest;
import yose.sun.SunHttpRequest;

import java.net.URI;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class SunHttpRequestTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    @Mock
    private HttpExchange exchange;

    @Test
    public void returnEmptyArrayIfParametersNotPresent() throws Exception {
        HttpRequest request = new SunHttpRequest(exchange);
        context.checking(new Expectations() {{
            allowing(exchange).getRequestURI();
            will(returnValue(URI.create("http://localhost")));
        }});
        assertThat(request.getParameterValues("number"), equalTo(new String[0]));
    }

    @Test
    public void returnParametersInOrderIfParametersPresent() throws Exception {
        HttpRequest request = new SunHttpRequest(exchange);
        context.checking(new Expectations() {{
            allowing(exchange).getRequestURI();
            will(returnValue(URI.create("http://localhost?number=3&number=6")));
        }});
        assertThat(request.getParameterValues("number"), equalTo(new String[]{"3", "6"}));
    }

    @Test
    public void getParametersValuesByName() throws Exception {
        HttpRequest request = new SunHttpRequest(exchange);
        context.checking(new Expectations() {{
            allowing(exchange).getRequestURI();
            will(returnValue(URI.create("http://localhost?number=3&number=6&debug=true")));
        }});
        assertThat(request.getParameterValues("number"), equalTo(new String[]{"3", "6"}));
        assertThat(request.getParameterValues("debug"), equalTo(new String[]{"true"}));
    }

}