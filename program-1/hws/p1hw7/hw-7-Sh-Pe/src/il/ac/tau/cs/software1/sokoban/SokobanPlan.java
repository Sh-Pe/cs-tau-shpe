package il.ac.tau.cs.software1.sokoban;
import java.util.*;

public final class SokobanPlan {
	private final SokobanPoint worker;
	private final List<SokobanPoint> boxes;
	private final List<SokobanPoint> walls;
	private final List<SokobanPoint> targets;
	public SokobanPlan(SokobanPoint worker, List<SokobanPoint> boxes, List<SokobanPoint> walls, List<SokobanPoint> targets) {
		this.worker = worker;
		this.boxes = boxes;
		this.walls = walls;
		this.targets = targets;
	}
	
	public boolean isWall(SokobanPoint p) {
		return walls.contains(p);
	}
	public boolean isBox(SokobanPoint p) {
		return boxes.contains(p);
	}
	
	public SokobanPoint getWorker() {
		return worker;
	}
	public List<SokobanPoint> getBoxes() { return boxes; }
	public List<SokobanPoint> getWalls() { return walls; }
	public List<SokobanPoint> getTargets() { return targets; }
	
	
	public SokobanPlan moveWorker(SokobanDirection dir) {
		SokobanPoint newWorker = worker.shift(dir,  1);
		return new SokobanPlan(newWorker, this.boxes, this.walls, this.targets);
	}
	public SokobanPlan moveBoxByWorker(SokobanDirection dir) {
		List<SokobanPoint> newBoxes = new ArrayList<>();
		SokobanPoint movedBox = worker.shift(dir,  1);
		for (SokobanPoint box : boxes) {
			if (box.equals(movedBox)) newBoxes.add(box.shift(dir, 1));
			else newBoxes.add(box);
		}
		return new SokobanPlan(this.worker, newBoxes, this.walls, this.targets);
	}
	public boolean isWin() {
		for (SokobanPoint target : targets) {
			if (!boxes.contains(target)) return false;
		}
		return true;
	}
}
