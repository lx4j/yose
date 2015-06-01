package test.yose.challenge.primefactors;

import org.junit.Test;
import yose.challenge.primefactors.ArabicNumber;
import yose.challenge.primefactors.Numeral;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 15-6-1.
 */
public class ArabicNumberTest {
    @Test
    public void parseNumber() throws Exception {
        assertThat(ArabicNumber.parse("1"), equalTo(ArabicNumber.valueOf(1)));
        assertThat(ArabicNumber.parse("3"), equalTo(ArabicNumber.valueOf(3)));
    }

    @Test(expected = NumberFormatException.class)
    public void raisingExceptionWhenParsingNaN() throws Exception {
        ArabicNumber.parse("hello");
    }

    @Test
    public void equality() throws Exception {
        assertThat(ArabicNumber.parse("1"), equalTo(ArabicNumber.parse("1")));
        assertThat(ArabicNumber.parse("1"), not(equalTo(ArabicNumber.parse("2"))));
        assertThat(ArabicNumber.parse("1"), equalTo(ArabicNumber.valueOf(1)));
        assertThat(ArabicNumber.parse("1"), not(equalTo((Object) 1)));
        assertThat(ArabicNumber.parse("1"), not(equalTo(null)));
    }

    @Test
    public void of() throws Exception {
        assertThat(ArabicNumber.valueOf(5).of(3).toString(), equalTo("3"));

    }

    @Test
    public void lessThan() throws Exception {
        Numeral five = ArabicNumber.parse("5");
        assertThat(five.lessThan(6), is(true));
        assertThat(five.lessThan(5), is(false));
    }

    @Test
    public void largeThan() throws Exception {
        Numeral five = ArabicNumber.parse("5");
        assertThat(five.largeThan(4), is(true));
        assertThat(five.largeThan(5), is(false));
    }

    @Test
    public void numeralToString() throws Exception {
        assertThat(ArabicNumber.parse("5").toString(), equalTo("5"));
    }
}
