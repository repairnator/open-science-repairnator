package com.dronegcs.mavlink.is.drone.variables;

import org.springframework.stereotype.Component;

import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.geo_tools.Coordinate;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;

@Component
public class GCS extends DroneVariable {

	private final int DEFAULT_GCS_ID = 255;
	
	private Coordinate pLastPosition = null;

	private int id = DEFAULT_GCS_ID;

	public Coordinate getPosition() {
		return pLastPosition;
	}

	public void setPosition(Coordinate position) {
		this.pLastPosition = position;
	}
	
	public void UpdateAll() {
		drone.notifyDroneEvent(DroneEventsType.GCS_LOCATION);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
