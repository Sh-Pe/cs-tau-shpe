package il.ac.tau.cs.software1.core;

import java.util.*;

import il.ac.tau.cs.software1.core.*;
import il.ac.tau.cs.software1.math.*;



public class CollisionDetection {

	public static <T extends GameObject> void collide(List<T> objects) {
		for (T a : objects)
			for (T b : objects) {
				if (a == b || !CollisionDetection.isCollision(a, b)) continue;
				a.onHit(b);
			}
	}
	
	public static <T extends GameObject> boolean canPerformTick(
			T object, List<T> objects, Vector2 velocity, IObjectFilter objectFilter) {
		object.getTransformComponent().position.add(velocity);
		boolean flag = true;
		for (T otherObject : objects) {
			if (object == otherObject) continue;
			if (!objectFilter.filter(otherObject)) continue;
			if (CollisionDetection.isCollision(otherObject, object)) {
				flag = false;
				break;
			}
		}
		object.getTransformComponent().position.add(Vector2.minus(velocity));
		return flag;
	}
	
	private static float collisionEps = 1e-4f;
	
	// For simplicity, instead of having a collision component for each GameObject,
	// we assume that every object is the box [0,1] x [0,1], which is scaled and transformed
	// by its transform component.
	// In general, we would probably need an abstract CollisionComponent, and then implement
	// different types (Box, Circle, Polygon, PerPixel, etc.) and the relations between each pair.
	public static boolean isCollision(GameObject a, GameObject b) {
		// tl = top left; br = bottom right
		Vector2 a_tl = a.transformComponent.topLeft();
		Vector2 a_br = a.transformComponent.bottomRight(); 
		Vector2 b_tl = b.transformComponent.topLeft();
		Vector2 b_br = b.transformComponent.bottomRight();
		
		// Since float equality is quite tricky, then we should instead allow some epsilon margin
		float eps = CollisionDetection.collisionEps;
		if (a_tl.x > b_br.x - eps || b_tl.x > a_br.x - eps) return false;
		if (a_br.y > b_tl.y - eps || b_br.y > a_tl.y - eps) return false;
		
		return true;
	}
	
	

}
