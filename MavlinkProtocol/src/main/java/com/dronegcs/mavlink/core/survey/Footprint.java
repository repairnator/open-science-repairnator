package com.dronegcs.mavlink.core.survey;

import java.util.ArrayList;
import java.util.List;

import com.dronegcs.mavlink.is.drone.mission.survey.CameraInfo;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_camera_feedback;
import com.geo_tools.Coordinate;
import com.geo_tools.GeoTools;

public class Footprint {
	
	private List<Coordinate> vertex = new ArrayList<Coordinate>();
	private Coordinate center;

	public Footprint(CameraInfo camera, msg_camera_feedback msg) {
		System.out.println("Footprint " + msg);
		center = new Coordinate(msg.lat/1E7,msg.lng/1E7);
		float yaw = msg.yaw;
		vertex.add(GeoTools.newCoordFromBearingAndDistance(center, yaw-60, 30));
		vertex.add(GeoTools.newCoordFromBearingAndDistance(center, yaw+60, 30));
		vertex.add(GeoTools.newCoordFromBearingAndDistance(center, yaw+120, 30));
		vertex.add(GeoTools.newCoordFromBearingAndDistance(center, yaw-120, 30));
	}

	public Coordinate getCenter() {
		return vertex.get(0);
	}

	public List<Coordinate> getVertex() {
		return vertex;
	}	

}
