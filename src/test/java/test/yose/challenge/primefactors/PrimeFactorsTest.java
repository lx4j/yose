package test.yose.challenge.primefactors;

import org.hamcrest.Matcher;
import org.junit.Test;
import yose.challenge.primefactors.PrimeFactors;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-5-28.
 */
public class PrimeFactorsTest {
    @Test
    public void noFactors() throws Exception {
        assertThat(PrimeFactors.of(1), returnsEmptyArray());
    }

    @Test
    public void primeNumbers() throws Exception {
        assertThat(PrimeFactors.of(2), returns(2));
        assertThat(PrimeFactors.of(3), returns(3));
    }

    @Test
    public void sameFactors() throws Exception {
        assertThat(PrimeFactors.of(4), returns(2, 2));
        assertThat(PrimeFactors.of(8), returns(2, 2, 2));
        assertThat(PrimeFactors.of(9), returns(3, 3));
    }

    @Test
    public void differentFactors() throws Exception {
        assertThat(PrimeFactors.of(6), returns(2, 3));
        assertThat(PrimeFactors.of(30), returns(2, 3, 5));
    }

    private Matcher<List<Integer>> returns(Integer... values) {
        return equalTo(Arrays.asList(values));
    }

    private Matcher<List<Integer>> returnsEmptyArray() {
        return returns();
    }
}
