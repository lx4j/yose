package yose.challenge.primefactors;

/**
 * Created by L.x on 15-6-1.
 */
public class ArabicNumber extends Numeral {

    public ArabicNumber(int value) {
        super(value);
    }

    public static Numeral parse(String number) {
        return new ArabicNumber(Integer.parseInt(number));
    }

    @Override
    public String toString() {
        return String.valueOf(intValue());
    }

    public static Numeral valueOf(int value) {
        return new ArabicNumber(value);
    }

    public Numeral of(int number) {
        return valueOf(number);
    }
}
