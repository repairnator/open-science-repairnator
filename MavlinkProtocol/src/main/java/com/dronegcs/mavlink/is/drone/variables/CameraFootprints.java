package com.dronegcs.mavlink.is.drone.variables;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dronegcs.mavlink.core.survey.Footprint;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.drone.mission.survey.CameraInfo;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_camera_feedback;

@Component
public class CameraFootprints extends DroneVariable {

	private CameraInfo camera = new CameraInfo();
	private List<Footprint> footprints = new ArrayList<Footprint>();

	public void newImageLocation(msg_camera_feedback msg) {
		footprints.add(new Footprint(camera,msg));
		drone.notifyDroneEvent(DroneEventsType.FOOTPRINT);
	}

	public Footprint getLastFootprint() {
		return footprints.get(footprints.size()-1);
	}

}
