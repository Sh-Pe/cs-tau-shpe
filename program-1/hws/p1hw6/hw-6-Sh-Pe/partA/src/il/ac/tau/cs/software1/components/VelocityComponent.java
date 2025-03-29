package il.ac.tau.cs.software1.components;

import il.ac.tau.cs.software1.math.*;

public class VelocityComponent implements IComponent {
	public Vector2 velocity = new Vector2(0.f, 0.f);
	
	public static final float zeroVelocityThreshold = 1e-5f;
	
	public boolean isZero() {
		return (Math.abs(velocity.x) < VelocityComponent.zeroVelocityThreshold) && 
				(Math.abs(velocity.y) < VelocityComponent.zeroVelocityThreshold);
	}
	
	public boolean isDiagonal() {
		return (Math.abs(velocity.x) > VelocityComponent.zeroVelocityThreshold) && 
				(Math.abs(velocity.y) > VelocityComponent.zeroVelocityThreshold);
	}
}
