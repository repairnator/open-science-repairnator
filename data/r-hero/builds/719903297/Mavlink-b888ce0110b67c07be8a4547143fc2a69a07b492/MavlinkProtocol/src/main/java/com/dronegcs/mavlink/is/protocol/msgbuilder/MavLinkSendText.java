package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_command_long;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_statustext;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_COMPONENT;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_SEVERITY;

public class MavLinkSendText {

	public static void sendArmMessage(Drone drone, MAV_SEVERITY severity, String message) {
		msg_statustext msg = new msg_statustext(drone.getGCS().getId());
		msg.severity = severity.value;
		msg.setText(message);
		drone.getMavClient().sendMavPacket(msg.pack());
	}

}