package com.dronegcs.mavlink.is.location;

public interface LocationFinder {
	
	void enableLocationUpdates();

	void disableLocationUpdates();

	void addLocationListener(LocationReceiver receiver);
	
	void removeLocationListener(LocationReceiver receiver);
}
