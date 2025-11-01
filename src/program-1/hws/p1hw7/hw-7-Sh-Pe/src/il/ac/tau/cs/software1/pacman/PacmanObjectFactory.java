package il.ac.tau.cs.software1.pacman;

public class PacmanObjectFactory {
	
	public static PacmanGameObject spawnObstacle(PacmanGridComponent location) {
		return new PacmanObstacle(location);
	}
	
	public static PacmanGameObject spawnPlayer(PacmanGridComponent location) {
		return new PacmanPlayer(location);
	}
	
	public static PacmanGameObject spawnBonusDot(PacmanGridComponent location) {
		return new PacmanBonusDot(location);
	}
	
	public static PacmanGameObject spawnBonusPotion(PacmanGridComponent location) {
		return new PacmanBonusPotion(location);
	}
	
	public static PacmanGameObject spawnGhost(PacmanGridComponent location) {
		return new PacmanGhost(location);
	}

}
