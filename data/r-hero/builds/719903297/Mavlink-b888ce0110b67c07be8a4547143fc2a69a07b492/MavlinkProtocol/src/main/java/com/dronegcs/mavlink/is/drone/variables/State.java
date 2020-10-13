package com.dronegcs.mavlink.is.drone.variables;

import com.dronegcs.mavlink.is.drone.DroneInterfaces.Clock;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.Handler;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ApmModes;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkModes;
import com.generic_tools.logger.Logger;
import com.generic_tools.validations.RuntimeValidator;
import com.generic_tools.validations.ValidatorResponse;
import com.geo_tools.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class State extends DroneVariable
{
	private static final long failsafeOnScreenTimeout = 5000;
	private String warning = "";
	private boolean armed = false;
	private boolean isFlying = false;
	private ApmModes mode = ApmModes.UNKNOWN;

	// flightTimer
	// ----------------
	private long startTime = 0;
	private long elapsedFlightTime = 0;

	@Autowired @NotNull( message = "Internal Error: Failed to get com.dronegcs.gcsis.logger" )
	private Logger logger;
	
	@Autowired @NotNull( message = "Internal Error: Clock Field wasn't initialized" )
	private Clock clock;

	@Autowired @NotNull( message = "Internal Error: Handler Field wasn't initialized" )
	public Handler handler;
	
	@Autowired @NotNull( message = "Internal Error: Failed to get validator" )
	private RuntimeValidator runtimeValidator;
	
	public Runnable watchdogCallback = () -> removeWarning();
	
	static int called;
	public void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");

		ValidatorResponse validatorResponse = runtimeValidator.validate(this);
		if (validatorResponse.isFailed())
			throw new RuntimeException(validatorResponse.toString());

		resetFlightTimer();
	}

	public boolean isWarning() {
		return !warning.equals("");
	}

	public boolean isArmed() {
		return armed;
	}

	public boolean isFlying() {
		return isFlying;
	}

	public ApmModes getMode() {
		return mode;
	}

	public String getWarning() {
		return warning;
	}

	public void setIsFlying(boolean newState) {
		if (newState != isFlying) {
			isFlying = newState;
			drone.notifyDroneEvent(DroneEventsType.STATE);
			if (isFlying) {
				startTimer();
			} else {
				stopTimer();
			}
		}
	}

	public void setWarning(String newFailsafe) {
		if (!this.warning.equals(newFailsafe)) {
			this.warning = newFailsafe;
			drone.notifyDroneEvent(DroneEventsType.AUTOPILOT_WARNING);
		}
		handler.removeCallbacks(watchdogCallback);
		handler.postDelayed(watchdogCallback, failsafeOnScreenTimeout);
	}

	public void setArmed(boolean newState) {
		if (this.armed != newState) {
			this.armed = newState;
			drone.notifyDroneEvent(DroneEventsType.ARMING);
			if (newState) {
				drone.getWaypointManager().getWaypoints();
			}
			else{
				if (mode == ApmModes.ROTOR_RTL || mode == ApmModes.ROTOR_LAND) {
					changeFlightMode(ApmModes.ROTOR_LOITER);  // When disarming set the mode back to loiter so we can do a takeoff in the future.					
				}
			}
		}
	}

	public void doTakeoff(double alt) {
		drone.getGuidedPoint().doGuidedTakeoff(alt);
	}
	
	public void doTakeoff(Coordinate coord, double alt) {
		drone.getGuidedPoint().doGuidedTakeoff(coord, alt);
	}

	public void setMode(ApmModes mode) {
		if (this.mode != mode) {
			this.mode = mode;
			System.out.println("State " + getClass().getName() + " New Mode!!");
			System.out.println("State " + getClass().getName() + " " + mode.getName());
			drone.notifyDroneEvent(DroneEventsType.MODE);
		}
	}

	public void changeFlightMode(ApmModes mode) {
		if (mode == null) {
			logger.LogErrorMessege("Unexpected Mode value: Null");
			return;
		}
		
		if (ApmModes.isValid(mode)) {
			logger.LogGeneralMessege("Start DroneMission - Change to " + mode.getName());
			System.out.println("State " + getClass().getName() + " " + mode.getName());
			MavLinkModes.changeFlightMode(drone, mode);
			drone.getState().setMode(mode); //TODO: Add OK messege to quad copter, which notifies about change approval
		}
	}

	protected void removeWarning() {
		setWarning("");
	}

	// flightTimer
	// ----------------

	public void resetFlightTimer() {
		elapsedFlightTime = 0;
		startTime = clock.elapsedRealtime();
	}

	public void startTimer() {
		startTime = clock.elapsedRealtime();
	}

	public void stopTimer() {
		// lets calc the final elapsed timer
		elapsedFlightTime += clock.elapsedRealtime() - startTime;
		startTime = clock.elapsedRealtime();
	}

	public long getFlightTime() {
		if (isFlying) {
			// calc delta time since last checked
			elapsedFlightTime += clock.elapsedRealtime() - startTime;
			startTime = clock.elapsedRealtime();
		}
		return elapsedFlightTime / 1000;
	}

}