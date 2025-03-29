package il.ac.tau.cs.software1.inventory;

import java.util.Arrays;

public class NaiveInventory implements Inventory {

	private final int INITIAL_CAPACITY = 100 ;
	private int dataPointer;
	private Collectible[] stuff;

	public NaiveInventory() {
		this.dataPointer = 0;
		this.stuff = new Collectible[INITIAL_CAPACITY];
	}

	@Override
	public void collect(Collectible collectible) {
		if (dataPointer == stuff.length) {
			updateStuff();
		}
		stuff[dataPointer] = collectible;
		dataPointer += 1;
	}

	private void updateStuff() {
		Collectible[] newStuff = new Collectible[stuff.length * 2];
		System.arraycopy(stuff, 0, newStuff, 0, stuff.length);
		stuff = newStuff;
	}

	@Override
	public float getTotalWeight() {
		float output = 0.0F;
		for (Collectible collectible : stuff) {
			if (collectible != null) {
				output += collectible.getWeight();
			}
		}
		return output;
	}

	@Override
	public int getCurrentCount() {
		return dataPointer;
	}

}
