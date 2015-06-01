package test.yose.challenge.primefactors;

import org.junit.Test;
import yose.challenge.primefactors.Numeral;
import yose.challenge.primefactors.RomanNumber;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-6-1.
 */
public class RomanNumberTest {
    @Test
    public void parseNumber() throws Exception {
        assertThat(RomanNumber.parse(""), equalTo(RomanNumber.valueOf(0)));
        assertThat(RomanNumber.parse("I"), equalTo(RomanNumber.valueOf(1)));
        assertThat(RomanNumber.parse("II"), equalTo(RomanNumber.valueOf(2)));
        assertThat(RomanNumber.parse("III"), equalTo(RomanNumber.valueOf(3)));
        assertThat(RomanNumber.parse("IV"), equalTo(RomanNumber.valueOf(4)));
        assertThat(RomanNumber.parse("V"), equalTo(RomanNumber.valueOf(5)));
        assertThat(RomanNumber.parse("VI"), equalTo(RomanNumber.valueOf(6)));
        assertThat(RomanNumber.parse("IX"), equalTo(RomanNumber.valueOf(9)));
        assertThat(RomanNumber.parse("X"), equalTo(RomanNumber.valueOf(10)));
        assertThat(RomanNumber.parse("XL"), equalTo(RomanNumber.valueOf(40)));
        assertThat(RomanNumber.parse("L"), equalTo(RomanNumber.valueOf(50)));
        assertThat(RomanNumber.parse("XC"), equalTo(RomanNumber.valueOf(90)));
        assertThat(RomanNumber.parse("C"), equalTo(RomanNumber.valueOf(100)));
        assertThat(RomanNumber.parse("CD"), equalTo(RomanNumber.valueOf(400)));
        assertThat(RomanNumber.parse("D"), equalTo(RomanNumber.valueOf(500)));
        assertThat(RomanNumber.parse("CM"), equalTo(RomanNumber.valueOf(900)));
        assertThat(RomanNumber.parse("M"), equalTo(RomanNumber.valueOf(1000)));
        assertThat(RomanNumber.parse("MCMLXXVI"), equalTo(RomanNumber.valueOf(1976)));
    }

    @Test
    public void of() throws Exception {
        assertThat(RomanNumber.valueOf(2).of(4).toString(), equalTo("IV"));
    }

    @Test
    public void equality() throws Exception {
        assertThat(RomanNumber.parse("I"), equalTo(RomanNumber.parse("I")));
        assertThat(RomanNumber.parse("I"), not(equalTo(RomanNumber.parse("II"))));
        assertThat(RomanNumber.parse("I"), equalTo(RomanNumber.valueOf(1)));
        assertThat(RomanNumber.parse("I"), not(equalTo((Object) 1)));
        assertThat(RomanNumber.parse("I"), not(equalTo(null)));

    }

    @Test(expected = NumberFormatException.class)
    public void raisingExceptionWhenParsingNaN() throws Exception {
        RomanNumber.parse("hello");
    }

    @Test
    public void lessThan() throws Exception {
        Numeral five = RomanNumber.parse("V");
        assertThat(five.lessThan(6), is(true));
        assertThat(five.lessThan(5), is(false));
    }

    @Test
    public void largeThan() throws Exception {
        Numeral five = RomanNumber.parse("V");
        assertThat(five.largeThan(4), is(true));
        assertThat(five.largeThan(5), is(false));
    }

    @Test
    public void numeralToString() throws Exception {
        assertThat(RomanNumber.valueOf(0).toString(), equalTo(""));
        assertThat(RomanNumber.valueOf(1).toString(), equalTo("I"));
        assertThat(RomanNumber.valueOf(2).toString(), equalTo("II"));
        assertThat(RomanNumber.valueOf(3).toString(), equalTo("III"));
        assertThat(RomanNumber.valueOf(4).toString(), equalTo("IV"));
        assertThat(RomanNumber.valueOf(5).toString(), equalTo("V"));
        assertThat(RomanNumber.valueOf(6).toString(), equalTo("VI"));
        assertThat(RomanNumber.valueOf(9).toString(), equalTo("IX"));
        assertThat(RomanNumber.valueOf(10).toString(), equalTo("X"));
        assertThat(RomanNumber.valueOf(40).toString(), equalTo("XL"));
        assertThat(RomanNumber.valueOf(50).toString(), equalTo("L"));
        assertThat(RomanNumber.valueOf(90).toString(), equalTo("XC"));
        assertThat(RomanNumber.valueOf(100).toString(), equalTo("C"));
        assertThat(RomanNumber.valueOf(400).toString(), equalTo("CD"));
        assertThat(RomanNumber.valueOf(500).toString(), equalTo("D"));
        assertThat(RomanNumber.valueOf(900).toString(), equalTo("CM"));
        assertThat(RomanNumber.valueOf(1000).toString(), equalTo("M"));
        assertThat(RomanNumber.valueOf(1976).toString(), equalTo("MCMLXXVI"));
    }
}
