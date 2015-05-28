package test.support;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by L.x on 15-5-28.
 */
public class JSON {
    public static <T> List<T> toList(JSONArray array) throws JSONException {
        List<T> result = new ArrayList<T>();
        for(int i=0;i<array.length();i++) {
            result.add((T) array.get(i));
        }
        return result;
    }
}
