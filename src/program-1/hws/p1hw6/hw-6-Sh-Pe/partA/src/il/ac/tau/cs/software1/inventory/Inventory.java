package il.ac.tau.cs.software1.inventory;

public interface Inventory extends Iterable<Collectible>{
	// Adds a collectible to our inventory
	void collect(Collectible collectible);
		
	// Returns combined weight of all items in the inventory
	float getTotalWeight();
	
	// Returns the amount of items in the inventory 
	int getCurrentCount();
}
