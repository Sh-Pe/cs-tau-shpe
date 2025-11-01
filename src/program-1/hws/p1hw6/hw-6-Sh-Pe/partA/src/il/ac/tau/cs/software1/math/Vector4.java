package il.ac.tau.cs.software1.math;

public class Vector4 {
	public float x;
	public float y;
	public float z;
	public float w;
	
	public Vector4() {
		this(0.f, 0.f, 0.f, 0.f);
	}
	public Vector4(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public static Vector4 add(Vector4 a, Vector4 b) {
		return new Vector4(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
	}
}
