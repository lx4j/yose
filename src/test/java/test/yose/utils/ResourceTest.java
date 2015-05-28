package test.yose.utils;

import org.junit.Test;
import yose.utils.Resource;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class ResourceTest {
    @Test
    public void from() throws Exception {
        String data = Resource.from("/test/index.data");
        assertThat(data,equalTo("Yose"));
    }
}