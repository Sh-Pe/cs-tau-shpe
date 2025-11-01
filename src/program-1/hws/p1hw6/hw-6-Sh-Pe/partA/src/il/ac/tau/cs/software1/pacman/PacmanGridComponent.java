package il.ac.tau.cs.software1.pacman;

import il.ac.tau.cs.software1.components.*;

public class PacmanGridComponent implements IComponent {
	public int x;
	public int y;
	
	public PacmanGridComponent(int x, int y) {
		this.setPosition(x, y);
	}
	
	public PacmanGridComponent() {
		this(0, 0);
	}
	
	public void setPosition(int x, int y) {
		this.x = x; 
		this.y = y;
	}
}
