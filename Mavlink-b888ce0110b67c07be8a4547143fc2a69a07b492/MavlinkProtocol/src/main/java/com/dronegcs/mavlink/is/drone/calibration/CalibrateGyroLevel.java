package com.dronegcs.mavlink.is.drone.calibration;


import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneInterfaces;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_command_long;
import org.springframework.stereotype.Component;

import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_statustext;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkCalibration;

@Component
public class CalibrateGyroLevel extends DroneVariable implements DroneInterfaces.OnDroneListener, Calibration {

	private boolean calibrating;

	@Override
	public boolean start() {
		if(drone.getState().isFlying()) {
			return false;
		}

		MavLinkCalibration.sendStartLevelCalibrationMessage(drone);
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}

	@Override
	public boolean isCalibrating() {
		return calibrating;
	}

	@Override
	public void onDroneEvent(DroneEventsType event, Drone drone) {
		switch (event) {
			case CALIBRATION_IMU:
				break;

			default:
				break;
		}
	}

}
