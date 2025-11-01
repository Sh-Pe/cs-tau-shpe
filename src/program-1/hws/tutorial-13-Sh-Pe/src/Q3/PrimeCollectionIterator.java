package Q3;

import java.util.Iterator;
import java.util.stream.IntStream;

public class PrimeCollectionIterator implements Iterator<Integer> {

    private final int max;
    private int pointer;
    private int count;

    public PrimeCollectionIterator(int k) {
        this.max = k;
		this.pointer = 2;
		this.count = 0;
    }

    public Integer next() throws IndexOutOfBoundsException {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException();
        }
        boolean working = false;
        while (working == false) {
            working = IntStream.range(0, (int) Math.sqrt(pointer)).anyMatch(
                x -> pointer / x != 1
            );
            pointer++;
        }
        count++;
        return pointer;
    }

    public boolean hasNext() {
        return pointer <= max;
    }

}
