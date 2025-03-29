package il.ac.tau.cs.software1.core;

public interface IGameLogic {
	
	// Initialization of the game, i.e., asset loading, etc.
	public void init();
	
	// Handle a single input event (key/button press)
	public void handleInput(EInputType input);
	
	// Update one tick of the game loop
	public void update();
	
	// Draw elements on screen
	public void render();
	
	// Used to quit the software from within the game logic
	public boolean wantsToQuit();
}
