package il.ac.tau.cs.software1.math;

public class Vector3 {
	public float x;
	public float y;
	public float z;
	
	public Vector3() {
		this(0.f, 0.f, 0.f);
	}
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3 add(Vector3 a, Vector3 b) {
		return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
	}
}
