package il.ac.tau.cs.software1.sokoban;

import il.ac.tau.cs.software1.components.*;
import il.ac.tau.cs.software1.pacman.PacmanGridComponent;

public class SokobanTarget extends SokobanGameObject {

	public SokobanTarget(PacmanGridComponent initialPosition) {
		super(initialPosition);
		this.imageComponent = new ImageComponent("target");
	}

}
