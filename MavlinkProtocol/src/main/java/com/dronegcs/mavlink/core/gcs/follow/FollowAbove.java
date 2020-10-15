package com.dronegcs.mavlink.core.gcs.follow;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.gcs.follow.FollowAlgorithm;
import com.dronegcs.mavlink.is.location.Location;
import com.geo_tools.Coordinate;

public class FollowAbove extends FollowAlgorithm {

	public FollowAbove(Drone drone, double radius) {
		super(drone, radius);
	}

	@Override
	public FollowModes getType() {
		return FollowModes.ABOVE;
	}

	@Override
	public void processNewLocation(Location location) {
		Coordinate gcsCoord = new Coordinate(location.getCoord().getLat(), location.getCoord().getLon());
		drone.getGuidedPoint().newGuidedCoord(gcsCoord);
	}

}
