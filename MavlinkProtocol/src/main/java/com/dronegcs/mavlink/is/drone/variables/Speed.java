package com.dronegcs.mavlink.is.drone.variables;

import org.springframework.stereotype.Component;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.parameters.Parameter;

@Component
public class Speed extends DroneVariable {

	public static final int COLLISION_SECONDS_BEFORE_COLLISION = 2;
	public static final double COLLISION_DANGEROUS_SPEED_METERS_PER_SECOND = -3.0;
	public static final double COLLISION_SAFE_ALTITUDE_METERS = 1.0;
	private com.dronegcs.mavlink.is.units.Speed verticalSpeed = new com.dronegcs.mavlink.is.units.Speed(0);
	private com.dronegcs.mavlink.is.units.Speed groundSpeed = new com.dronegcs.mavlink.is.units.Speed(0);
	private com.dronegcs.mavlink.is.units.Speed airSpeed = new com.dronegcs.mavlink.is.units.Speed(0);
	private com.dronegcs.mavlink.is.units.Speed targetSpeed = new com.dronegcs.mavlink.is.units.Speed(0);
	
	private com.dronegcs.mavlink.is.units.Speed maxAirSpeed = new com.dronegcs.mavlink.is.units.Speed(0);


	public com.dronegcs.mavlink.is.units.Speed getVerticalSpeed() {
		return verticalSpeed;
	}

	public com.dronegcs.mavlink.is.units.Speed getGroundSpeed() {
		return groundSpeed;
	}

	public com.dronegcs.mavlink.is.units.Speed getAirSpeed() {
		return airSpeed;
	}

	public com.dronegcs.mavlink.is.units.Speed getTargetSpeed() {
		return targetSpeed;
	}
	
	public com.dronegcs.mavlink.is.units.Speed getMaxAirSpeed() {
		return maxAirSpeed;
	}

	public void setSpeedError(double aspd_error) {
		targetSpeed = new com.dronegcs.mavlink.is.units.Speed(aspd_error
				+ airSpeed.valueInMetersPerSecond());
	}

	public void setGroundAndAirSpeeds(double groundSpeed, double airSpeed, double climb) {
		this.groundSpeed = new com.dronegcs.mavlink.is.units.Speed(groundSpeed);
		this.airSpeed = new com.dronegcs.mavlink.is.units.Speed(airSpeed);
		this.verticalSpeed = new com.dronegcs.mavlink.is.units.Speed(climb);
		checkCollisionIsImminent();
		
		if (this.maxAirSpeed.valueInMetersPerSecond() < airSpeed) {
			this.maxAirSpeed = this.airSpeed;
		}
	}

	public com.dronegcs.mavlink.is.units.Speed getSpeedParameter(){
		Parameter param = drone.getParameters().getParameter("WPNAV_SPEED");
		if (param == null ) {
			return null;			
		}else{
			return new com.dronegcs.mavlink.is.units.Speed(param.getValue().doubleValue() / 100);
		}
			
	}
	
	/**
	 * if drone will crash in 2 seconds at constant climb rate and climb rate <
	 * -3 m/s and altitude > 1 meter
	 */
	private void checkCollisionIsImminent() {

		double altitude = drone.getAltitude().getAltitude();
		if (altitude + verticalSpeed.valueInMetersPerSecond() * COLLISION_SECONDS_BEFORE_COLLISION < 0
				&& verticalSpeed.valueInMetersPerSecond() < COLLISION_DANGEROUS_SPEED_METERS_PER_SECOND
				&& altitude > COLLISION_SAFE_ALTITUDE_METERS) {
			drone.getAltitude().setCollisionImminent(true);
		} else {
			drone.getAltitude().setCollisionImminent(false);
		}
	}

}
