package il.ac.tau.cs.sw1.ex8.riddles.forth;


import java.util.Iterator;

public class B4 implements Iterator<String> {

	private String[] strings;
	private int k;
	private int memoryPointer;

	public B4(String[] strings, int k) {
		this.strings = strings; this.k = k; this.memoryPointer = 0;
	}

	@Override
	public boolean hasNext() {
		return memoryPointer < k * strings.length;
	}

	@Override
	public String next() throws IndexOutOfBoundsException {
		if (!hasNext()) {
			throw new IndexOutOfBoundsException("index out of bound");
		}
//		System.out.println("K:" + k + " memP:" + memoryPointer);
		return strings[memoryPointer++ % (strings.length)];
	}
}
