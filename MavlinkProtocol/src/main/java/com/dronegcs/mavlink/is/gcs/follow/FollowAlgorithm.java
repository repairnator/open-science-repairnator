package com.dronegcs.mavlink.is.gcs.follow;

import com.dronegcs.mavlink.core.gcs.follow.FollowAbove;
import com.dronegcs.mavlink.core.gcs.follow.FollowCircle;
import com.dronegcs.mavlink.core.gcs.follow.FollowLead;
import com.dronegcs.mavlink.core.gcs.follow.FollowLeash;
import com.dronegcs.mavlink.core.gcs.follow.FollowLeft;
import com.dronegcs.mavlink.core.gcs.follow.FollowRight;
import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.location.Location;

public abstract class FollowAlgorithm {
	public abstract void processNewLocation(Location location);

	public abstract FollowModes getType();

	protected Drone drone;
	protected double radius;

	public FollowAlgorithm(Drone drone, double radius) {
		super();
		this.drone = drone;
		this.radius = radius;
	}

	public void changeRadius(Double radius) {
		this.radius = Math.max(0, radius);
	}

	public enum FollowModes {
		LEASH("Leash"), LEAD("Lead"), RIGHT("Right"), LEFT("Left"), CIRCLE("Orbit"), ABOVE("Above");

		private String name;

		FollowModes(String str) {
			name = str;
		}

		@Override
		public String toString() {
			return name;
		}

		public FollowModes next() {
			return values()[(ordinal() + 1) % values().length];
		}

		public FollowAlgorithm getAlgorithmType(Drone drone) {
			switch (this) {
			case LEASH:
				return new FollowLeash(drone, 8.0);
			case LEAD:
				return new FollowLead(drone, 15.0);
			case RIGHT:
				return new FollowRight(drone, 10.0);
			case LEFT:
				return new FollowLeft(drone, 10.0);
			case CIRCLE:
				return new FollowCircle(drone, 15.0, 10.0);
			case ABOVE:
				return new FollowAbove(drone, 0.0);
			}
			return null; // Should never reach this
		}
	}

}
