package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.commands.MavlinkEpmGripper;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_command_long;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_digicam_control;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.geo_tools.Coordinate;

public class MavLinkROI {
	public static void setROI(Drone drone, Coordinate coord) {
		msg_command_long msg = new msg_command_long(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.command = MAV_CMD.MAV_CMD_DO_SET_ROI;

		msg.param5 = (float) coord.getX();
		msg.param6 = (float) coord.getY();
		msg.param7 = (float) 0.0;

		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void resetROI(Drone drone) {
		msg_command_long msg = new msg_command_long(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.command = MAV_CMD.MAV_CMD_DO_SET_ROI;

		msg.param5 = (float) 0.0;
		msg.param6 = (float) 0.0;
		msg.param7 = (float) 0.0;

		drone.getMavClient().sendMavPacket(msg.pack());
	}
	
	public static void triggerCamera(Drone drone){
		msg_digicam_control msg = new msg_digicam_control(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.shot = 1;
		drone.getMavClient().sendMavPacket(msg.pack());
	}
	
	public static void empCommand(Drone drone, boolean release) {
		msg_command_long msg = new msg_command_long(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.command = MavlinkEpmGripper.MAV_CMD_DO_GRIPPER;
		msg.param2 = release ? MavlinkEpmGripper.GRIPPER_ACTION_RELEASE : MavlinkEpmGripper.GRIPPER_ACTION_GRAB;

		drone.getMavClient().sendMavPacket(msg.pack());
	}
	
}
