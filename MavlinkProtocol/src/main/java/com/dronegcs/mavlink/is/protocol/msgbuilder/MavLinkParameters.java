package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.parameters.Parameter;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_param_request_list;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_param_request_read;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_param_set;

public class MavLinkParameters {
	public static void requestParametersList(Drone drone) {
		msg_param_request_list msg = new msg_param_request_list(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void sendParameter(Drone drone, Parameter parameter) {
		if (parameter.getType() == -1)
			throw new RuntimeException("Type is missing for parameter " + parameter.getName());
		msg_param_set msg = new msg_param_set(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.setParam_Id(parameter.getName());
 		msg.param_type = (byte) parameter.getType();
//		msg.param_value = (float) parameter.getValue();
		msg.param_value = parameter.getValue().floatValue();
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void readParameter(Drone drone, String name) {
		msg_param_request_read msg = new msg_param_request_read(drone.getGCS().getId());
		msg.param_index = -1;
		msg.target_system = 1;
		msg.target_component = 1;
		msg.setParam_Id(name);
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void readParameter(Drone drone, int index) {
		msg_param_request_read msg = new msg_param_request_read(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.param_index = (short) index;
		drone.getMavClient().sendMavPacket(msg.pack());
	}
}
