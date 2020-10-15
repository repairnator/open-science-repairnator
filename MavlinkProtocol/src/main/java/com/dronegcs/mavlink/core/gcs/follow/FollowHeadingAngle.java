package com.dronegcs.mavlink.core.gcs.follow;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.gcs.follow.FollowAlgorithm;
import com.dronegcs.mavlink.is.location.Location;
import com.geo_tools.Coordinate;
import com.geo_tools.GeoTools;

public abstract class FollowHeadingAngle extends FollowAlgorithm {

	protected double angleOffset;

	protected FollowHeadingAngle(Drone drone, double radius, double angleOffset) {
		super(drone, radius);
		this.angleOffset = angleOffset;
	}

	@Override
	public void processNewLocation(Location location) {

		Coordinate gcsCoord = new Coordinate(location.getCoord().getLat(), location.getCoord().getLon());
		double bearing = location.getBearing();

		Coordinate goCoord = GeoTools.newCoordFromBearingAndDistance(gcsCoord, bearing + angleOffset, radius);
		drone.getGuidedPoint().newGuidedCoord(goCoord);
	}

}