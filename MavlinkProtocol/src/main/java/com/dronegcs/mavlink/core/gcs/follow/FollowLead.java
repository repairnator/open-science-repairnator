package com.dronegcs.mavlink.core.gcs.follow;

import com.dronegcs.mavlink.is.drone.Drone;

public class FollowLead extends FollowHeadingAngle {

	public FollowLead(Drone drone, double radius) {
		super(drone, radius, 0.0);
	}

	@Override
	public FollowModes getType() {
		return FollowModes.LEAD;
	}

}
