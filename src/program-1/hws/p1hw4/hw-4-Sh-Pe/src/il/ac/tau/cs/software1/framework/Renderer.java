package il.ac.tau.cs.software1.framework;

import java.util.*;

import static org.lwjgl.opengl.GL33.*;

import il.ac.tau.cs.software1.math.*;

/*
 * Singleton class for various rendering methods 
 */
public class Renderer {
	
	private static Renderer _instance = null;
	private Renderer() {}
	
	private DrawableRectangle drawableRectangle;
	private Shader shader;
	private Map<String, Texture> textures = new HashMap<>();
	
	private int screenWidth;
	private int screenHeight;
	
	public static synchronized Renderer getInstance() {
		if (_instance == null) {
			_instance = new Renderer();
		}
		return _instance;
	}
	
	public void init() {
		drawableRectangle = new DrawableRectangle();
		shader = new Shader();
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);  
	}
	
	public int getScreenWidht() { return screenWidth; }
	public int getScreenHeight() { return screenHeight; }
	public void setScreenWidth(int width) { screenWidth = width; }
	public void setScreenHeight(int height) { screenHeight = height; }
	
	// Fill background color with RGB values (between 0.0 and 1.0)
	public void fillColor(float r, float g, float b) {
		glClearColor(r, g, b, 1.0f);
	}
	
	// Fill background color with RGB values (between 0 and 255)
	public void fillColor(int r, int g, int b) {
		this.fillColor(r / 255.f, g / 255.f, b / 255.f);
	}
	
	public void drawRectangle(String textureName, Vector2 position, Vector2 scale) {
		shader.use();
		shader.setUniform("diffuseImage", 0);
		shader.setUniform("offset", position);
		shader.setUniform("scale", scale);
		textures.get(textureName).use();
		drawableRectangle.render();
	}
		
	public void addTexture(String name, String imagePath) {
		textures.put(name, new Texture(imagePath));
	}

}
