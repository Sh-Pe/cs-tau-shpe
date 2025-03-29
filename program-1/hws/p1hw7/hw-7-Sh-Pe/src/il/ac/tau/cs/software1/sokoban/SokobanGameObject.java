package il.ac.tau.cs.software1.sokoban;

import il.ac.tau.cs.software1.core.GameObject;
import il.ac.tau.cs.software1.math.Vector2;
import il.ac.tau.cs.software1.pacman.PacmanGridComponent;

public class SokobanGameObject extends GameObject {
	protected PacmanGridComponent gridComponent;
	
	public SokobanGameObject(PacmanGridComponent initialPosition) {
		gridComponent = initialPosition;
		transformComponent.scale = new Vector2(SokobanConstants.cellWidth, SokobanConstants.cellHeight);
		snapTransformToGrid();
	}
	
	@Override
	public void update() {
		
	}
	
	protected void snapTransformToGrid() {
		transformComponent.position = new Vector2(
				-1.f + gridComponent.x * SokobanConstants.cellWidth,
				-1.f + gridComponent.y * SokobanConstants.cellHeight
		);
	}
	
	public void teleport(int x, int y) {
		gridComponent.setPosition(x, y);
		snapTransformToGrid();
	}
}
