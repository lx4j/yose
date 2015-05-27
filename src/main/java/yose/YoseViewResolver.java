package yose;

import static yose.View.html;
import static yose.View.json;

/**
 * Created by L.x on 15-5-28.
 */
public class YoseViewResolver extends ViewMappingResolver {
    public YoseViewResolver() {
        respond("/").with(html("Hello Yose<a id='repository-link' href='https://github.com/lx4j/yose'>github</a>"));
        respond("/ping").with(json("{\"alive\":true}"));
    }
}
