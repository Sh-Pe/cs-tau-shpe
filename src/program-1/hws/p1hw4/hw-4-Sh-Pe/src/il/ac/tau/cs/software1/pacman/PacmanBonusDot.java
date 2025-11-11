package il.ac.tau.cs.software1.pacman;

import il.ac.tau.cs.software1.components.*;

public class PacmanBonusDot extends PacmanBonus {

	public PacmanBonusDot(PacmanGridComponent initialPosition) {
		super(initialPosition);
		this.imageComponent = new ImageComponent("bonus-dot");
		this.bonusScore = 10;
	}

	@Override
	public float getWeight() {
		return 1.0F;
	}

	@Override
	public float getPrice() {
		return 12.0F;
	}
}
