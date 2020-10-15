package com.dronegcs.mavlink.core.gcs;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneInterfaces;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkHeartbeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class is used to send periodic heartbeat messages to the drone.
 */

@Component
public class GCSHeartbeat implements DroneInterfaces.OnDroneListener {

	private final Logger LOGGER = LoggerFactory.getLogger(GCSHeartbeat.class);

	/**
	 * This is the drone to send the heartbeat message to.
	 */
	@Autowired @NotNull(message = "Internal Error: Failed to get drone")
	private Drone drone;

	/**
	 * This is the heartbeat period in seconds.
	 */
	private int period = 1;

	/**
	 * ScheduledExecutorService used to periodically schedule the heartbeat.
	 */
	private ScheduledExecutorService heartbeatExecutor = null;

	/**
	 * Runnable used to send the heartbeat.
	 */
	private final Runnable heartbeatRunnable = () -> MavLinkHeartbeat.sendMavHeartbeat(drone);

	static int called;
	@PostConstruct
	public void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");
		drone.addDroneListener(this);
	}

	/**
	 * Set the state of the heartbeat.
	 * 
	 * @param active
	 *            true to activate the heartbeat, false to deactivate it
	 */
	public void setActive(boolean active) {
		if (active) {
			heartbeatExecutor = Executors.newSingleThreadScheduledExecutor();
			heartbeatExecutor.scheduleWithFixedDelay(heartbeatRunnable, 0, period, TimeUnit.SECONDS);
		}
		else if (heartbeatExecutor != null) {
			heartbeatExecutor.shutdownNow();
			heartbeatExecutor = null;
		}
	}

	public void setFrequency(int i) {
		period = i;
		setActive(false);
		setActive(true);
	}

	public int getFrequency() {
		return period;
	}

	@Override
	public void onDroneEvent(DroneInterfaces.DroneEventsType event, Drone drone) {
		switch (event) {
			case HEARTBEAT_FIRST:
				LOGGER.debug("Activate GCS Heartbeat mechanism");
				if (heartbeatExecutor == null)
					setActive(true);
				break;
			case DISCONNECTED:
				LOGGER.debug("De-Activate GCS Heartbeat mechanism");
				setActive(false);
				break;
		}
	}
}
