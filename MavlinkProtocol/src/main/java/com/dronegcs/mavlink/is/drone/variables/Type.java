package com.dronegcs.mavlink.is.drone.variables;

import javax.validation.constraints.NotNull;

import com.generic_tools.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dronegcs.mavlink.core.firmware.FirmwareType;
import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneInterfaces;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_TYPE;

@Component
public class Type extends DroneVariable implements DroneInterfaces.OnDroneListener {
	
	private static final MAV_TYPE DEFAULT_TYPE = MAV_TYPE.MAV_TYPE_GENERIC;
	
	@Autowired
	@NotNull(message = "Internal Error: Failed to get com.dronegcs.gcsis.logger")
	private Logger logger;

	private MAV_TYPE droneType = DEFAULT_TYPE;
	private String firmwareVersion = null;

	static int called;
	public void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");
		drone.addDroneListener(this);
	}

	public void setDroneType(MAV_TYPE droneType) {
		if (this.droneType != droneType) {
			this.droneType = droneType;
			drone.loadVehicleProfile();
			drone.notifyDroneEvent(DroneEventsType.TYPE);
		}
	}

	public MAV_TYPE getDroneType() {
		return droneType;
	}

	public FirmwareType getFirmwareType() {
//		if (drone.getMavClient().isConnected()) {
			switch (this.droneType) {

			case MAV_TYPE_FIXED_WING:
				return FirmwareType.ARDU_PLANE;

			case MAV_TYPE_GENERIC:
			case MAV_TYPE_QUADROTOR:
			case MAV_TYPE_COAXIAL:
			case MAV_TYPE_HELICOPTER:
			case MAV_TYPE_HEXAROTOR:
			case MAV_TYPE_OCTOROTOR:
			case MAV_TYPE_TRICOPTER:
				return FirmwareType.ARDU_COPTER;

			default:
				// unsupported - fall thru to offline condition
				logger.LogErrorMessege("Unsupported Profile");
				return FirmwareType.ARDU_COPTER;
			}
//		}
//		return drone.getPreferences().getVehicleType(); // offline or
															// unsupported
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String message) {
		firmwareVersion = message;
		drone.notifyDroneEvent(DroneEventsType.FIRMWARE);
	}

	public boolean isCopter() {
		return Type.isCopter(this.droneType);
	}

    public static boolean isCopter(MAV_TYPE type){
        switch (type) {
            case MAV_TYPE_TRICOPTER:
            case MAV_TYPE_QUADROTOR:
            case MAV_TYPE_HEXAROTOR:
            case MAV_TYPE_OCTOROTOR:
            case MAV_TYPE_HELICOPTER:
                return true;

            default:
                return false;
        }
    }

	public boolean isPlane() {
		return Type.isPlane(this.droneType);
	}

	public static boolean isPlane(MAV_TYPE type){
        return type == MAV_TYPE.MAV_TYPE_FIXED_WING;
    }

    @SuppressWarnings("incomplete-switch")
	@Override
    public void onDroneEvent(DroneEventsType event, Drone drone) {
        switch(event){
            case DISCONNECTED:
                setDroneType(DEFAULT_TYPE);
                break;
        }
    }
}