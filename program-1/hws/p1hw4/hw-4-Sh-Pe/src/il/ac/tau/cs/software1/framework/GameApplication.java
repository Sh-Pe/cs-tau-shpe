package il.ac.tau.cs.software1.framework;

import java.util.*;

import static org.lwjgl.glfw.GLFW.*;

import il.ac.tau.cs.software1.core.*;

public class GameApplication {
	private IGameLogic gameLogic;
	private GameWindow window;
	private int fps;
	
	private double lastTime = 0.0;
	private double currTime = 0.0;

	
	public GameApplication(IGameLogic gameLogic, int screenWidth, int screenHeight, String windowTitle, int fps) {
		this.gameLogic = gameLogic;
		this.fps = fps;
		window = new GameWindow(screenWidth, screenHeight, windowTitle);
		Renderer.getInstance().init();
		lastTime = glfwGetTime();
		currTime = glfwGetTime();
	}
	
	public GameApplication(IGameLogic gameLogic, int screenWidth, int screenHeight, String windowTitle) {
		this(gameLogic, screenWidth, screenHeight, windowTitle, 30);
	}
	
	public void run() {
		gameLogic.init();
		
		
		while (window.shouldRun()) {
			currTime = glfwGetTime();
			
			if ((currTime - lastTime) < 1.0 / fps) continue; 
						
			
			List<EInputType> inputs = window.getInputs();
			for(EInputType input : inputs) {
				gameLogic.handleInput(input);
			}
			gameLogic.update();
			
			
			window.beginRender();
			gameLogic.render();
			window.endRender();
			
			lastTime = glfwGetTime();
			
			if (gameLogic.wantsToQuit()) break;
		}
		
		window.shutdown();
	}
}
