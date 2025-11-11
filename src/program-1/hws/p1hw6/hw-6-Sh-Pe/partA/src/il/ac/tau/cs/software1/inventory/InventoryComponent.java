package il.ac.tau.cs.software1.inventory;

import il.ac.tau.cs.software1.components.IComponent;
import il.ac.tau.cs.software1.math.Vector2;

public class InventoryComponent implements IComponent {
	private Inventory inv = new MapInventory();
	private static final Vector2 INVENTORY_POSITION = new Vector2(-100.0f, -100.0f);
	private static final float INITIAL_MAX_WEIGHT = 10;
	private float maxWeight = INITIAL_MAX_WEIGHT;

	public Inventory getInventory()
	{
		return inv;
	}
	
	public Vector2 getInventoryPosition()
	{
		return INVENTORY_POSITION;
	}
	
	public float getMaxWeight()
	{
		return maxWeight;
	}
	
	public void setMaxWeight(float w)
	{
		maxWeight = w;
	}
}
