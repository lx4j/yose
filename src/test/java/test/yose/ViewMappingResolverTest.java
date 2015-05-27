package test.yose;

import org.junit.Test;
import yose.View;
import yose.ViewMappingResolver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static yose.View.html;

/**
 * Created by L.x on 15-5-27.
 */
public class ViewMappingResolverTest {
    @Test
    public void viewResolved() throws Exception {
        ViewMappingResolver resolver = new ViewMappingResolver();
        View expected = html("yose");
        resolver.respond("/").with(expected);

        View resolved = resolver.resolve("/");
        assertThat(resolved, sameInstance(expected));
    }

    @Test
    public void return404_ifViewUnresolved() throws Exception {
        ViewMappingResolver resolver = new ViewMappingResolver();

        View resolved = resolver.resolve("/ping");
        assertThat(resolved.status, equalTo(404));
    }
}
