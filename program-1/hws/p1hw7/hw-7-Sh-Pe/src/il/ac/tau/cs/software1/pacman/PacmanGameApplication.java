package il.ac.tau.cs.software1.pacman;

import java.util.*;

import il.ac.tau.cs.software1.core.*;
import il.ac.tau.cs.software1.framework.*;
import il.ac.tau.cs.software1.math.*;


public class PacmanGameApplication implements IGameLogic {
	private boolean shouldQuit = false;
	
	private final int updateFrame = 5;
	private int currFrame = 0;
	
	private PacmanPlayer player;
	private Vector2 playerRequestedVelocity = new Vector2();
	private List<PacmanGameObject> objects;
	
	
	public void init() {
		// Load texture assets
		Renderer.getInstance().addTexture("cat", "resources/textures/Cat03.jpg");
		Renderer.getInstance().addTexture("player", "resources/textures/kenney_tiny-dungeon/Tiles/tile_0112.png");
		Renderer.getInstance().addTexture("wall", "resources/textures/kenney_tiny-dungeon/Tiles/tile_0040.png");
		Renderer.getInstance().addTexture("bonus-dot", "resources/textures/kenney_tiny-dungeon/Tiles/tile_0101.png");
		Renderer.getInstance().addTexture("bonus-potion", "resources/textures/kenney_tiny-dungeon/Tiles/tile_0115.png");
		Renderer.getInstance().addTexture("ghost", "resources/textures/kenney_tiny-dungeon/Tiles/tile_0121.png");
		Renderer.getInstance().addTexture("ghost-scared", "resources/textures/kenney_tiny-dungeon/Tiles/tile_0108.png");
		
		// Read scene
		objects = PacmanSceneReader.readScene("resources/scenes/first.txt");
		for (PacmanGameObject obj : objects)
			if (obj instanceof PacmanPlayer) {
				player = (PacmanPlayer)obj;
				break;
			}
	}
	
	public void handleInput(EInputType input) {
		if (input == EInputType.INPUT_QUIT)
			shouldQuit = true;
		
		
		// Handle player input
		if (input == EInputType.INPUT_RIGHT) {
			playerRequestedVelocity.y = 0.f;
			playerRequestedVelocity.x = 1.f;
		}
		if (input == EInputType.INPUT_LEFT) {
			playerRequestedVelocity.y = 0.f;
			playerRequestedVelocity.x = -1.f;
		}
		if (input == EInputType.INPUT_UP) {
			playerRequestedVelocity.x = 0.f;
			playerRequestedVelocity.y = 1.f;
		}
		if (input == EInputType.INPUT_DOWN) {
			playerRequestedVelocity.x = 0.f;
			playerRequestedVelocity.y = -1.f;
		}
	}
	
	public void update() {
		// Update only once per X frames (instead of 30/60 FPS)
		currFrame++;
		if (currFrame != updateFrame) return;
		currFrame = 0;
		
		
		// The player asked to move in some direction.
		// But we will only change the actual velocity,
		// if moving one step in the requested direction is allowed.
		// Otherwise, remember kindly the request, but keep going as usual.
		// Also note that collision detection is done in screen space coordinates,
		// hence we need to adjust the velocity accordingly.
		Vector2 adjustedRequestedVelocity = new Vector2(
				playerRequestedVelocity.x * PacmanConstants.cellWidth,
				playerRequestedVelocity.y * PacmanConstants.cellHeight
		);
		if (CollisionDetection.canPerformTick(
				player, objects, adjustedRequestedVelocity, 
				(obj) -> obj instanceof PacmanObstacle))
			player.getVelocityComponent().velocity = playerRequestedVelocity.copy();
		///////////////////////////////////
		
		for (PacmanGameObject obj : objects)
			obj.update();
		
		CollisionDetection.collide(objects);
		objects.removeIf(obj -> obj.getShouldDelete());
	}
	
	public void render() {
//		Renderer.getInstance().fillColor(100, 149, 237); // Cornflower Blue
		Renderer.getInstance().fillColor(50, 50, 50);
		
		for (PacmanGameObject obj : objects)
			obj.render();
	}
	
	public boolean wantsToQuit() {
		return shouldQuit;
	}
	
	public static void main(String[] args) {
		PacmanGameApplication pacmanLogic = new PacmanGameApplication();
		GameApplication gameApplication = new GameApplication(pacmanLogic, PacmanConstants.screenWidth, PacmanConstants.screenHeight, "SW1: Pac-Man!");
		gameApplication.run();
	}
}
