package yose.challenge.primefactors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by L.x on 15-5-28.
 */
public class PrimeFactors {
    public static Object decompose(String... numbers) {
        List<Result> compositions = new ArrayList<Result>();
        for (String parameter : numbers) {
            Result result = decompose(parameter);
            compositions.add(result);
        }
        return compositions.size() == 1 ? compositions.get(0) : compositions;
    }


    public static Result decompose(String number) {
        try {
            return decompose(Numeral.parse(number));
        } catch (NumberFormatException ex) {
            return Error.NaN(number);
        }
    }

    public static Result decompose(final Numeral numeral) {
        if (numeral.lessThan(1)) {
            return Error.numberLessThan1(numeral);
        }
        if (numeral.largeThan(1000000)) {
            return Error.numberTooBig(numeral);
        }
        return new Decomposition(numeral, transform(of(numeral.intValue()), as(numeral)));
    }

    private static Transformation<Numeral> as(final Numeral numeral) {
        return new Transformation<Numeral>() {
            @Override
            public Numeral transform(Integer number) {
                return numeral.of(number);
            }
        };
    }

    private static interface Transformation<T> {
        T transform(Integer number);
    }

    private static <T>  List<T> transform(List<Integer> numerals, Transformation<T> transformer) {
        List<T> result = new ArrayList<T>();
        for (Integer numeral : numerals) {
            result.add(transformer.transform(numeral));
        }
        return result;
    }

    public static List<Integer> of(int number) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int candidate = 2; number > 1; candidate++) {
            for (; number % candidate == 0; number /= candidate) {
                result.add(candidate);
            }
        }
        return result;
    }
}
