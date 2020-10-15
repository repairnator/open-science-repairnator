package com.dronegcs.mavlink.is.location;

import com.geo_tools.Coordinate;

public class Location {

	private Coordinate coordinate;
	private double heading = 0.0;
	private double speed = 0.0;
    private boolean isAccurate;

    public Location(Coordinate coord2d){
    	coordinate = coord2d;
    }
    
	public Location(Coordinate coord2d, float heading, float speed, boolean isAccurate) {
		coordinate = coord2d;
		this.heading = heading;
		this.speed = speed;
        this.isAccurate = isAccurate;
	}

	public Coordinate getCoord() {
		return coordinate;
	}

    public boolean isAccurate(){
        return this.isAccurate;
    }

	public double getBearing() {
		return heading;
	}

	public double getSpeed() {
		return speed;
	}

}
