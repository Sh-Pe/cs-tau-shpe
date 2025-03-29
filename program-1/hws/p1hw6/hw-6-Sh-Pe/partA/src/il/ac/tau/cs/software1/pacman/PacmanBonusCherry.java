package il.ac.tau.cs.software1.pacman;

import il.ac.tau.cs.software1.components.*;

public class PacmanBonusCherry extends PacmanBonus {
	public PacmanBonusCherry(PacmanGridComponent initialPosition) {
		super(initialPosition);
		this.imageComponent = new ImageComponent("bonus-cherry");
		this.bonusScore = 30;
	}
	
	@Override
	public float getWeight() {
		return 2.0f;
	}

	@Override
	public float getPrice() {
		return 25.0f;
	}
}
