package il.ac.tau.cs.software1.sokoban;

import il.ac.tau.cs.software1.components.*;
import il.ac.tau.cs.software1.pacman.PacmanGridComponent;

public class SokobanWorker extends SokobanGameObject {

	public SokobanWorker(PacmanGridComponent initialPosition) {
		super(initialPosition);
		this.imageComponent = new ImageComponent("worker");
	}

}
