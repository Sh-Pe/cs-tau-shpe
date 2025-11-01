package il.ac.tau.cs.software1.sokoban;

public class SokobanWalkMove extends SokobanAbstractMove {

	public SokobanWalkMove(SokobanDirection dir) {
		super(dir);
	}

	@Override
	public boolean isValid(SokobanPlan b) {
		SokobanPoint worker = b.getWorker();
		if (b.isBox(worker.shift(dir,  1)) || b.isWall(worker.shift(dir, 1))) return false;
		return true;
	}

	@Override
	public SokobanPlan apply(SokobanPlan b) {
		return b.moveWorker(dir);
	}

}
