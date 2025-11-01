package il.ac.tau.cs.software1.inventory;

import java.util.Iterator;

public class NaiveInventoryIterator implements Iterator<Collectible> {

	private final NaiveInventory inventory;
	private int dataPointer;

	public NaiveInventoryIterator(NaiveInventory inventory) {
		this.inventory = inventory;
		this.dataPointer = 0;
	}

	@Override
	public boolean hasNext() {
		return dataPointer < inventory.getCurrentCount();
	}

	@Override
	public Collectible next() {
		return inventory.getStuff()[dataPointer++];
	}
}
