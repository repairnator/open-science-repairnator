package com.dronegcs.mavlink.is.drone;

import com.dronegcs.mavlink.core.firmware.FirmwareType;
import com.dronegcs.mavlink.is.connection.MavLinkConnection;
import com.dronegcs.mavlink.is.drone.calibration.CalibrateCompass;
import com.dronegcs.mavlink.is.drone.calibration.CalibrateRC;
import com.dronegcs.mavlink.is.drone.calibration.CalibrateGyroLevel;
import com.dronegcs.mavlink.is.drone.calibration.CalibrateGyroOrientation;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.profiles.Parameters;
import com.dronegcs.mavlink.is.drone.profiles.VehicleProfile;
import com.dronegcs.mavlink.is.drone.variables.*;
import com.dronegcs.mavlink.is.gcs.follow.Follow;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_heartbeat;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_TYPE;
import com.dronegcs.mavlink.is.protocol.msgbuilder.WaypointManager;

public interface Drone {

	void addDroneListener(DroneInterfaces.OnDroneListener listener);

	void removeDroneListener(DroneInterfaces.OnDroneListener listener);

	void notifyDroneEvent(DroneInterfaces.DroneEventsType event);

	GPS getGps();

//	int getMavlinkVersion();

	boolean isConnectionAlive();

	void onHeartbeat(msg_heartbeat msg);

	State getState();

	Parameters getParameters();

	void setType(MAV_TYPE type);

	Type getType();

	FirmwareType getFirmwareType();

	void loadVehicleProfile();

	VehicleProfile getVehicleProfile();

	MavLinkConnection getMavClient();

	Preferences getPreferences();

	WaypointManager getWaypointManager();

	Speed getSpeed();

	Battery getBattery();

	Radio getRadio();

	Home getHome();

	Altitude getAltitude();

	Orientation getOrientation();

	Navigation getNavigation();

	DroneMission getDroneMission();

	StreamRates getStreamRates();

	MissionStats getMissionStats();

	GuidedPoint getGuidedPoint();

	CalibrateGyroLevel getCalibrateGyroLevel();

	CalibrateGyroOrientation getCalibrateGyroOrientation();

	CalibrateCompass getCalibrateCompass();

	CalibrateRC getCalibrateRC();

	RC getRC();
	
	Magnetometer getMagnetometer();

	void setAltitudeGroundAndAirSpeeds(double altitude, double groundSpeed, double airSpeed,
			double climb);

	void setDisttowpAndSpeedAltErrors(double disttowp, double alt_error, double aspd_error);

	String getFirmwareVersion();

	void setFirmwareVersion(String message);

	CameraFootprints getCameraFootprints();

	Perimeter getPerimeter();

	Messages getMessegeQueue();

	Beacon getBeacon();

	GCS getGCS();

	Follow getFollow();
	
}
