package il.ac.tau.cs.software1.pacman;

import il.ac.tau.cs.software1.core.*;
import il.ac.tau.cs.software1.math.*;


public class PacmanGameObject extends GameObject {
	
	protected PacmanGridComponent gridComponent;
		
	public PacmanGameObject(PacmanGridComponent initialPosition) {
		gridComponent = initialPosition;
		transformComponent.scale = new Vector2(PacmanConstants.cellWidth, PacmanConstants.cellHeight);
		snapTransformToGrid();
	}
	
	@Override
	public void update() {
		
	}
	
	protected void snapTransformToGrid() {
		transformComponent.position = new Vector2(
				-1.f + gridComponent.x * PacmanConstants.cellWidth,
				-1.f + gridComponent.y * PacmanConstants.cellHeight
		);
	}
	
	public void teleport(int x, int y) {
		gridComponent.setPosition(x, y);
		snapTransformToGrid();
	}
}
