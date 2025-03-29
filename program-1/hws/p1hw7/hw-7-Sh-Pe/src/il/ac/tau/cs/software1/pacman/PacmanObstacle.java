package il.ac.tau.cs.software1.pacman;

import il.ac.tau.cs.software1.components.*;

public class PacmanObstacle extends PacmanGameObject {

	public PacmanObstacle(PacmanGridComponent initialPosition) {
		super(initialPosition);
		this.imageComponent = new ImageComponent("wall");
	}

}
