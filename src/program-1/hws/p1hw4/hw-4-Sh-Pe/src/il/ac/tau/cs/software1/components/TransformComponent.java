package il.ac.tau.cs.software1.components;

import il.ac.tau.cs.software1.math.*;

public class TransformComponent implements IComponent {
	public Vector2 position;
	public Vector2 scale;
	
	public TransformComponent(Vector2 position, Vector2 scale) {
		this.position = position;
		this.scale = scale;
	}
	public TransformComponent() {
		this(new Vector2(), new Vector2(1.f, 1.f));
	}
	
	public Vector2 topLeft() {
		return new Vector2(position.x, position.y + scale.y);
	}
	
	public Vector2 bottomRight() {
		return new Vector2(position.x + scale.x, position.y);
	}
}
