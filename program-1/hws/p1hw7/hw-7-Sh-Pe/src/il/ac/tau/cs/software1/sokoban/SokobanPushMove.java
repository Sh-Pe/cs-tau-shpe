package il.ac.tau.cs.software1.sokoban;

public class SokobanPushMove extends SokobanAbstractMove {
	public SokobanPushMove(SokobanDirection dir) {
		super(dir);
	}

	@Override
	public boolean isValid(SokobanPlan b) {
		SokobanPoint worker = b.getWorker();
		if (!b.isBox(worker.shift(dir,  1))) return false;
		if (b.isBox(worker.shift(dir,  2)) || b.isWall(worker.shift(dir, 2))) return false;
		return true;
	}

	@Override
	public SokobanPlan apply(SokobanPlan b) {
		return b.moveBoxByWorker(dir).moveWorker(dir);
	}
	
}
