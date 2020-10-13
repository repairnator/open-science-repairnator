package com.dronegcs.mavlink.is.drone;

import org.springframework.beans.factory.annotation.Autowired;

public class DroneVariable {
	
	@Autowired
	protected transient Drone drone;

	public DroneVariable() {

	}
	
	public DroneVariable(DroneVariable dv) {
		drone = dv.drone;
	}

	public void setDrone(Drone myDrone) {
		drone = myDrone;
	}
}