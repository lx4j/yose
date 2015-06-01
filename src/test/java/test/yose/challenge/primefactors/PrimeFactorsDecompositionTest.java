package test.yose.challenge.primefactors;

import org.junit.Test;
import yose.challenge.primefactors.*;
import yose.challenge.primefactors.Error;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-5-28.
 */
public class PrimeFactorsDecompositionTest {
    @Test
    public void decomposeArabicNumber() throws Exception {
        Decomposition decomposition = (Decomposition) PrimeFactors.decompose("16");

        assertThat(decomposition.number, equalTo(ArabicNumber.valueOf(16)));
        assertThat(decomposition.decomposition, equalTo(Arrays.asList(
                ArabicNumber.valueOf(2),
                ArabicNumber.valueOf(2),
                ArabicNumber.valueOf(2),
                ArabicNumber.valueOf(2)
        )));
    }

    @Test
    public void decomposeRomanNumber() throws Exception {
        Decomposition decomposition = (Decomposition) PrimeFactors.decompose("CCC");

        assertThat(decomposition.number, equalTo(RomanNumber.valueOf(300)));
        assertThat(decomposition.decomposition, equalTo(Arrays.asList(
                RomanNumber.parse("II"),
                RomanNumber.parse("II"),
                RomanNumber.parse("III"),
                RomanNumber.parse("V"),
                RomanNumber.parse("V")
        )));
    }

    @Test
    public void decomposeWithInvalidNumber() throws Exception {

        Error decomposition = (Error) PrimeFactors.decompose("bad");

        assertThat(decomposition.number, equalTo("bad"));
        assertThat(decomposition.error, equalTo("not a number"));
    }

    @Test
    public void decomposeWithNumberLargeThan1000001() throws Exception {

        Error decomposition = (Error) PrimeFactors.decompose("1000001");

        assertThat(decomposition.number, equalTo("1000001"));
        assertThat(decomposition.error, equalTo("too big number (>1e6)"));
    }


    @Test
    public void decomposeWithNumberLargeThan1() throws Exception {

        Error decomposition = (Error) PrimeFactors.decompose("0");

        assertThat(decomposition.number, equalTo("0"));
        assertThat(decomposition.error, equalTo("not an integer > 1"));
    }
}
