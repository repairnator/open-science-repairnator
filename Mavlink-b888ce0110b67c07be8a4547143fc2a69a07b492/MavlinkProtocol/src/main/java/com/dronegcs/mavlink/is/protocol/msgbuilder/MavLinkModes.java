package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ApmModes;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_set_mode;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_FRAME;

public class MavLinkModes {
	public static void setGuidedMode(Drone drone, double latitude, double longitude, double d) {
		msg_mission_item msg = new msg_mission_item(drone.getGCS().getId());
		msg.seq = 0;
		msg.current = 2; // TODO use guided mode enum
		msg.frame = MAV_FRAME.MAV_FRAME_GLOBAL;
		msg.command = MAV_CMD.MAV_CMD_NAV_WAYPOINT; //
		msg.param1 = 0; // TODO use correct parameter
		msg.param2 = 0; // TODO use correct parameter
		msg.param3 = 0; // TODO use correct parameter
		msg.param4 = 0; // TODO use correct parameter
		msg.x = (float) latitude;
		msg.y = (float) longitude;
		msg.z = (float) d;
		msg.autocontinue = 1; // TODO use correct parameter
		msg.target_system = 1;
		msg.target_component = 1;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void sendGuidedVelocity(Drone drone, double xVel, double yVel, double zVel) {
		msg_mission_item msg = new msg_mission_item(drone.getGCS().getId());
		msg.seq = 0;
		msg.current = 2; // TODO use guided mode enum
		msg.frame = MAV_FRAME.MAV_FRAME_GLOBAL;
		msg.command =  91; // MAV_CMD_NAV_VELOCITY
		msg.param1 = 0; // TODO use correct parameter
		msg.param2 = 0; // TODO use correct parameter
		msg.param3 = 0; // TODO use correct parameter
		msg.param4 = 0; // TODO use correct parameter
		msg.x = (float) (xVel );
		msg.y = (float) (yVel);
		msg.z = (float) (zVel);
		msg.autocontinue = 1; // TODO use correct parameter
		msg.target_system = 1;
		msg.target_component = 1;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void changeFlightMode(Drone drone, ApmModes mode) {
		msg_set_mode msg = new msg_set_mode(drone.getGCS().getId());
		msg.target_system = 1;
		msg.base_mode = 1; // TODO use meaningful constant
		msg.custom_mode = mode.getNumber();
		drone.getMavClient().sendMavPacket(msg.pack());
	}
}
