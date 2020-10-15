package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_command_ack;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_command_long;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD_ACK;

public class MavLinkCalibration {

	public static void sendAccelCalibrationAckMessage(int count, Drone drone) {
		msg_command_ack msg = new msg_command_ack(drone.getGCS().getId());
		msg.command = (short) count;
		msg.result = MAV_CMD_ACK.MAV_CMD_ACK_OK;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void sendStartAccelCalibrationMessage(Drone drone) {
		msg_command_long msg = new msg_command_long(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;

		msg.command = MAV_CMD.MAV_CMD_PREFLIGHT_CALIBRATION;
		msg.param1 = 0;
		msg.param2 = 0;
		msg.param3 = 0;
		msg.param4 = 0;
		msg.param5 = 1;
		msg.param6 = 0;
		msg.param7 = 0;
		msg.confirmation = 0;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void sendStartLevelCalibrationMessage(Drone drone) {
		msg_command_long msg = new msg_command_long(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;

		msg.command = MAV_CMD.MAV_CMD_PREFLIGHT_CALIBRATION;
		msg.param1 = 0;
		msg.param2 = 0;
		msg.param3 = 0;
		msg.param4 = 0;
		msg.param5 = 2;
		msg.param6 = 0;
		msg.param7 = 0;
		msg.confirmation = 0;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void sendStartMagnometerCalibrationMessage(Drone drone) {
		msg_command_long msg = new msg_command_long(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;

		msg.command = MAV_CMD.MAV_CMD_PREFLIGHT_CALIBRATION;
		msg.param1 = 0;
		msg.param2 = 1;
		msg.param3 = 0;
		msg.param4 = 0;
		msg.param5 = 0;
		msg.param6 = 0;
		msg.param7 = 0;
		msg.confirmation = 0;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

}
