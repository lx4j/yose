package yose;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by L.x on 15-5-27.
 */
public class ViewMappingResolver implements ViewResolver {
    private Map<String, View> views = new HashMap<String, View>();

    public ViewRegistry respond(final String path) {
        return new ViewRegistry() {
            public void with(View view) {
                views.put(path, view);
            }
        };
    }

    @Override
    public View resolve(String path) {
        View view = views.get(path);
        if (view == null) {
            return View.fileNotFound();
        }
        return view;
    }

}
