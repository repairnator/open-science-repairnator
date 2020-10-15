package com.dronegcs.mavlink.is.drone.calibration;


import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneInterfaces;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkCalibration;
import org.springframework.stereotype.Component;

@Component
public class CalibrateGyroOrientation extends DroneVariable implements DroneInterfaces.OnDroneListener, Calibration {

	private boolean calibrating;

	@Override
	public boolean start() {
        if(drone.getState().isFlying()) {
            calibrating = false;
        }
        else {
            calibrating = true;
            MavLinkCalibration.sendStartAccelCalibrationMessage(drone);
        }
        return calibrating;
	}

    @Override
    public boolean stop() {
        return true;
    }

    @Override
    public boolean isCalibrating() {
        return calibrating;
    }

    public void ack(int step) {
        MavLinkCalibration.sendAccelCalibrationAckMessage(step, drone);
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

	public void setCalibrating(boolean flag) {
		calibrating = flag;
		drone.notifyDroneEvent(DroneEventsType.CALIBRATION_IMU);
	}
}
