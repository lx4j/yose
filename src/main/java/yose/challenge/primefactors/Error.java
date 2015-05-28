package yose.challenge.primefactors;

/**
 * Created by L.x on 15-5-28.
 */
public class Error extends Result {
    public final String error;

    public Error(String number, String error) {
        super(number);
        this.error = error;
    }

    public static Result NaN(String number) {
        return new Error(number, "not a number");
    }

    public static Result numberTooBig(Integer number) {
        return new Error(String.valueOf(number), "too big number (>1e6)");
    }
}
