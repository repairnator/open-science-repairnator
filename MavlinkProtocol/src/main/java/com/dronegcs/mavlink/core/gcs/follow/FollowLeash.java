package com.dronegcs.mavlink.core.gcs.follow;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.gcs.follow.FollowAlgorithm;
import com.dronegcs.mavlink.is.location.Location;
import com.geo_tools.Coordinate;
import com.geo_tools.GeoTools;

public class FollowLeash extends FollowAlgorithm {

	public FollowLeash(Drone drone, double radius) {
		super(drone, radius);
	}

	@Override
	public FollowModes getType() {
		return FollowModes.LEASH;
	}

	@Override
	public void processNewLocation(Location location) {
		final Coordinate locationCoord = location.getCoord();
		final Coordinate dronePosition = drone.getGps().getPosition();

		if (locationCoord == null || dronePosition == null) {
			return;
		}

		final double radiusInMeters = radius;
		if (GeoTools.getDistance(locationCoord, dronePosition) > radiusInMeters) {
			double headingGCStoDrone = GeoTools.getHeadingFromCoordinates(locationCoord,
					dronePosition);
			Coordinate goCoord = GeoTools.newCoordFromBearingAndDistance(locationCoord,
					headingGCStoDrone, radiusInMeters);
			drone.getGuidedPoint().newGuidedCoord(goCoord);
		}
	}

}
