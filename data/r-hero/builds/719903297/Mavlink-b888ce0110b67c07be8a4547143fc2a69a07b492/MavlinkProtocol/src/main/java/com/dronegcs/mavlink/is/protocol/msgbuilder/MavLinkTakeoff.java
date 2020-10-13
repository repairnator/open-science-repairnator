package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_command_long;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.geo_tools.Coordinate;

public class MavLinkTakeoff {
	public static void sendTakeoff(Drone drone, double alt) {
		msg_command_long msg = new msg_command_long(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.command = MAV_CMD.MAV_CMD_NAV_TAKEOFF;

		msg.param7 = (float) alt;

		drone.getMavClient().sendMavPacket(msg.pack());
	}
	
	public static void sendTakeoff(Drone drone, Coordinate target_coord) {
		double latitude = target_coord.getLat();
		double longitude = target_coord.getLon();
		double alt = target_coord.getAltitude();
		msg_command_long msg = new msg_command_long(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.command = MAV_CMD.MAV_CMD_NAV_TAKEOFF;

		msg.param5 = (float) latitude;
		msg.param6 = (float) longitude;
		msg.param7 = (float) alt;

		drone.getMavClient().sendMavPacket(msg.pack());
	}
}
