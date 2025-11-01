package il.ac.tau.cs.software1.pacman;

import il.ac.tau.cs.software1.math.*;
import il.ac.tau.cs.software1.core.*;
import il.ac.tau.cs.software1.components.*;

public class PacmanGhost extends PacmanGameObject {
	// TOOD: Implement behavior! In class we'll talk on how to approach this.
	protected ImageComponent regularImageComponent;
	protected ImageComponent scaredImageComponent;
	protected boolean isScared = false;
	
	public PacmanGhost(PacmanGridComponent initialPosition) {
		super(initialPosition);
		regularImageComponent = new ImageComponent("ghost");
		scaredImageComponent = new ImageComponent("ghost-scared");
		setScared(false);
		
		/* Q5 */
		EventManager.getInstance().subscribe(
			"potionCollected",
			this,
			(data) -> {
				System.out.println("Collected potion @ " + data.publisher.getTransformComponent().position);
				setScared(!isScared);
			}
		);
	}
	
	protected void setScared(boolean scared) {
		isScared = scared;
		imageComponent = isScared ? scaredImageComponent : regularImageComponent;
	}
	
	public boolean getScared() {
		return isScared;
	}
	
}
