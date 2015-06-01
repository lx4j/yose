package yose.challenge.primefactors;

import java.util.List;

/**
 * Created by L.x on 15-5-28.
 */
public class Decomposition implements Result {

    public final List<Numeral> decomposition;
    public final Numeral number;

    public Decomposition(Numeral number, List<Numeral> decomposition) {
        this.number = number;
        this.decomposition = decomposition;
    }


}
