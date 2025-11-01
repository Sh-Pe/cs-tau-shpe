package il.ac.tau.cs.software1.core;

public class EventSubscription {
	public GameObject subscriber;
	public IEventCallback callback;
	
	public EventSubscription(GameObject subscriber, IEventCallback callback) {
		this.subscriber = subscriber;
		this.callback = callback;
	}
}
