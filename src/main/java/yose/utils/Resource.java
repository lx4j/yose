package yose.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by L.x on 15-5-28.
 */
public class Resource {
    public static String from(String classpath) throws IOException {
        InputStream resource = Resource.class.getResourceAsStream(classpath);
        try {
            return IO.toString(resource);
        } finally {
            IO.close(resource);
        }
    }

}
