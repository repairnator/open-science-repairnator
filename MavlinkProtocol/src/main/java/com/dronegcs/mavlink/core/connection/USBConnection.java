package com.dronegcs.mavlink.core.connection;

import com.dronegcs.mavlink.is.connection.MavLinkConnection;
import com.dronegcs.mavlink.is.connection.MavLinkConnectionTypes;
import com.generic_tools.devices.SerialConnection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Provides support for mavlink connection via udp.
 */
@Component
public class USBConnection extends MavLinkConnection {

	private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(USBConnection.class);

//	@Autowired
	private SerialConnection serialConnection;

	@Autowired
	public void setSerialConnection( SerialConnection serialConnection) {
		this.serialConnection = serialConnection;
	}
	
	@Autowired
	private DroneUpdateListener droneUpdateListener;

	private ScheduledFuture scheduledFuture = null;
	
	private static int called;
	@PostConstruct
	private void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");
	}

	private final Runnable monitorTask = () -> {
		if (serialConnection == null)
			return;

		LOGGER.trace("Starting Monitor connection");
		connectionStatistics.setReceivedBytes(serialConnection.getRx());
		connectionStatistics.setReceivedBytesPerSecond(serialConnection.getReceivedBytesPerSeconds());
		connectionStatistics.setTransmittedBytes(serialConnection.getTx());
		connectionStatistics.setTransmittedBytesPerSecond(serialConnection.getTransmittedBytesPerSeconds());
	};

	@Override
	public boolean openConnection() throws Exception {
		LOGGER.debug("openConnection");
		boolean res = serialConnection.connect();
		if (!res) {
			LOGGER.debug("Failed to open USB connection");
			return res;
		}

		scheduledFuture = Executors.newScheduledThreadPool(1).scheduleAtFixedRate(monitorTask, 0, 1, TimeUnit.SECONDS);
		return res;
	}
	
	@Override
	public boolean closeConnection() throws IOException {
		LOGGER.debug("closeConnection");
		if (scheduledFuture != null) {
			scheduledFuture.cancel(false);
			scheduledFuture = null;
		}
		boolean res = serialConnection.disconnect();
		LOGGER.debug("closeConnection done");
		return res;
	}

	@Override
	public final void sendBuffer(byte[] buffer) throws IOException {
		try {
			if (serialConnection != null) { // We can't send to our sister until they
				// have connected to us
				serialConnection.write(buffer);
			}
		}
		catch (Exception e) {
			LOGGER.error("Failed to send buffer", e);
		}
	}

	@Override
	public final int readDataBlock(byte[] readData) throws IOException {
		if (serialConnection == null)
			return -1;
		return serialConnection.read(readData, readData.length);
	}

	@Override
	public void loadPreferences() {
		addMavLinkConnectionListener("Drone", droneUpdateListener);
	}

	@Override
	public final int getConnectionType() {
		return MavLinkConnectionTypes.MAVLINK_CONNECTION_USB;
	}
}
