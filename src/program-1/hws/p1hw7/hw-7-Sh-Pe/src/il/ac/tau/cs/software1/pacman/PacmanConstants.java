package il.ac.tau.cs.software1.pacman;

public class PacmanConstants {
	/*
	 * Various constants used for the Pac-Man game 
	 */
	
	public static final int gridWidth = 28;
	public static final int gridHeight = 31;
	
	// Any 4:3 resolution should work well. Otherwise the game will be skewed!
	public static final int screenWidth = 700;
	public static final int screenHeight = 775;
	
	public static final float cellWidth = 2.f / (float)gridWidth;
	public static final float cellHeight = 2.f / (float)gridHeight;
}
