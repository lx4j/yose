package test.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by L.x on 15-5-28.
 */
public class IO {
    static String toString(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        while (true) {
            int reads = in.read(buff);
            if (reads == -1) {
                break;
            }
            out.write(buff, 0, reads);
        }
        return out.toString();
    }
}
