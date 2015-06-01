package yose.challenge.primefactors;

/**
 * Created by L.x on 15-6-1.
 */
public abstract class Numeral {
    public static Numeral parse(String number) {
        try {
            return ArabicNumber.parse(number);
        } catch (NumberFormatException ex) {
            return RomanNumber.parse(number);
        }
    }

    private int value;

    public Numeral(int value) {
        this.value = value;
    }

    public boolean equals(Object o) {
        if (!getClass().isInstance(o)) {
            return false;
        }
        Numeral that = (Numeral) o;
        return intValue() == that.intValue();
    }

    public int intValue() {
        return value;
    }

    public boolean lessThan(int number) {
        return intValue() < number;
    }

    public boolean largeThan(int number) {
        return intValue() > number;
    }

    public abstract Numeral of(int number);
}
