package com.dronegcs.mavlink.core.drone.profile;

import com.dronegcs.mavlink.is.drone.profiles.VehicleProfile;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_COPTER;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_I;

public class ArduCopterProfile extends VehicleProfile {
	
	public ArduCopterProfile() {
		super.getDefault().setMaxAltitude(100);
		super.getDefault().setWpNavSpeed(3);
	}

	@Override
	public MAV_PARAM_I[] getParametersMetadataList() {
		return MAV_PARAM_COPTER.values();
	}
}
