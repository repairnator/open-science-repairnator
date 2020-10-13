package com.dronegcs.mavlink.is.gcs.roi;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.Handler;
import com.dronegcs.mavlink.is.location.Location;
import com.dronegcs.mavlink.is.location.LocationReceiver;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkROI;
import com.geo_tools.Coordinate;
import com.geo_tools.GeoTools;

/**
 * Uses location data from Android's FusedLocation LocationManager at 1Hz and
 * calculates new points at 10Hz based on Last Location and Last Velocity.
 * 
 */
@Component
public class ROIEstimator implements LocationReceiver {

	private static final int TIMEOUT = 100;
	private Location realLocation;
	private long timeOfLastLocation;

	@Autowired @NotNull(message = "Internal Error: Failed to get drone")
	private Drone drone;
	
	@Autowired @NotNull(message = "Internal Error: Failed to get connection handler")
	private Handler handler;
	
	public Runnable watchdogCallback = () -> updateROI();

	public void disableLocationUpdates() {
		handler.removeCallbacks(watchdogCallback);
	}

	@Override
	public void onLocationChanged(Location location) {
		disableLocationUpdates();
		realLocation = location;
		timeOfLastLocation = System.currentTimeMillis();
		updateROI();
	}

	private void updateROI() {
		if (realLocation == null) {
			return;
		}
		Coordinate gcsCoord = new Coordinate(realLocation.getCoord().getLat(), realLocation.getCoord().getLon());

		double bearing = realLocation.getBearing();
		double distanceTraveledSinceLastPoint = realLocation.getSpeed() * (System.currentTimeMillis() - timeOfLastLocation) / 1000f;
		Coordinate goCoord = GeoTools.newCoordFromBearingAndDistance(gcsCoord, bearing,
				distanceTraveledSinceLastPoint);
		if (distanceTraveledSinceLastPoint > 0.0) {
			MavLinkROI.setROI(drone, new Coordinate(goCoord.getLat(), goCoord.getLon(), 1.0));
		}
		handler.postDelayed(watchdogCallback, TIMEOUT);

	}
}
