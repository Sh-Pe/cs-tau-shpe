package il.ac.tau.cs.software1.framework;

import java.util.*;

import static org.lwjgl.opengl.GL33.*;

import il.ac.tau.cs.software1.math.*;

public class Shader {
	private String vsSource = """
			#version 330 core
			layout (location = 0) in vec3 pos;
			
			uniform vec2 offset;
			uniform vec2 scale;
			
			out vec2 uv;
			
			void main() {
				gl_Position = vec4(pos.xy * scale + offset, 0.0, 1.0);
				uv = pos.xy; 
				
			}
			""";
	private String fsSource = """
			#version 330 core
			
			in vec2 uv;
			out vec4 fragColor;
			
			uniform sampler2D diffuseImage;
			
			void main() {
				fragColor = texture(diffuseImage, uv);
			} 
			""";
	private int id;
	private Map<String, Integer> uniformLocations = new HashMap<>();
	
	
	public Shader() {
		int vs = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vs, vsSource);
		glCompileShader(vs);
		if(glGetShaderi(vs, GL_COMPILE_STATUS) == GL_FALSE)
			throw new RuntimeException(glGetShaderInfoLog(vs));
		
		int fs = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fs, fsSource);
		glCompileShader(fs);
		if(glGetShaderi(fs, GL_COMPILE_STATUS) == GL_FALSE)
			throw new RuntimeException(glGetShaderInfoLog(fs));
		
		id = glCreateProgram();
		glAttachShader(id, vs);
		glAttachShader(id, fs);
		glLinkProgram(id);
		if(glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE)
			throw new RuntimeException(glGetProgramInfoLog(id));
	}
	
	public void use() {
		glUseProgram(id);
	}
	
	private int getUniformLocation(String name) {
		if (!uniformLocations.containsKey(name))
			uniformLocations.put(name, glGetUniformLocation(id, name));
		return uniformLocations.get(name);
	}
	
	public void setUniform(String name, int n) {
		glUniform1i(getUniformLocation(name), n);
	}
	public void setUniform(String name, float x) {
		glUniform1f(getUniformLocation(name), x);
	}
	
	public void setUniform(String name, float x, float y) {
		glUniform2f(getUniformLocation(name), x, y);
	}
	public void setUniform(String name, Vector2 vec) {
		glUniform2f(getUniformLocation(name), vec.x, vec.y);
	}
	
	public void setUniform(String name, float x, float y, float z) {
		glUniform3f(getUniformLocation(name), x, y, z);
	}
	public void setUniform(String name, Vector3 vec) {
		glUniform3f(getUniformLocation(name), vec.x, vec.y, vec.z);
	}
	
	
	public void setUniform(String name, float x, float y, float z, float w) {
		glUniform4f(getUniformLocation(name), x, y, z, w);
	}
	public void setUniform(String name, Vector4 vec) {
		glUniform4f(getUniformLocation(name), vec.x, vec.y, vec.z, vec.w);
	}
	
}
