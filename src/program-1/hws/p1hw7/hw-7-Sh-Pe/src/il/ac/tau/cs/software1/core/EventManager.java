package il.ac.tau.cs.software1.core;

import java.util.*;

public class EventManager {
	Map<String, List<EventSubscription>> subscribers = new HashMap<>();
	private static EventManager instance;

	private EventManager() {}

	public void subscribe(String event, GameObject subscriber, IEventCallback callback) {
		if (!subscribers.containsKey(event)) {
			subscribers.put(event, new ArrayList<>());
		}
		subscribers.get(event).add(new EventSubscription(
			subscriber,
			callback
		));
	}
	
	public void notifyEvent(String event, GameObject publisher, Object data) {
		if (subscribers.containsKey(event)) {
			for (EventSubscription subscription : subscribers.get(event)) {
				subscription.callback.call(new EventData(publisher, data));
			}
		}
	}
	
	// --------------------------------------- (Singleton)
	/* Q1 */

	public static EventManager getInstance() {
		if (instance == null) {
			instance = new EventManager();
		}
		return instance;
	}

}
