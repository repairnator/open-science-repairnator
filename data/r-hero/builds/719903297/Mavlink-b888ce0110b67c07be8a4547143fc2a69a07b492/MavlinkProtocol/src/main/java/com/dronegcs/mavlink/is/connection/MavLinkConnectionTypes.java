package com.dronegcs.mavlink.is.connection;

/**
 * List the supported com.dronegcs.mavlink.is.mavlink connection types.
 */
public class MavLinkConnectionTypes {

	/**
	 * Bluetooth com.dronegcs.mavlink.is.mavlink connection.
	 */
	public static final int MAVLINK_CONNECTION_BLUETOOTH = 0;

	/**
	 * USB com.dronegcs.mavlink.is.mavlink connection.
	 */
	public static final int MAVLINK_CONNECTION_USB = 1;

	/**
	 * UDP com.dronegcs.mavlink.is.mavlink connection.
	 */
	public static final int MAVLINK_CONNECTION_UDP = 2;

	/**
	 * TCP com.dronegcs.mavlink.is.mavlink connection.
	 */
	public static final int MAVLINK_CONNECTION_TCP = 3;

	// Not instantiable
	private MavLinkConnectionTypes() {
	}
}
