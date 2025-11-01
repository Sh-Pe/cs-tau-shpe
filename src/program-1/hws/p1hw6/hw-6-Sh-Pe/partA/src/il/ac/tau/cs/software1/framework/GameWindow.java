package il.ac.tau.cs.software1.framework;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import il.ac.tau.cs.software1.core.EInputType;

import java.nio.*;
import java.util.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;


public class GameWindow {
	private long windowHandle;
	private List<EInputType> activeInputs = new ArrayList<EInputType>();
	
	private String windowTitle;
	
	public GameWindow(int screenWidth, int screenHeight, String windowTitle) {
		this.windowTitle = windowTitle;
		
		// Initialize LWJGL & GLFW
		GLFWErrorCallback.createPrint(System.err).set();
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");
		glfwDefaultWindowHints(); 
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
	    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		
		// Create the window
		windowHandle = glfwCreateWindow(screenWidth, screenHeight, this.windowTitle, NULL, NULL);
		if (windowHandle == NULL)
			throw new RuntimeException("Failed to create the GLFW window");
		
		// Center the window
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			glfwGetWindowSize(windowHandle, pWidth, pHeight);
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(
				windowHandle,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		}
		
		glfwMakeContextCurrent(windowHandle);
		glfwSwapInterval(1);
		glfwShowWindow(windowHandle);
		
		GL.createCapabilities();
		
		glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
			this.keyCallback(window, key, scancode, action, mods);
		});
		
		Renderer.getInstance().setScreenWidth(screenWidth);
		Renderer.getInstance().setScreenHeight(screenHeight);
	}
	
	public boolean shouldRun() {
		return !glfwWindowShouldClose(windowHandle);
	}
	
	public void beginRender() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void endRender() {
		glfwSwapBuffers(windowHandle);
		glfwPollEvents();
	}
	
	public void shutdown() {
		glfwFreeCallbacks(windowHandle);
		glfwDestroyWindow(windowHandle);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public List<EInputType> getInputs() {
		return activeInputs;
	}
	
	
	private void keyCallback(long windowHandle, int key, int scancode, int action, int mods) {
		EInputType input = null;
		if (key == GLFW_KEY_UP || key == GLFW_KEY_W)
			input = EInputType.INPUT_UP;
		if (key == GLFW_KEY_DOWN || key == GLFW_KEY_S)
			input = EInputType.INPUT_DOWN;
		if (key == GLFW_KEY_RIGHT || key == GLFW_KEY_D)
			input = EInputType.INPUT_RIGHT;
		if (key == GLFW_KEY_LEFT || key == GLFW_KEY_A)
			input = EInputType.INPUT_LEFT;
		if (key == GLFW_KEY_SPACE || key == GLFW_KEY_ENTER)
			input = EInputType.INPUT_SELECT;
		if (key == GLFW_KEY_ESCAPE)
			input = EInputType.INPUT_QUIT;
		if (input == null) return;
		
		
		if (action == GLFW_PRESS && !activeInputs.contains(input))  {
			activeInputs.add(input);
		}
		if (action == GLFW_RELEASE) {
			activeInputs.remove(input);
		}
	}
}
