package il.ac.tau.cs.software1.components;

import il.ac.tau.cs.software1.math.*;

public class ImageComponent implements IComponent {
	public Vector4 colorOverlay = new Vector4(1.f, 1.f, 1.f, 1.f);
	public boolean hasImage = true;
	public String imageName;
	
	public ImageComponent(String imageName) {
		this.imageName = imageName;
	}
	
}
