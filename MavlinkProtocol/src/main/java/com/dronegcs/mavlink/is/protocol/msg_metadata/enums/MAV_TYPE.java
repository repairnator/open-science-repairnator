/** 
*/
package com.dronegcs.mavlink.is.protocol.msg_metadata.enums;

/*
MAVLINK component type reported in HEARTBEAT message.
Flight controllers must report the type of the vehicle on which they are mounted (e.g. MAV_TYPE_OCTOROTOR).
All other components must report a value appropriate for their type (e.g. a camera must use MAV_TYPE_CAMERA).
 */
public enum MAV_TYPE {
	
	MAV_TYPE_GENERIC(0), /* Generic micro air vehicle. | */
	MAV_TYPE_FIXED_WING(1), /* Fixed wing aircraft. | */
	MAV_TYPE_QUADROTOR(2), /* Quadrotor | */
	MAV_TYPE_COAXIAL(3), /* Coaxial helicopter | */
	MAV_TYPE_HELICOPTER(4), /* Normal helicopter with tail rotor. | */
	MAV_TYPE_ANTENNA_TRACKER(5), /* Ground installation | */
	MAV_TYPE_GCS(6), /* Operator control unit / ground control station | */
	MAV_TYPE_ROCKET(9), /* Rocket | */
	MAV_TYPE_HEXAROTOR(13), /* Hexarotor | */
	MAV_TYPE_OCTOROTOR(14), /* Octorotor | */
	MAV_TYPE_TRICOPTER(15), /* Octorotor | */
	MAV_TYPE_FLAPPING_WING(16), /* Flapping wing | */
	MAV_TYPE_ONBOARD_CONTROLLER(18); /* Onboard companion controller | */

	private final int type;

	MAV_TYPE(int type){
		this.type = type;
	}

	public static MAV_TYPE getType(int type) {
		for (MAV_TYPE val : MAV_TYPE.values())
			if (val.type == type)
				return val;
		return MAV_TYPE_GENERIC;
	}

	public int getIndex() {
		return type;
	}
}
