package yose.challenge.primefactors;

/**
 * Created by L.x on 15-5-28.
 */
public class Error implements Result {
    public final String error;
    public final String number;

    public Error(String number, String error) {
        this.number = number;
        this.error = error;
    }

    public static Result NaN(String number) {
        return new Error(number, "not a number");
    }

    public static Result numberTooBig(Numeral number) {
        return new Error(String.valueOf(number), "too big number (>1e6)");
    }

    public static Result numberLessThan1(Numeral number) {
        return new Error(String.valueOf(number), "not an integer > 1");
    }
}
