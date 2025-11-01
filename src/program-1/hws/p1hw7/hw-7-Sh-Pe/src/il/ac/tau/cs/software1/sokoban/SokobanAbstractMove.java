package il.ac.tau.cs.software1.sokoban;

public abstract class SokobanAbstractMove {
	protected SokobanDirection dir;
	
	public abstract boolean isValid(SokobanPlan b);
	public abstract SokobanPlan apply(SokobanPlan b);
	public SokobanAbstractMove(SokobanDirection dir) { this.dir = dir; }
}
