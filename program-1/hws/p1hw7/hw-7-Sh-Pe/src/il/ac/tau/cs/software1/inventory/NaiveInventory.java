package il.ac.tau.cs.software1.inventory;

import java.util.Arrays;

public class NaiveInventory implements Inventory {
	private final int INITIAL_CAPACITY = 100;
	
	private Collectible[] items;
	private int numCollectibles;
	
	public NaiveInventory() {
		items = new Collectible[INITIAL_CAPACITY];
		numCollectibles = 0;
	}
	
	public void collect(Collectible collectible) {
		// Resize to twice the size when we need to 
		if (numCollectibles == items.length) {
			items = Arrays.copyOf(items, items.length * 2);
		}
		items[numCollectibles++] = collectible;
	}
	
	public float getTotalWeight() {
		float weight = 0.0f;
		for (int i = 0; i < numCollectibles; i++) weight += items[i].getWeight();
		return weight;
	}
	
	public int getCurrentCount() {
		return numCollectibles;
	}
}
