package il.ac.tau.cs.software1.sokoban;

import java.util.Objects;

public final class SokobanPoint {
	private final int x;
	private final int y;
	
	public SokobanPoint(int x, int y) { this.x = x; this.y = y; }
	public int getX() { return x; }
	public int getY() { return y; }
	
	public SokobanPoint shift(SokobanDirection dir, int steps) {
		return new SokobanPoint(getX() + steps * dir.getX(), getY() + steps * dir.getY());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SokobanPoint other = (SokobanPoint) obj;
		return x == other.x && y == other.y;
	}
}
