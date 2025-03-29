package il.ac.tau.cs.software1.sokoban;

public enum SokobanDirection {
	TOP(0, 1), RIGHT(1, 0), BOTTOM(0, -1), LEFT(-1, 0), NONE(0, 0);
	
	private int x;
	private int y;
	
	private SokobanDirection(int x, int y) { this.x = x; this.y = y; }
	public int getX() { return x; }
	public int getY() { return y; }
}
