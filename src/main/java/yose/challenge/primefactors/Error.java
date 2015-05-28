package yose.challenge.primefactors;

import java.util.List;

/**
 * Created by L.x on 15-5-28.
 */
public class Error {

    public final String number;
    public final String error;

    public Error(String number, String error) {
        this.number = number;
        this.error = error;
    }
}
