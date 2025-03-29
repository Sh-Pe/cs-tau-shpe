package il.ac.tau.cs.software1.sokoban;

import il.ac.tau.cs.software1.pacman.PacmanGridComponent;


public class SokobanObjectFactory {
	public static SokobanGameObject spawnWorker(PacmanGridComponent location) {
		return new SokobanWorker(location);
	}
	public static SokobanGameObject spawnWall(PacmanGridComponent location) {
		return new SokobanWall(location);
	}
	public static SokobanGameObject spawnBox(PacmanGridComponent location) {
		return new SokobanBox(location);
	}
	public static SokobanGameObject spawnTarget(PacmanGridComponent location) {
		return new SokobanTarget(location);
	}
}
