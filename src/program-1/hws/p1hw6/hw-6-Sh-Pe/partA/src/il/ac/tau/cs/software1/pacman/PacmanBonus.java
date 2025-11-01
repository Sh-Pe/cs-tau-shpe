package il.ac.tau.cs.software1.pacman;

import il.ac.tau.cs.software1.core.*;
import il.ac.tau.cs.software1.inventory.Collectible;

public abstract class PacmanBonus extends PacmanGameObject implements Collectible {

	protected int bonusScore = 0;
	
	public PacmanBonus(PacmanGridComponent initialPosition) {
		super(initialPosition);
	}
}
