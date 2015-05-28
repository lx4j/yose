package test.yose.challenge.primefactors;

import org.hamcrest.Matcher;
import org.junit.Test;
import yose.challenge.primefactors.PrimeFactors;
import yose.challenge.primefactors.PrimeFactorsView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-5-28.
 */
public class PrimeFactorsTest {
    @Test
    public void decompose() throws Exception {
        assertThat(PrimeFactors.decompose(16), returns(2, 2, 2, 2));
    }

    @Test
    public void noFactors() throws Exception {
        assertThat(PrimeFactors.decompose(1), returnsEmptyArray());
    }

    @Test
    public void primeNumbers() throws Exception {
        assertThat(PrimeFactors.decompose(2), returns(2));
        assertThat(PrimeFactors.decompose(3), returns(3));
    }

    @Test
    public void sameFactors() throws Exception {
        assertThat(PrimeFactors.decompose(4), returns(2, 2));
        assertThat(PrimeFactors.decompose(8), returns(2, 2, 2));
        assertThat(PrimeFactors.decompose(9), returns(3, 3));
    }

    @Test
    public void differentFactors() throws Exception {
        assertThat(PrimeFactors.decompose(6), returns(2, 3));
        assertThat(PrimeFactors.decompose(30), returns(2, 3, 5));
    }

    private Matcher<List<Integer>> returns(Integer... values) {
        return equalTo(Arrays.asList(values));
    }

    private Matcher<List<Integer>> returnsEmptyArray() {
        return returns();
    }
}
