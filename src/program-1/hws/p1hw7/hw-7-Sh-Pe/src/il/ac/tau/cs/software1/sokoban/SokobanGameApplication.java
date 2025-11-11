package il.ac.tau.cs.software1.sokoban;

import java.util.*;

import il.ac.tau.cs.software1.core.EInputType;
import il.ac.tau.cs.software1.core.IGameLogic;
import il.ac.tau.cs.software1.framework.GameApplication;
import il.ac.tau.cs.software1.framework.Renderer;
import il.ac.tau.cs.software1.pacman.PacmanGridComponent;

public class SokobanGameApplication implements IGameLogic {
	private boolean shouldQuit = false;
	
	private final int updateFrame = 10;
	private int currFrame = 0;
	
	private List<SokobanGameObject> objects;
	private SokobanPlan currPlan;
	private SokobanDirection dir;
	
	private SokobanPlan gameObjToPlan(List<SokobanGameObject> objects) {
		SokobanPoint worker = null;
		List<SokobanPoint> boxes = new ArrayList<>();
		List<SokobanPoint> walls = new ArrayList<>();
		List<SokobanPoint> targets = new ArrayList<>();
		
		for (SokobanGameObject object : objects) {
			SokobanPoint p = new SokobanPoint(object.gridComponent.x, object.gridComponent.y);
			if (object instanceof SokobanWorker) 
				worker = p;
			if (object instanceof SokobanBox)
				boxes.add(p);
			if (object instanceof SokobanWall)
				walls.add(p);
			if (object instanceof SokobanTarget)
				targets.add(p);
		}
		return new SokobanPlan(worker, boxes, walls, targets);
	}
	
	private void refreshObjects() {
		objects.clear();
		for (SokobanPoint p : currPlan.getTargets())
			objects.add(SokobanObjectFactory.spawnTarget(new PacmanGridComponent(p.getX(), p.getY())));
		objects.add(SokobanObjectFactory.spawnWorker(
				new PacmanGridComponent(currPlan.getWorker().getX(), currPlan.getWorker().getY())));
		
		for (SokobanPoint p : currPlan.getBoxes())
			objects.add(SokobanObjectFactory.spawnBox(new PacmanGridComponent(p.getX(), p.getY())));
		for (SokobanPoint p : currPlan.getWalls())
			objects.add(SokobanObjectFactory.spawnWall(new PacmanGridComponent(p.getX(), p.getY())));
		
	}
	

	@Override
	public void init() {
		// Load texture assets
		Renderer.getInstance().addTexture("worker", "resources/textures/kenney_tiny-dungeon/Tiles/tile_0099.png");
		Renderer.getInstance().addTexture("box", "resources/textures/kenney_tiny-dungeon/Tiles/tile_0089.png");
		Renderer.getInstance().addTexture("wall", "resources/textures/kenney_tiny-dungeon/Tiles/tile_0040.png");
		Renderer.getInstance().addTexture("target", "resources/textures/kenney_tiny-dungeon/Tiles/tile_0101.png");
		
		// Read scene
		objects = SokobanSceneReader.readScene("resources/scenes/sokoban.txt");
		currPlan = gameObjToPlan(objects);
		dir = SokobanDirection.NONE;
	}

	@Override
	public void handleInput(EInputType input) {
		if (input == EInputType.INPUT_QUIT)
			shouldQuit = true;
		
		// Handle player input
		dir = SokobanDirection.NONE;
		if (input == EInputType.INPUT_RIGHT) {
			dir = SokobanDirection.RIGHT;
		}
		if (input == EInputType.INPUT_LEFT) {
			dir = SokobanDirection.LEFT;
		}
		if (input == EInputType.INPUT_UP) {
			dir = SokobanDirection.TOP;
		}
		if (input == EInputType.INPUT_DOWN) {
			dir = SokobanDirection.BOTTOM;
		}
	}

	@Override
	public void update() {
		// Update only once per X frames (instead of 30/60 FPS)
		currFrame++;
		if (currFrame != updateFrame) return;
		currFrame = 0;
		
		if (dir != SokobanDirection.NONE) {
			List<SokobanAbstractMove> moves = new ArrayList<>();
			moves.add(new SokobanPushMove(dir));
			moves.add(new SokobanWalkMove(dir));
			for (SokobanAbstractMove move : moves) {
				if (move.isValid(currPlan)) {
					currPlan = move.apply(currPlan);
					break;
				}
			}
			dir = SokobanDirection.NONE;
			this.refreshObjects();
		}
	}

	@Override
	public void render() {
		Renderer.getInstance().fillColor(50, 50, 50);
		for (SokobanGameObject obj : objects)
			obj.render();
	}

	@Override
	public boolean wantsToQuit() {
		// TODO Auto-generated method stub
		return shouldQuit;
	}
	
	public static void main(String[] args) {
		SokobanGameApplication sokobanLogic = new SokobanGameApplication();
		GameApplication gameApplication = new GameApplication(sokobanLogic, SokobanConstants.screenWidth, SokobanConstants.screenHeight, "SW1: Sokoban");
		gameApplication.run();
	}

}
