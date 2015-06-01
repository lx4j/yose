package yose.challenge.primefactors;

/**
 * Created by L.x on 15-6-1.
 */
public class RomanNumber extends Numeral {

    public RomanNumber(int value) {
        super(value);
    }

    public static Numeral valueOf(int value) {
        return new RomanNumber(value);
    }

    @Override
    public Numeral of(int number) {
        return valueOf(number);
    }

    private static enum Convention {
        M(1000), CM(900), D(500), CD(400), C(100),
        XC(90), L(50), XL(40), X(10),
        IX(9), V(5), IV(4), I(1);

        private int value;

        Convention(int value) {
            this.value = value;
        }
    }

    public static Numeral parse(String number) {
        int value = 0;
        int pos = 0;
        for (Convention candidate : Convention.values()) {
            while (number.startsWith(candidate.name(), pos)) {
                value += candidate.value;
                pos += candidate.name().length();
            }
        }
        if (pos != number.length()) {
            throw new NumberFormatException("Invalid Number:" + number);
        }
        return new RomanNumber(value);
    }

    @Override
    public String toString() {
        return toString(intValue());
    }

    private String toString(int value) {
        for (Convention candidate : Convention.values()) {
            if (value >= candidate.value) {
                return candidate.name() + toString(value - candidate.value);
            }
        }
        return "";
    }
}
