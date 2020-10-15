package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_heartbeat;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_AUTOPILOT;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_TYPE;

/**
 * This class contains logic used to send an heartbeat to a
 * {@link com.dronegcs.mavlink.is.drone.Drone}.
 */
public class MavLinkHeartbeat {

	/**
	 * This is the com.dronegcs.mavlink.is.mavlink packet obtained from the msg heartbeat, and used for
	 * actual communication.
	 */
	private static MAVLinkPacket sMsgPacket = null;

	/**
	 * Sends the heartbeat to the {@link com.dronegcs.mavlink.is.drone.Drone}
	 * object.
	 *
	 * @param drone
	 *            drone to send the heartbeat to
	 */
	public static void sendMavHeartbeat(Drone drone) {
		if (drone != null) {
			if (sMsgPacket == null) {
				/**
				 * This is the msg heartbeat used to check the drone is present, and
				 * responding.
				 */
				msg_heartbeat sMsg = new msg_heartbeat(drone.getGCS().getId());
				sMsg.type = (byte) MAV_TYPE.MAV_TYPE_GCS.getIndex();
				sMsg.autopilot = MAV_AUTOPILOT.MAV_AUTOPILOT_INVALID;
				sMsg.mavlink_version = 3;
				sMsgPacket = sMsg.pack();
			}
			drone.getMavClient().sendMavPacket(sMsgPacket);
		}
	}

}
