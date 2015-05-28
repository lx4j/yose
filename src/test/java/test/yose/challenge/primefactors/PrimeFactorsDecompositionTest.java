package test.yose.challenge.primefactors;

import org.junit.Test;
import yose.challenge.primefactors.Decomposition;
import yose.challenge.primefactors.PrimeFactors;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-5-28.
 */
public class PrimeFactorsDecompositionTest {
    @Test
    public void decompose() throws Exception {
        Decomposition decomposition = PrimeFactors.decompose("16");

        assertThat(decomposition.number, equalTo("16"));
        assertThat(decomposition.decomposition, equalTo(Arrays.asList(2, 2, 2, 2)));
    }
}
