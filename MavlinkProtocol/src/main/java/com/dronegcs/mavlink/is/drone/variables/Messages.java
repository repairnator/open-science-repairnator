package com.dronegcs.mavlink.is.drone.variables;

import com.dronegcs.mavlink.is.drone.DroneInterfaces;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Component
public class Messages extends DroneVariable {

	private Map<DroneInterfaces.OnDroneListener, Vector<String>> messages;
	
	static int called;

	@PostConstruct
	public void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");
		messages = new HashMap<>();
	}

	public void addMessageQueue(DroneInterfaces.OnDroneListener listener) {
		messages.put(listener, new Vector<>());
	}

	public void removeMessageQueue(DroneInterfaces.OnDroneListener listener) {
		messages.remove(listener);
	}
	
	public String pop(DroneInterfaces.OnDroneListener listener) {
		if (messages == null)
			return null;
		
		if (messages.isEmpty())
			return null;
		
		Vector<String> res = messages.get(listener);
		if (res == null || res.isEmpty())
			return null;

		return res.remove(0);
	}
	
	public void push(String text) {
		for (DroneInterfaces.OnDroneListener listener : messages.keySet()) {
			messages.get(listener).addElement(text);
		}
		drone.notifyDroneEvent(DroneEventsType.TEXT_MESSEGE);
	}
}
