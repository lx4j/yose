package test.yose;

import org.junit.Test;
import yose.View;
import yose.YoseViewResolver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static yose.View.html;

/**
 * Created by L.x on 15-5-27.
 */
public class YoseViewResolverTest {
    @Test
    public void viewResolved() throws Exception {
        YoseViewResolver resolver = new YoseViewResolver();
        View expected = html("yose");
        resolver.respond("/").with(expected);

        View resolved = resolver.resolve("/");
        assertThat(resolved, sameInstance(expected));
    }

    @Test
    public void return404_ifViewUnresolved() throws Exception {
        YoseViewResolver resolver = new YoseViewResolver();

        View resolved = resolver.resolve("/ping");
        assertThat(resolved.status, equalTo(404));
    }
}
