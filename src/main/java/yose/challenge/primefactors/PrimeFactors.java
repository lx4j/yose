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

    public static Decomposition decompose(String numberAsString) {
        return decompose(Integer.parseInt(numberAsString));
    }

    public static Decomposition decompose(int number) {
        return new Decomposition(String.valueOf(number), PrimeFactors.of(number));
    }
}
