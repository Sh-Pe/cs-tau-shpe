package il.ac.tau.cs.software1.framework;

import java.nio.*;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
	
	private int id;
	
	public Texture(String imagePath) {
		int[] x = new int[1];
		int[] y = new int[1];
		int[] n = new int[1];
		stbi_set_flip_vertically_on_load(true);
		ByteBuffer imageData = stbi_load(imagePath, x, y, n, 0);
		if (imageData == null)
			throw new RuntimeException("Could not load texture: " + imagePath);
		
		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		int type = GL_RGB;
		if (n[0] > 3) type = GL_RGBA;
		glTexImage2D(GL_TEXTURE_2D, 0, type, x[0], y[0], 0, type, GL_UNSIGNED_BYTE, imageData);
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_MIRRORED_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		stbi_image_free(imageData);
	}
	
	public void use() {
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, id);
	}
}
