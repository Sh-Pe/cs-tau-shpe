package il.ac.tau.cs.software1.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MapInventory implements Inventory {

	private final HashMap<Float, List<Collectible>> inventory = new HashMap<>();
	private int count = 0;

	@Override
	public void collect(Collectible collectible) {
		inventory.computeIfAbsent(collectible.getPrice(), collectables -> new ArrayList<>());
		inventory.get(collectible.getPrice()).add(collectible);
		count += 1;
	}

	@Override
	public float getTotalWeight() {
		float output = 0;
		for (Float key : inventory.keySet()) {
			for (Collectible collectible : inventory.get(key)) {
				output += collectible.getWeight();
			};
		}
		return output;
	}

	@Override
	public int getCurrentCount() {
		return count;
	}

	@Override
	public Iterator<Collectible> iterator() {
//		System.out.println(inventory.keySet().size());
		return new MapInvenotryIterator(inventory);
	}

}
