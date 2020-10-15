package com.dronegcs.mavlink.core.drone.profile;

import com.dronegcs.mavlink.is.drone.profiles.VehicleProfile;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_I;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_PLANE;

public class ArduPlaneProfile extends VehicleProfile {

	public ArduPlaneProfile() {
		super.getDefault().setMaxAltitude(100);
	}

	@Override
	public MAV_PARAM_I[] getParametersMetadataList() {
		return MAV_PARAM_PLANE.values();
	}
}
