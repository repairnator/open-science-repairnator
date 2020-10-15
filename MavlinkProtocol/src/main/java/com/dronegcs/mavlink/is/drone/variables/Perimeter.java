package com.dronegcs.mavlink.is.drone.variables;

import javax.validation.constraints.NotNull;

import com.generic_tools.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ApmModes;
import com.geo_tools.Coordinate;

@Component
public class Perimeter extends DroneVariable {

	public enum PerimeterMode {
		ALERT,
		ENFORCE,
		OFF,
	}
	private Compound pCompound;
	private PerimeterMode pPerMode;
	private Coordinate pLastPosition = null;
	private Coordinate pLastPositionInPerimeter = null;
	private ApmModes pMode;
	private boolean pEnforcePerimeterRunning = false;

	@Autowired @NotNull(message = "Internal Error: Failed to get com.dronegcs.gcsis.logger")
	private Logger logger;
	
	static int called;
	public void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");
		pPerMode = PerimeterMode.ALERT;
		pCompound = null;
		pMode = drone.getState().getMode();
	}
	
	public void setCompound(Compound compound) {
		pCompound = compound;
		drone.notifyDroneEvent(DroneEventsType.PERIMETER_RECEIVED);
	}
	
	public void setPosition(Coordinate position) {
		pLastPosition = position;
		
		if (pPerMode.equals(PerimeterMode.ENFORCE)) {
			if (pLastPosition != null && !pCompound.isContained(position)) {
				drone.notifyDroneEvent(DroneEventsType.LEFT_PERIMETER);
				if (drone.getState().isFlying()) {
					drone.notifyDroneEvent(DroneEventsType.ENFORCING_PERIMETER);
					try {
						if (!pEnforcePerimeterRunning) {
							pMode = drone.getState().getMode();							
							logger.LogErrorMessege("Changing flight from " + pMode.getName() + " to " + ApmModes.ROTOR_GUIDED.getName() + " (Enforcing perimeter)");
							drone.getGuidedPoint().forcedGuidedCoordinate(getClosestPointOnPerimeterBorder());
							pEnforcePerimeterRunning = true;
						}
					} catch (Exception e) {
						logger.LogErrorMessege(e.toString());
						logger.LogErrorMessege("Error occur while changing flight mode; " + e.getMessage());
					}
				}
			}
			else {
				pLastPositionInPerimeter = pLastPosition;
				if (pEnforcePerimeterRunning) {
					logger.LogErrorMessege("Changing flight from " + ApmModes.ROTOR_GUIDED.getName() + " back to " + pMode.getName());
					drone.getState().changeFlightMode(pMode);
					pEnforcePerimeterRunning = false;
				}
				
			}
		}
	}
	
	public void setPerimeterMode(PerimeterMode mode) {
		if (mode.equals(PerimeterMode.ENFORCE))
			logger.LogGeneralMessege("Enable Perimeter enforcement");
		else if (mode.equals(PerimeterMode.ALERT))
			logger.LogGeneralMessege("Enable perimeter alert only");
		else
			logger.LogGeneralMessege("Disable perimeter enforcement/alert");

		pPerMode = mode;
	}

	public PerimeterMode getPerimeterMode() {
		return pPerMode;
	}
	
	public Coordinate getClosestPointOnPerimeterBorder() {
		if (pLastPositionInPerimeter == null) {
			logger.LogGeneralMessege("Last position not exist, return closest corner in it");
			return getClosestCornerInPoligon();
		}
		
		return pLastPositionInPerimeter;
	}
	
	public Coordinate getClosestCornerInPoligon() {
		return pCompound.getClosestPointOnEdge(pLastPosition);
	}

	public boolean isAlertOnly() {
		return pPerMode.equals(PerimeterMode.ALERT);
	}

	public boolean isEnforce() {
		return pPerMode.equals(PerimeterMode.ENFORCE);
	}
}
