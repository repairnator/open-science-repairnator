package com.dronegcs.mavlink.core.drone;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import com.dronegcs.mavlink.core.connection.DroneUpdateListener;
import com.dronegcs.mavlink.core.drone.profile.ArduPlaneProfile;
import com.generic_tools.logger.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.dronegcs.mavlink.core.drone.profile.ArduCopterProfile;
import com.dronegcs.mavlink.core.firmware.FirmwareType;
import com.dronegcs.mavlink.is.drone.Preferences;
import com.dronegcs.mavlink.is.drone.profiles.VehicleProfile;

import static com.dronegcs.mavlink.is.drone.Preferences.Rates.*;

@ComponentScan(basePackages = "com.generic_tools")
@Component
public class PreferencesImpl implements Preferences {

	private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DroneUpdateListener.class);

	@Autowired @NotNull(message = "Internal Error: Failed to get logger")
	private Logger logger;

	private FirmwareType type;
	private VehicleProfile profile;
	private Rates rates;
	
//	 Create a factory instead of this
	@PostConstruct
	public void TemporaryLoadMe() {
		loadVehicleProfile(FirmwareType.ARDU_COPTER);
	}

	@Override
	public VehicleProfile loadVehicleProfile(FirmwareType firmwareType) {
		switch (firmwareType) {
			case ARDU_COPTER:
				type = FirmwareType.ARDU_COPTER;
				rates = new Rates(DEFAULT_RATE_STATUS, DEFAULT_RATE_ALTITUDE, DEFAULT_RATE_ALTITUDE, DEFAULT_RATE_SENSORS, DEFAULT_RATE_POSITION, DEFAULT_RATE_RC_CHANNELS, DEFAULT_RATE_SENSORS, DEFAULT_RATE_CONTROLLER);
				profile = new ArduCopterProfile();
				profile.init();
				logger.LogDesignedMessege("ArduCopter Profile was created");
				LOGGER.debug("ArduCopter Profile was created");
				LOGGER.debug("The following rates were selected '{}'", rates);

				return profile;
			case ARDU_PLANE:
				type = FirmwareType.ARDU_PLANE;
				rates = new Rates(DEFAULT_RATE_STATUS, DEFAULT_RATE_ALTITUDE, DEFAULT_RATE_ALTITUDE, DEFAULT_RATE_SENSORS, DEFAULT_RATE_POSITION, DEFAULT_RATE_RC_CHANNELS, DEFAULT_RATE_SENSORS, DEFAULT_RATE_CONTROLLER);
				profile = new ArduPlaneProfile();
				profile.init();
				logger.LogDesignedMessege("ArduPlane Profile was created");
				LOGGER.debug("ArduPlane Profile was created");
				LOGGER.debug("The following rates were selected '{}'", rates);

				return profile;
			default:
				String msg = "Unsupported frame '" + firmwareType.name() + "'";
				logger.LogErrorMessege(msg);
				LOGGER.error(msg);
		}
		return null;
	}

	@Override
	public FirmwareType getVehicleType() {
		return type;
	}

	@Override
	public VehicleProfile getProfile() {
		return profile;
	}

	@Override
	public Rates getRates() {
		return rates;
	}

	@Override
	public void setRates(Rates rates) {
		this.rates = rates;
	}
}
