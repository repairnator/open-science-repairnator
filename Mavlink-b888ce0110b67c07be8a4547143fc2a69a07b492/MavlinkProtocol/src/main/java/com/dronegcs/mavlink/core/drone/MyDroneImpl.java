package com.dronegcs.mavlink.core.drone;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import com.dronegcs.mavlink.is.connection.MavLinkConnection;
import com.dronegcs.mavlink.core.firmware.FirmwareType;
import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneEvents;
import com.dronegcs.mavlink.is.drone.DroneInterfaces;
import com.dronegcs.mavlink.is.drone.Preferences;
import com.dronegcs.mavlink.is.drone.calibration.CalibrateCompass;
import com.dronegcs.mavlink.is.drone.calibration.CalibrateRC;
import com.dronegcs.mavlink.is.drone.calibration.CalibrateGyroOrientation;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.profiles.Parameters;
import com.dronegcs.mavlink.is.drone.profiles.VehicleProfile;
import com.dronegcs.mavlink.is.drone.variables.Altitude;
import com.dronegcs.mavlink.is.drone.variables.Battery;
import com.dronegcs.mavlink.is.drone.variables.Beacon;
import com.dronegcs.mavlink.is.drone.calibration.CalibrateGyroLevel;
import com.dronegcs.mavlink.is.drone.variables.CameraFootprints;
import com.dronegcs.mavlink.is.drone.variables.GCS;
import com.dronegcs.mavlink.is.drone.variables.GPS;
import com.dronegcs.mavlink.is.drone.variables.GuidedPoint;
import com.dronegcs.mavlink.is.drone.variables.HeartBeat;
import com.dronegcs.mavlink.is.drone.variables.Home;
import com.dronegcs.mavlink.is.drone.variables.Magnetometer;
import com.dronegcs.mavlink.is.drone.variables.Messages;
import com.dronegcs.mavlink.is.drone.variables.MissionStats;
import com.dronegcs.mavlink.is.drone.variables.Navigation;
import com.dronegcs.mavlink.is.drone.variables.Orientation;
import com.dronegcs.mavlink.is.drone.variables.Perimeter;
import com.dronegcs.mavlink.is.drone.variables.RC;
import com.dronegcs.mavlink.is.drone.variables.Radio;
import com.dronegcs.mavlink.is.drone.variables.Speed;
import com.dronegcs.mavlink.is.drone.variables.State;
import com.dronegcs.mavlink.is.drone.variables.StreamRates;
import com.dronegcs.mavlink.is.drone.variables.Type;
import com.dronegcs.mavlink.is.gcs.follow.Follow;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_heartbeat;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_TYPE;
import com.dronegcs.mavlink.is.protocol.msgbuilder.WaypointManager;

import com.generic_tools.validations.RuntimeValidator;
import com.generic_tools.validations.ValidatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = "com.dronegcs.mavlink")
@Component
public class MyDroneImpl implements Drone {

//	@Bean
//	public VehicleProfile profile() {
//		loadVehicleProfile();
//		return profile;
//	}
	
	@NotNull(message="Missing GPS parameter")
	@Autowired
	private GPS GPS;
	
	@NotNull(message="Missing Preferences parameter")
	@Autowired
	private Preferences preferences;
	
	@NotNull(message="Missing RC parameter")
	@Autowired
	private RC RC;

	@NotNull(message="Missing Beacon parameter")
	@Autowired
	private Beacon Beacon;

	@NotNull(message="Missing GCS parameter")
	@Autowired
	private GCS GCS;
	
	@NotNull(message="Missing Speed parameter")
	@Autowired
	private Speed speed;

	@NotNull(message="Missing Battery parameter")
	@Autowired
	private Battery battery;

	@NotNull(message="Missing Radio parameter")
	@Autowired
	private Radio radio;

	@NotNull(message="Missing Home parameter")
	@Autowired
	private Home home;

	@Autowired
	private DroneMission droneMission;

	@Autowired
	private MissionStats missionStats;

	@NotNull(message="Missing StreamRates parameter")
	@Autowired
	private StreamRates streamRates;

	@NotNull(message="Missing Altitude parameter")
	@Autowired
	private Altitude altitude;

	@NotNull(message="Missing Orientation parameter")
	@Autowired
	private Orientation orientation;

	@NotNull(message="Missing Navigation parameter")
	@Autowired
	private Navigation navigation;

	@NotNull(message="Missing GuidedPoint parameter")
	@Autowired
	private GuidedPoint guidedPoint;
	
	@NotNull(message="Missing Calibration parameter")
	@Autowired
	private CalibrateGyroLevel calibrateGyroLevel;

	@NotNull(message="Missing Calibration parameter")
	@Autowired
	private CalibrateGyroOrientation calibrateGyroOrientation;

	@NotNull(message="Missing CalibrateCompass parameter")
	@Autowired
	private CalibrateCompass calibrateCompass;

	@NotNull(message="Missing CalibrateRC parameter")
	@Autowired
	private CalibrateRC calibrateRC;

	@Autowired
	private WaypointManager waypointManager;

	@NotNull(message="Missing Magnetometer parameter")
	@Autowired
	private Magnetometer mag;

	@NotNull(message="Missing CameraFootprints parameter")
	@Autowired
	private CameraFootprints footprints;

	@NotNull(message="Missing Perimeter parameter")
	@Autowired
	private Perimeter Perimeter;
	
	@NotNull(message="Missing Type parameter")
	@Autowired
	private Type type;
	
	@NotNull(message="Missing Messege parameter")
	@Autowired
	private Messages messeges;
	
