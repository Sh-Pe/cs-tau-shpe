package il.ac.tau.cs.software1.core;

import il.ac.tau.cs.software1.components.*;
import il.ac.tau.cs.software1.framework.*;

public abstract class GameObject {
	
	protected TransformComponent transformComponent = new TransformComponent();
	protected VelocityComponent velocityComponent = new VelocityComponent();
	protected ImageComponent imageComponent = null;
	
	private boolean shouldDelete = false;
	
	public abstract void update();
	
	public void render() {
		if (imageComponent != null) {
			Renderer.getInstance().drawRectangle(imageComponent.imageName, transformComponent.position, transformComponent.scale);
		}
	}
	
	public TransformComponent getTransformComponent() {
		return transformComponent;
	}
	
	public VelocityComponent getVelocityComponent() {
		return velocityComponent;
	}
	
	public void onHit(GameObject other) {
	}
	
	public void destroy() {
		shouldDelete = true;
	}
	
	public boolean getShouldDelete() {
		return shouldDelete;
	}
	
}
