package il.ac.tau.cs.software1.math;

public class Vector2 {
	public float x;
	public float y;
	
	public Vector2() {
		this(0.f, 0.f);
	}
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public Vector2 copy() {
		return new Vector2(this.x, this.y);
	}
	
	public static Vector2 add(Vector2 a, Vector2 b) {
		return new Vector2(a.x + b.x, a.y + b.y);
	}
	public static Vector2 minus(Vector2 a) {
		return new Vector2(-a.x, -a.y);
	}
	
	public void add(Vector2 other) {
		this.x += other.x;
		this.y += other.y;
	}
	
	public void multiply(float alpha) {
		this.x *= alpha;
		this.y *= alpha;
	}
	
	public String toString() {
		return "(" + String.valueOf(x) + ", " + String.valueOf(y) + ")";
	}
}
