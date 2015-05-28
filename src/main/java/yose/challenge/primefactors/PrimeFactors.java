package yose.challenge.primefactors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by L.x on 15-5-28.
 */
public class PrimeFactors {
    public static List<Integer> of(int number) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int candidate = 2; number > 1; candidate++) {
            for (; number % candidate == 0; number /= candidate) {
                result.add(candidate);
            }
        }
        return result;
    }


    public static Result decompose(String number) {
        try {
            return decompose(Integer.parseInt(number));
        } catch (NumberFormatException ex) {
            return Error.NaN(number);
        }
    }

    public static Result decompose(Integer number) {
        if (number > 1000000) {
            return Error.numberTooBig(number);
        }
        return new Decomposition(String.valueOf((int) number), PrimeFactors.of(number));
    }
}
