package yose.challenge.primefactors;

import java.util.List;

/**
 * Created by L.x on 15-5-28.
 */
public class Decomposition extends Result{

    public final List<Integer> decomposition;

    public Decomposition(String number, List<Integer> decomposition) {
        super(number);
        this.decomposition = decomposition;
    }


}
