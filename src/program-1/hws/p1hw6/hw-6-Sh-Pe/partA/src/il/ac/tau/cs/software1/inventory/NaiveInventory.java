package il.ac.tau.cs.software1.inventory;

import java.util.Arrays;
import java.util.Iterator;

public class NaiveInventory implements Inventory{
	private final int INITIAL_CAPACITY = 100;
	private Collectible[] arr;
	private int numOfItems;
	private float totalWeight;
	
	public NaiveInventory()
	{
		arr = new Collectible[INITIAL_CAPACITY];
	}
	
	@Override
	public void collect(Collectible collectible) {
		if (numOfItems == arr.length)
		{
			arr = Arrays.copyOf(arr, 2*arr.length);
		}
		arr[numOfItems]= collectible;
		totalWeight += collectible.getWeight();
		numOfItems++;
	}
	
	@Override
	public float getTotalWeight() {
		return totalWeight;
	}
	
	@Override
	public int getCurrentCount() {
		return numOfItems;
	}
	
	
	public Collectible[] getCollArr() {
		return arr;
	}

	@Override
	public Iterator<Collectible> iterator() {
		return new NaiveInventoryIterator(this);
	}

	protected Collectible[] getStuff() {
		return arr;
	}
	
	
}
