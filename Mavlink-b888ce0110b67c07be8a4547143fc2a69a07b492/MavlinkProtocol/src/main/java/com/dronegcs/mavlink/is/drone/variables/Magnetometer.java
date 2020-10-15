package com.dronegcs.mavlink.is.drone.variables;


import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_scaled_imu;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_scaled_imu2;
import org.springframework.stereotype.Component;

import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.drone.parameters.Parameter;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_raw_imu;

@Component
public class Magnetometer extends DroneVariable {

	private int x;
	private int y;
	private int z;

	public void newData(msg_raw_imu msg_imu) {
		x = msg_imu.xmag;
		y = msg_imu.ymag;
		z = msg_imu.zmag;
		drone.notifyDroneEvent(DroneEventsType.MAGNETOMETER);
	}

	public void newData(msg_scaled_imu msg_imu) {
		x = msg_imu.xmag;
		y = msg_imu.ymag;
		z = msg_imu.zmag;
		drone.notifyDroneEvent(DroneEventsType.MAGNETOMETER);
	}

	public void newData(msg_scaled_imu2 msg_imu) {
		x = msg_imu.xmag;
		y = msg_imu.ymag;
		z = msg_imu.zmag;
		drone.notifyDroneEvent(DroneEventsType.MAGNETOMETER);
	}

	public int[] getVector() {
		return new int[] { x, y, z };
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int[] getOffsets() {
		Parameter paramX = drone.getParameters().getParameter("COMPASS_OFS_X");
		Parameter paramY = drone.getParameters().getParameter("COMPASS_OFS_Y");
		Parameter paramZ = drone.getParameters().getParameter("COMPASS_OFS_Z");
		if (paramX == null || paramY == null || paramZ == null) {
			return null;
		}
		return new int[]{paramX.getValue().intValue(), paramY.getValue().intValue(), paramZ.getValue().intValue()};

	}
}
