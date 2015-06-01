package yose.utils;

import com.google.gson.*;
import yose.challenge.primefactors.ArabicNumber;
import yose.challenge.primefactors.Numeral;
import yose.challenge.primefactors.RomanNumber;

import java.lang.reflect.Type;

/**
 * Created by L.x on 15-5-28.
 */
public class JSON {
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(ArabicNumber.class, toInteger())
            .registerTypeAdapter(RomanNumber.class, toRomanNumber())
            .registerTypeAdapter(Numeral.class, stringToNumeral())
            .create();

    private static JsonDeserializer<Numeral> stringToNumeral() {
        return new JsonDeserializer<Numeral>() {
            @Override
            public Numeral deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return Numeral.parse(json.getAsString());
            }
        };
    }

    private static JsonSerializer<Numeral> toRomanNumber() {
        return new JsonSerializer<Numeral>() {
            @Override
            public JsonElement serialize(Numeral src, Type typeOfSrc, JsonSerializationContext context) {
                return context.serialize(src.toString(), String.class);
            }
        };
    }

    private static JsonSerializer<Numeral> toInteger() {
        return new JsonSerializer<Numeral>() {
            @Override
            public JsonElement serialize(Numeral src, Type typeOfSrc, JsonSerializationContext context) {
                return context.serialize(src.intValue(), Integer.TYPE);
            }
        };
    }

    public static <T> T parse(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }


    public static String stringify(Object object) {
        return gson.toJson(object);
    }
}
