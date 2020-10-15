package com.dronegcs.mavlink.core.connection.helper;

import com.geo_tools.Coordinate;

public class BeaconData {
	
	private Coordinate coordinate = null;
	private int fetchTime = 0;
	
	public BeaconData(Coordinate coordinate, int fetchTime) {
		this.coordinate = coordinate;
		this.fetchTime = fetchTime;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public int getFetchTime() {
		return fetchTime;
	}
}
