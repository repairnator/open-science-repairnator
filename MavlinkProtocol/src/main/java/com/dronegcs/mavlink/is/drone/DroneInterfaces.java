package com.dronegcs.mavlink.is.drone;

import java.util.List;

import com.dronegcs.mavlink.is.drone.parameters.Parameter;
import com.dronegcs.mavlink.is.protocol.msgbuilder.WaypointManager;

public class DroneInterfaces {

	/**
	 * Sets of drone events used for broadcast throughout the app.
	 */
	public enum DroneEventsType {
		/**
         *
         */
		ORIENTATION,

		/**
         *
         */
		SPEED,

		/**
         *
         */
		BATTERY,

		/**
         *
         */
		GUIDEDPOINT,

		/**
         *
         */
		NAVIGATION,

		/**
         *
         */
		ATTITUDE,

		/**
         *
         */
		RADIO,

		/**
         *
         */
		RC_IN,

		/**
         *
         */
		RC_OUT,

		/**
         *
         */
		ARMING,

		/**
         *
         */
		AUTOPILOT_WARNING,

		/**
         *
         */
		MODE,

		/**
         *
         */
		STATE,

		/**
         *
         */
		MISSION_UPDATE,

		/**
         *
         */
		MISSION_RECEIVED,

		/**
         *
         */
		TYPE,

		/**
         *
         */
		HOME,

		/**
		 * Broadcast to notify of updates to the drone's gps location.
		 */
		GPS,

		/**
         *
         */
		GPS_FIX,

		/**
         *
         */
		GPS_COUNT,

		/**
         *
         */
		PARAMETER,

		/**
         *
         */
		CALIBRATION_IMU,

		/**
         *
         */
		CALIBRATION_TIMEOUT,

		/**
         *
         */
		HEARTBEAT_TIMEOUT,

		/**
         *
         */
		HEARTBEAT_FIRST,

		/**
         *
         */
		HEARTBEAT_RESTORED,

		/**
         *
         */
		DISCONNECTED,

		/**
         *
         */
		CONNECTED,

		/**
         *
         */
		MISSION_SENT,

		/**
         *
         */
		ARMING_STARTED,

		/**
         *
         */
		INVALID_POLYGON,

		/**
         *
         */
		MISSION_WP_UPDATE,

		/**
		 * 'Follow' mode has been enabled.
		 */
		FOLLOW_START,

		/**
		 * 'Follow' mode has been disabled.
		 */
		FOLLOW_STOP,
		
		/**
		 * 'Follow' state has been updated.
		 */
		FOLLOW_UPDATE,

		/**
         * 
         */
		FOLLOW_CHANGE_TYPE,

		/**
		 *
		 */
		WARNING_400FT_EXCEEDED,

		/**
		 *
		 */
		WARNING_SIGNAL_WEAK,
		/**
		 * Announces that a new version for the firmware has been received
		 */
		FIRMWARE,

		/**
		 * Warn that the drone has no gps signal
		 */
		WARNING_NO_GPS, 
		
		/**
		 * New magnetometer data has been received
		 */
		MAGNETOMETER,
		
		/**
		 * The drone camera footprints has been updated
		 */
		FOOTPRINT,
		
		/**
		 * the drone left the perimeter
		 */
		PERIMETER_RECEIVED,

		LEFT_PERIMETER,

		ENFORCING_PERIMETER,
		
		TEXT_MESSEGE, 
		
		BEACON_BEEP, 
		
		BEACON_LOCK_START, 
		
		BEACON_LOCK_FINISH, 
		
		GCS_LOCATION,

		PROTOCOL_LEARNING,

		PROTOCOL_IDENTIFIED,

		/**
		 * New magnetometer data has been received
		 */
		EXT_CALIB_MAGNETOMETER_START,
		EXT_CALIB_MAGNETOMETER_PROGRESS,
		EXT_CALIB_MAGNETOMETER_FINISH,

		EXT_CALIB_RC_START,
		EXT_CALIB_RC_PROGRESS,
		EXT_CALIB_RC_FINISH,
	}

	public interface OnDroneListener {
		void onDroneEvent(DroneEventsType event, Drone drone);
	}

	public interface OnParameterManagerListener {
		void onBeginReceivingParameters();

		void onParameterReceived(Parameter parameter, int index, int count);

		void onEndReceivingParameters(List<Parameter> parameter);
	}

	public interface OnWaypointManagerListener {
		void onBeginWaypointEvent(WaypointManager.WaypointEvent_Type wpEvent);

		void onWaypointEvent(WaypointManager.WaypointEvent_Type wpEvent, int index, int count);

		void onEndWaypointEvent(WaypointManager.WaypointEvent_Type wpEvent);
	}

	public interface OnTimeout {

		void notifyTimeOut(int timeOutCount);

	}

	public interface Clock {

		long elapsedRealtime();

	}

	public interface Handler {

		void removeCallbacks(Runnable thread);

        void post(Runnable thread);


		void postDelayed(Runnable thread, long delayInMilliseconds);

	}

}
