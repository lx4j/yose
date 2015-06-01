package test.yose;

import org.junit.Test;
import test.support.HttpRequest;
import test.support.HttpResponse;
import yose.challenge.primefactors.*;
import yose.challenge.primefactors.Error;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testinfected.hamcrest.dom.DomMatchers.hasUniqueSelector;

/**
 * Created by L.x on 15-5-27.
 */
public class PrimeFactorsChallenge extends YoseChallenge {

    @Test
    public void decompositeArabicNumber() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/primeFactors?number=16");

        assertThat(response.contentType(), equalTo("application/json"));
        Decomposition result = response.as(Decomposition.class);
        assertThat(result.number, equalTo(ArabicNumber.valueOf(16)));
        assertThat(result.decomposition, equalTo(Arrays.asList(
                ArabicNumber.valueOf(2),
                ArabicNumber.valueOf(2),
                ArabicNumber.valueOf(2),
                ArabicNumber.valueOf(2)
        )));
    }

    @Test
    public void decompositeRomanNumber() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/primeFactors?number=CCC");

        assertThat(response.contentType(), equalTo("application/json"));
        Decomposition result = response.as(Decomposition.class);
        assertThat(result.number, equalTo(RomanNumber.valueOf(300)));
        assertThat(result.decomposition, equalTo(Arrays.asList(
                RomanNumber.parse("II"),
                RomanNumber.parse("II"),
                RomanNumber.parse("III"),
                RomanNumber.parse("V"),
                RomanNumber.parse("V")
        )));
    }

    @Test
    public void reportErrorWithInvalidNumber() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/primeFactors?number=hello");

        assertThat(response.contentType(), equalTo("application/json"));
        Error result = response.as(Error.class);
        assertThat(result.number, equalTo("hello"));
        assertThat(result.error, equalTo("not a number"));
    }

    @Test
    public void reportErrorWithNumberLargeThan1000000() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/primeFactors?number=1000001");

        assertThat(response.contentType(), equalTo("application/json"));
        Error result = response.as(Error.class);
        assertThat(result.number, equalTo("1000001"));
        assertThat(result.error, equalTo("too big number (>1e6)"));
    }


    @Test
    public void reportErrorWithNumberLessThan1() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/primeFactors?number=-1");

        assertThat(response.contentType(), equalTo("application/json"));
        Error result = response.as(Error.class);
        assertThat(result.number, equalTo("-1"));
        assertThat(result.error, equalTo("not an integer > 1"));
    }

    @Test
    public void decomposeMultiEntries() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/primeFactors?number=16&number=hello");

        assertThat(response.contentType(), equalTo("application/json"));
        List result = response.as(List.class);
        assertThat(result.size(), equalTo(2));
    }

    @Test
    public void uiSketches() throws Exception {
        HttpResponse response = HttpRequest.get("http://localhost:3000/primeFactors/ui");

        assertThat(response.asHtml(), allOf(
                hasUniqueSelector("#title"),
                hasUniqueSelector("#invitation"),
                hasUniqueSelector("input#number"),
                hasUniqueSelector("button#go")
        ));

    }
}
