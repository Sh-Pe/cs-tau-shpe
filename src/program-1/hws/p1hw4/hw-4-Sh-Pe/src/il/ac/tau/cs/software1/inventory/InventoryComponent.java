package il.ac.tau.cs.software1.inventory;

import il.ac.tau.cs.software1.math.Vector2;

public class InventoryComponent {

	public static final Vector2 INVENTORY_POSITION = new Vector2(-100.0f, 100.0f);
	private static final int INITIAL_MAX_WEIGHT = 10;
	private final Inventory INVENTORY = new NaiveInventory();
	private int maxWeight;

	public InventoryComponent() {
		this.maxWeight = INITIAL_MAX_WEIGHT;
	}

	public static Vector2 getInventoryPosition() {
		return INVENTORY_POSITION;
	}

	public Inventory getInventory() {
		return INVENTORY;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int newMaxWeight) {
		this.maxWeight = newMaxWeight;
	}

}
