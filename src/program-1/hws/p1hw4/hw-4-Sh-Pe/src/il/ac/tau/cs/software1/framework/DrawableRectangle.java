package il.ac.tau.cs.software1.framework;

import java.nio.*;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL33.*;

public class DrawableRectangle {
	private int vao;
	private int vbo;
	
	public DrawableRectangle() {
		float[] vertices = new float[] {
				0.f, 0.f, 0.f,
				1.f, 0.f, 0.f,
				1.f, 1.f, 0.f,
				
				0.f, 0.f, 0.f,
				1.f, 1.f, 0.f,
				0.f, 1.f, 0.f};
		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
		verticesBuffer.put(vertices);
		verticesBuffer.flip();
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		
		glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 3*Float.BYTES, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
				
		glBindVertexArray(0);
	}
	
	public void render() {
		glBindVertexArray(vao);
		glDrawArrays(GL_TRIANGLES, 0, 6);
		glBindVertexArray(0);
	}
}
