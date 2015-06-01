package test.yose.challenge.primefactors;

import org.junit.Test;
import yose.challenge.primefactors.Numeral;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class NumeralTest {
    @Test
    public void parseArabicNumeral() throws Exception {
        assertThat(Numeral.parse("1").intValue(), equalTo(1));
    }

    @Test
    public void parseRomanNumeral() throws Exception {
        assertThat(Numeral.parse("II").intValue(), equalTo(2));
    }
}