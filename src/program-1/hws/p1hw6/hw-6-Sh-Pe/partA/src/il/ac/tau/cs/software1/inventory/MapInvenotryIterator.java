package il.ac.tau.cs.software1.inventory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapInvenotryIterator implements Iterator<Collectible> {

	private final Map<Float, List<Collectible>> inventory;
	private final Float[] keys;
	private int pricePointer;
	private int dataPointer;

	public MapInvenotryIterator(Map<Float, List<Collectible>> inventory) {
		this.inventory = inventory;
		this.keys = inventory.keySet().toArray(new Float[]{});
		this.pricePointer = 0;
		this.dataPointer = 0;
	}

	@Override
	public boolean hasNext() {
		if (pricePointer >= keys.length) {
			return false;
		}
		List<Collectible> collectibles = inventory.get(keys[pricePointer]);
		if (collectibles == null) {
			return false;
		}
		return !(pricePointer >= keys.length - 1 && dataPointer >= collectibles.size());
	}

	@Override
	public Collectible next() {
		if (dataPointer == inventory.get(keys[pricePointer]).size()) {
			dataPointer = 0;
			pricePointer += 1;
		}
		return inventory.get(keys[pricePointer]).get(dataPointer++);
	}

}
