package Q3;

import java.util.Iterator;

class PrimeCollection implements Iterable<Integer> {

	int max;
	int pointer;

	PrimeCollection(int k) {
		this.max = 0;
		this.pointer = 0;
	}

	public Iterator<Integer> iterator() {
		return new PrimeCollectionIterator(max);
	}


}