	@NotNull(message="Missing Parameters parameter")
	@Autowired
	private Parameters parameters;

	@NotNull(message="Missing DroneEvents parameter")
	@Autowired
	private DroneEvents events;
	
	@NotNull(message="Missing HeartBeat parameter")
	@Autowired
	private HeartBeat heartbeat;
	
	@NotNull(message="Missing State parameter")
	@Autowired
	private State state;

	@NotNull(message="Missing Follow parameter")
	@Autowired
	private Follow follow;
	
	@NotNull(message="Missing MavLinkConnection parameter")
	@Autowired
	private MavLinkConnection mavlinkConnection;

	@NotNull(message = "Internal Error: Failed to get validator")
	@Autowired
	private RuntimeValidator runtimeValidator;

//	private VehicleProfile profile;
	
	static int called = 0;
	@PostConstruct
	private void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");
		
		heartbeat.init();
		state.init();
		follow.init();
		guidedPoint.init();
		type.init();
		streamRates.init();
		Perimeter.init();
		parameters.init();
		calibrateCompass.init();
//		messeges.init();

		ValidatorResponse validatorResponse = runtimeValidator.validate(this);
		if (validatorResponse.isFailed())
			throw new RuntimeException(validatorResponse.toString());
	}

	@Override
	public void setAltitudeGroundAndAirSpeeds(double altitude, double groundSpeed, double airSpeed, double climb) {
		this.altitude.setAltitude(altitude);
		speed.setGroundAndAirSpeeds(groundSpeed, airSpeed, climb);
	    notifyDroneEvent(DroneInterfaces.DroneEventsType.SPEED);
	}

	@Override
	public void setDisttowpAndSpeedAltErrors(double disttowp, double alt_error, double aspd_error) {
		missionStats.setDistanceToWp(disttowp);
		altitude.setAltitudeError(alt_error);
		speed.setSpeedError(aspd_error);
		notifyDroneEvent(DroneInterfaces.DroneEventsType.ORIENTATION);
	}

	@Override
	public boolean isConnectionAlive() {
		return heartbeat.isConnectionAlive();
	}

	@Override
	public void addDroneListener(DroneInterfaces.OnDroneListener listener) {
		events.addDroneListener(listener);
		messeges.addMessageQueue(listener);
	}

	@Override
	public void removeDroneListener(DroneInterfaces.OnDroneListener listener) {
		messeges.removeMessageQueue(listener);
		events.removeDroneListener(listener);
	}

	@Override
	public void notifyDroneEvent(final DroneInterfaces.DroneEventsType event) {
		events.notifyDroneEvent(event);
	}

	@Override
	public GPS getGps() {
		return GPS;
	}

//	@Override
//	public int getMavlinkVersion() {
//		return heartbeat.getMavlinkVersion();
//	}

	@Override
	public void onHeartbeat(msg_heartbeat msg) {
		heartbeat.onHeartbeat(msg);
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public Parameters getParameters() {
		return parameters;
	}

	@Override
	public void setType(MAV_TYPE type) {
		this.type.setDroneType(type);
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public FirmwareType getFirmwareType() {
		return type.getFirmwareType();
	}

	@Override
	public void loadVehicleProfile() {
		preferences.loadVehicleProfile(getFirmwareType());
	}

	@Override
	public VehicleProfile getVehicleProfile() {
		return preferences.getProfile();
	}

	@Override
	public Preferences getPreferences() {
		return preferences;
	}

	@Override
	public WaypointManager getWaypointManager() {
		return waypointManager;
	}

	@Override
	public RC getRC() {
		return RC;
	}

	@Override
	public Speed getSpeed() {
		return speed;
	}

	@Override
	public Battery getBattery() {
		return battery;
	}

	@Override
	public Radio getRadio() {
		return radio;
	}

	@Override
	public Home getHome() {
		return home;
	}

	public DroneMission getDroneMission() {
		return droneMission;
	}

	@Override
	public MissionStats getMissionStats() {
		return missionStats;
	}

	@Override
	public StreamRates getStreamRates() {
		return streamRates;
	}

	@Override
	public Altitude getAltitude() {
		return altitude;
	}

	@Override
	public Orientation getOrientation() {
		return orientation;
	}

	@Override
	public Navigation getNavigation() {
		return navigation;
	}

	@Override
	public GuidedPoint getGuidedPoint() {
		return guidedPoint;
	}

	@Override
	public CalibrateGyroLevel getCalibrateGyroLevel() {
		return calibrateGyroLevel;
	}

	@Override
	public CalibrateGyroOrientation getCalibrateGyroOrientation() {
		return calibrateGyroOrientation;
	}

	@Override
	public CalibrateCompass getCalibrateCompass() {
		return calibrateCompass;
	}

	@Override
	public CalibrateRC getCalibrateRC() {
		return calibrateRC;
	}

	@Override
	public String getFirmwareVersion() {
		return type.getFirmwareVersion();
	}

	@Override
	public void setFirmwareVersion(String message) {
		type.setFirmwareVersion(message);
	}

	@Override
	public Magnetometer getMagnetometer() {
		return mag;
	}
	
	public CameraFootprints getCameraFootprints() {
		return footprints;
	}

	@Override
	public Perimeter getPerimeter() {
		return Perimeter;
	}

	@Override
	public Messages getMessegeQueue() {
		return messeges;
	}

	@Override
	public Beacon getBeacon() {
		return Beacon;
	}
	
	@Override
	public GCS getGCS() {
		return GCS;
	}

	@Override
	public Follow getFollow() {
		return follow;
	}
	
	@Override
	public MavLinkConnection getMavClient() {
		return mavlinkConnection;
	}
}