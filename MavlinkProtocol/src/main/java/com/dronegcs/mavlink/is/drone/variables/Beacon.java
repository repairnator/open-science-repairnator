package com.dronegcs.mavlink.is.drone.variables;

import com.dronegcs.mavlink.core.connection.helper.BeaconData;
import com.dronegcs.mavlink.core.connection.helper.BeaconDataFactory;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ApmModes;
import com.generic_tools.logger.Logger;
import com.geo_tools.Coordinate;
import javax.swing.SwingWorker;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Beacon extends DroneVariable {
	
	@Autowired @NotNull(message = "Internal Error: Failed to get com.dronegcs.gcsis.logger")
	private Logger logger;

	@Autowired @NotNull(message = "Internal Error: Failed to get beacon data factory")
	private BeaconDataFactory beaconDataFactory;
	
	private boolean pIsActive;
	private Coordinate pLastPosition;
	private transient SwingWorker<Void, Void> pFollowThread = null;

	public Beacon() {
		pIsActive = false;
		pLastPosition = null;
	}
	
	public void setPosition(Coordinate coord) {
		pLastPosition = coord;
		drone.notifyDroneEvent(DroneEventsType.BEACON_BEEP);
	}
	
	public Coordinate getPosition() {
		return pLastPosition;
	}
	
	public void setActive(boolean should_be_active) {
		pIsActive = should_be_active;
		if (pIsActive) {
			drone.notifyDroneEvent(DroneEventsType.BEACON_LOCK_START);
			RunThread();
		}
		else {
			KillThread();
			drone.notifyDroneEvent(DroneEventsType.BEACON_LOCK_FINISH);
		}
	}
	
	public boolean isActive() {
		return pIsActive;
	}
	
	public void RunThread() {
		logger.LogGeneralMessege("Start Beacon Thread");
		if (pFollowThread != null) {
			logger.LogGeneralMessege("Terminate existing beacon thread");
			pFollowThread.cancel(true);
		}
		
		pFollowThread = new SwingWorker<Void, Void>(){
			
			@Override
   			protected Void doInBackground() throws Exception {
				logger.LogGeneralMessege("Beacon Started");
				logger.LogGeneralMessege("Beacon Mainloop Started");
				Coordinate tmpPos = null;
				boolean started = false;
				while (true) {
					Thread.sleep(1000);
					if (!drone.getGps().isPositionValid()) {
						logger.LogErrorMessege("Drone doesn't have GPS location, skipp request to follow beacon");
						continue;
					}
					
					syncBeacon();

					// tmpPos != null mark that we are in the first iteration of the loop
					if (started && !GuidedPoint.isGuidedMode(drone)) {
						logger.LogErrorMessege("Quad is must be in guided mode to follow beacon, operation canceled");
						drone.notifyDroneEvent(DroneEventsType.FOLLOW_STOP);
						started = false;
						break;
					}
					
					if (tmpPos == pLastPosition) {
						logger.LogGeneralMessege("Same beacon position");
						continue;
					}
					
					drone.getGuidedPoint().forcedGuidedCoordinate(pLastPosition.dot(1));
					tmpPos = pLastPosition;
					started = true;
					logger.LogGeneralMessege("Update Beacon position was sent to quad");
				}
				logger.LogGeneralMessege("Beacon Mainloop Finished");
				return null;
			}
			
			@Override
            protected void done() {
				logger.LogGeneralMessege("Beacon Thread Finish");
			}
		};
		
		pFollowThread.execute();
	}
	
	public void KillThread() {
		logger.LogGeneralMessege("Stop Beacon Thread");
		if (pFollowThread != null) {
			logger.LogGeneralMessege("Terminate existing beacon thread");
			pFollowThread.cancel(true);
		}
		logger.LogGeneralMessege("Change mode to Position Hold");
		drone.getState().changeFlightMode(ApmModes.ROTOR_POSHOLD);
		
		pFollowThread = null;
	}

	public void syncBeacon() {
		logger.LogGeneralMessege("Sync Beacon");
		BeaconData beaconData = beaconDataFactory.fetch();
		if (beaconData == null) {
			logger.LogErrorMessege("Failed to get beacon point from the web");
			return;
		}
		setPosition(beaconData.getCoordinate());
	}
}
