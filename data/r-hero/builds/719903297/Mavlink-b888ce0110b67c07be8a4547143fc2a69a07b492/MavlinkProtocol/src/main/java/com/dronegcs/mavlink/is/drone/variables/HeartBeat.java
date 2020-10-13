package com.dronegcs.mavlink.is.drone.variables;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.Handler;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.OnDroneListener;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_heartbeat;

/**
 * @author taljmars
 * @see #init
 **/
@Component
public class HeartBeat extends DroneVariable implements OnDroneListener {

	private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HeartBeat.class);

	private static final long HEARTBEAT_NORMAL_TIMEOUT = 5000; //ms
	private static final long HEARTBEAT_LOST_TIMEOUT = 15000; //ms
    private static final long HEARTBEAT_IMU_CALIBRATION_TIMEOUT = 35000; //ms

	private static final int INVALID_MAVLINK_VERSION = -1;

	private HeartbeatState heartbeatState = HeartbeatState.FIRST_HEARTBEAT;
	
	@SuppressWarnings("unused")
	private int droneID = 1;

//	/**
//	 * Stores the version of the com.dronegcs.mavlink.is.mavlink protocol.
//	 */
//	private byte mMavlinkVersion = INVALID_MAVLINK_VERSION;

	private enum HeartbeatState {
		FIRST_HEARTBEAT, LOST_HEARTBEAT, NORMAL_HEARTBEAT, IMU_CALIBRATION
	}

	@Autowired @NotNull(message = "Internal Error: Failed to get handler")
	private Handler handler;
	
	private final Runnable watchdogCallback = () -> onHeartbeatTimeout();
	
	private static int called;
	/**
	 * must be called in order to finish object creation
	 **/
	public void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");
		drone.addDroneListener(this);
	}

//	/**
//	 * @return the version of the com.dronegcs.mavlink.is.mavlink protocol.
//	 */
//	public byte getMavlinkVersion() {
//		return mMavlinkVersion;
//	}

	@SuppressWarnings("incomplete-switch")
	public void onHeartbeat(msg_heartbeat msg) {
		droneID = msg.sysid;			
//		mMavlinkVersion = msg.mavlink_version;

		//System.err.println(getClass().getName() + " Currnet Status: " + heartbeatState);
		LOGGER.trace("Current Status: " + heartbeatState);

		switch (heartbeatState) {
		case FIRST_HEARTBEAT:
			drone.notifyDroneEvent(DroneEventsType.HEARTBEAT_FIRST);
			break;
		case LOST_HEARTBEAT:
			drone.notifyDroneEvent(DroneEventsType.HEARTBEAT_RESTORED);
			break;
		}

		heartbeatState = HeartbeatState.NORMAL_HEARTBEAT;
		
		restartWatchdog(HEARTBEAT_NORMAL_TIMEOUT);
	}

	public boolean isConnectionAlive() {
		//return heartbeatState != HeartbeatState.LOST_HEARTBEAT;
		return heartbeatState != HeartbeatState.LOST_HEARTBEAT && heartbeatState != HeartbeatState.FIRST_HEARTBEAT;
	}

	@Override
	public void onDroneEvent(DroneEventsType event, Drone drone) {
		switch (event) {
		case CALIBRATION_IMU:
			//Set the heartbeat in imu calibration mode.
			heartbeatState = HeartbeatState.IMU_CALIBRATION;
			restartWatchdog(HEARTBEAT_IMU_CALIBRATION_TIMEOUT);
			break;

		case CONNECTED:
			notifyConnected();
			break;

		case DISCONNECTED:
			notifyDisconnected();
			break;

		default:
			break;
		}
	}

	private void notifyConnected() {
		System.err.println("HB Notification - restart Heartbeat Watchdog");
		LOGGER.debug("HB Notification - restart Heartbeat Watchdog");
		restartWatchdog(HEARTBEAT_NORMAL_TIMEOUT);
	}

	private void notifyDisconnected() {
		handler.removeCallbacks(watchdogCallback);
		heartbeatState = HeartbeatState.FIRST_HEARTBEAT;
//		mMavlinkVersion = INVALID_MAVLINK_VERSION;
	}

	private void onHeartbeatTimeout() {
        switch(heartbeatState){
            case IMU_CALIBRATION:
                restartWatchdog(HEARTBEAT_IMU_CALIBRATION_TIMEOUT);
                drone.notifyDroneEvent(DroneEventsType.CALIBRATION_TIMEOUT);
                break;

            default:
                heartbeatState = HeartbeatState.LOST_HEARTBEAT;
                restartWatchdog(HEARTBEAT_LOST_TIMEOUT);
                drone.notifyDroneEvent(DroneEventsType.HEARTBEAT_TIMEOUT);
                break;
        }
	}

	private void restartWatchdog(long timeout) {
		// re-start watchdog
		handler.removeCallbacks(watchdogCallback);
		handler.postDelayed(watchdogCallback, timeout);
	}
}
