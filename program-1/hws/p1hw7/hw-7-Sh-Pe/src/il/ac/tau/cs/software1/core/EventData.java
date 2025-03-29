package il.ac.tau.cs.software1.core;

public class EventData {
	public GameObject publisher;
	public Object data = null; // Optional additional data
	
	public EventData(GameObject publisher, Object data) {
		this.publisher = publisher;
		this.data = data;
	}
}
