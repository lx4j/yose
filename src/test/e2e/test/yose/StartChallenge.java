package test.yose;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.w3c.dom.Element;
import test.support.HttpRequest;
import test.support.HttpResponse;

import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testinfected.hamcrest.dom.DomMatchers.hasAttribute;
import static org.testinfected.hamcrest.dom.DomMatchers.hasText;
import static org.testinfected.hamcrest.dom.DomMatchers.hasUniqueSelector;

/**
 * Created by L.x on 15-5-27.
 */
public class StartChallenge extends YoseChallenge {

    @Test
    public void serveAHtmlPageContainingTextHelloYoseInTheRootOfYoseServer() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000");

        assertThat(response.contentType(), equalTo("text/html"));
        assertThat(response.asString(), containsString("Hello Yose"));
    }

    @Test
    public void pingRespondWithAliveJSONResponse() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/ping");

        assertThat(response.contentType(), equalTo("application/json"));
        assertThat((Boolean) response.asJsonObject(Map.class).get("alive"), equalTo(true));
    }

    @Test
    public void serveAHtmlPageContainingARepositoryLinkElement() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000");

        assertThat(response.asHtml(), hasUniqueSelector("a#repository-link", targeting(hasUniqueSelector("#readme", hasText(containsString("YoseTheGame"))))));
    }

    private Matcher<Element> targeting(final Matcher<Element> matcher) {
        return hasAttribute("href", new DiagnosingMatcher<String>() {
            @Override
            protected boolean matches(Object item, Description mismatch) {
                String target = String.valueOf(item);
                try {
                    Element dom = HttpRequest.get(target).asHtml();
                    boolean result = matcher.matches(dom);
                    if (!result) {
                        matcher.describeMismatch(dom, mismatch);
                        return false;
                    }
                    return true;
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("targeting a page ").appendDescriptionOf(matcher);
            }
        });
    }

}
