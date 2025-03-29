package il.ac.tau.cs.software1.pacman;

import il.ac.tau.cs.software1.core.*;

public abstract class PacmanBonus extends PacmanGameObject {

	protected int bonusScore = 0;
	
	public PacmanBonus(PacmanGridComponent initialPosition) {
		super(initialPosition);
	}
	
	@Override
	public void onHit(GameObject other) {
		if (other instanceof PacmanPlayer) {
			PacmanPlayer player = (PacmanPlayer)other;
			player.addScore(this.bonusScore);
		}
	}

}
