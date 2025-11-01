package il.ac.tau.cs.sw1.ex8.riddles.forth;

import java.util.Iterator;

public class A4 implements Iterable<String> {
    
    private String[] strings;
    private int k;
    public A4(String[] strings, int k){
        this.strings = strings;
        this.k = k;
    }

    @Override
    public Iterator<String> iterator() {
        return new B4(strings, k);
    }
}
