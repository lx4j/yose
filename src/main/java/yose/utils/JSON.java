package yose.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by L.x on 15-5-28.
 */
public class JSON {
    private static Gson gson = new GsonBuilder().create();

    public static <T> T parse(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }


    public static String stringify(Object object) {
        return gson.toJson(object);
    }
}
