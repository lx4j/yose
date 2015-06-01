package test.yose.utils;

import org.junit.Test;
import yose.challenge.primefactors.ArabicNumber;
import yose.challenge.primefactors.Numeral;
import yose.challenge.primefactors.RomanNumber;
import yose.utils.JSON;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class JSONTest {
    @Test
    public void stringifyArabicNumber() throws Exception {
        assertThat(JSON.stringify(ArabicNumber.valueOf(6)), equalTo("6"));
    }

    @Test
    public void stringifyRomanNumber() throws Exception {
        assertThat(JSON.stringify(RomanNumber.valueOf(6)), equalTo("\"VI\""));
    }

    @Test
    public void parseArabicNumber() throws Exception {
        assertThat(JSON.parse("6", Numeral.class).intValue(), equalTo(6));
    }

    @Test
    public void parseRomanNumber() throws Exception {
        assertThat(JSON.parse("VI", Numeral.class).intValue(), equalTo(6));
    }
}