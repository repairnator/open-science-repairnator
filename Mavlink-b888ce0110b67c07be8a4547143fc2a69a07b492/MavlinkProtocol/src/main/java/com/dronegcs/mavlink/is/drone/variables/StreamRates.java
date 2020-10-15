package com.dronegcs.mavlink.is.drone.variables;

import com.generic_tools.logger.Logger;
import com.generic_tools.validations.RuntimeValidator;
import com.generic_tools.validations.ValidatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.OnDroneListener;
import com.dronegcs.mavlink.is.drone.Preferences.Rates;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkStreamRates;

import javax.validation.constraints.NotNull;

@Component
public class StreamRates extends DroneVariable implements OnDroneListener
{
	@Autowired @NotNull(message = "Internal Error: Failed to get logger")
	private Logger logger;

	@Autowired
	private RuntimeValidator runtimeValidator;

	private boolean streamRatesWasSet = false;
	
	static int called;
	public void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");

		ValidatorResponse validatorResponse = runtimeValidator.validate(this);
		if (validatorResponse.isFailed())
			throw new RuntimeException(validatorResponse.toString());

		drone.addDroneListener(this);
	}

	@Override
	public void onDroneEvent(DroneEventsType event, Drone drone) {
		switch (event) {
		case HEARTBEAT_FIRST:
			logger.LogGeneralMessege("First HB Packet detected");
			break;
		case PROTOCOL_IDENTIFIED:
			logger.LogGeneralMessege("Protocol Identified, Sending stream rates");
		case HEARTBEAT_RESTORED:
			setupStreamRatesFromPref();
			break;
		default:
			break;
		}
	}

	public void setupStreamRatesFromPref() {
		if (streamRatesWasSet)
			return;

		Rates rates = drone.getPreferences().getRates();

		MavLinkStreamRates.setupStreamRates(drone, rates.extendedStatus,
				rates.extra1, rates.extra2, rates.extra3, rates.position, rates.rcChannels,
				rates.rawSensors, rates.rawController);
		
		streamRatesWasSet = true;
	}
	
	public void prepareStreamRates() {
		streamRatesWasSet = false;
	}

}
