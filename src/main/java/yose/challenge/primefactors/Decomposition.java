package yose.challenge.primefactors;

import java.util.List;

/**
 * Created by L.x on 15-5-28.
 */
public class Decomposition {

    public final String number;
    public final List<Integer> decomposition;

    public Decomposition(String number, List<Integer> decomposition) {
        this.number = number;
        this.decomposition = decomposition;
    }


}
