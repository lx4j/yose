package test.yose;

import org.junit.Test;
import yose.core.StaticView;
import yose.core.View;
import yose.core.ViewMappingResolver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static yose.core.StaticView.html;

/**
 * Created by L.x on 15-5-27.
 */
public class ViewMappingResolverTest {
    @Test
    public void viewResolved() throws Exception {
        ViewMappingResolver resolver = new ViewMappingResolver();
        View expected = html("yose");
        resolver.respond("/").with(expected);

        View resolved = resolver.resolveView("/");
        assertThat(resolved, sameInstance(expected));
    }

    @Test
    public void return404_ifViewUnresolved() throws Exception {
        ViewMappingResolver resolver = new ViewMappingResolver();

        StaticView resolved = (StaticView) resolver.resolveView("/ping");
        assertThat(resolved.status, equalTo(404));
    }
}
