package il.ac.tau.cs.software1.pacman;

import il.ac.tau.cs.software1.inventory.Collectible;

public class HW6_tester {

	public static void main(String[] args) {
		PacmanGridComponent gc = new PacmanGridComponent(0,0);
		PacmanPlayer player = new PacmanPlayer(gc);
		
		System.out.println("Print Score: " + player.getScore());
		System.out.println("Print Total weight: " + player.getInventoryComponent().getInventory().getTotalWeight());
		
		PacmanBonusDot bd = new PacmanBonusDot(gc);
		player.onHit(bd);
		player.onHit(bd);
		
		System.out.println("Print Score: " + player.getScore());
		System.out.println("Print Total weight: " + player.getInventoryComponent().getInventory().getTotalWeight());
		
		for (int i=0; i < 5; ++i)
			player.onHit(bd);
		
		System.out.println("Print Score: " + player.getScore());
		System.out.println("Print Total weight: " + player.getInventoryComponent().getInventory().getTotalWeight());
		
		System.out.println("Printing inv contents: ");
		for (Collectible c : player.getInventoryComponent().getInventory())
		{
			System.out.print("(" + c.getPrice() + ", " +c.getWeight() + ")   ");
		}
		
		System.out.println("");
		System.out.println("");
		
		PacmanBonusCherry bc = new PacmanBonusCherry(gc);
		player.onHit(bc);
		player.onHit(bc);
		player.onHit(bc);
		
		System.out.println("size of inv: " + player.getInventoryComponent().getInventory().getCurrentCount() );
		
		System.out.println("Printing inv contents: ");
		int i=0;
		for (Collectible c : player.getInventoryComponent().getInventory())
		{
			System.out.println(i);
			System.out.print("(" + c.getPrice() + ", " +c.getWeight() + ")   ");
			i++;
		}
		
		

	}


}
