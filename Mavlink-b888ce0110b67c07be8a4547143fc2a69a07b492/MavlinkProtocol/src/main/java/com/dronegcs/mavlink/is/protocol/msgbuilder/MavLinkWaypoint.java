package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_ack;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_count;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_request;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_request_list;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_set_current;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_MISSION_RESULT;

public class MavLinkWaypoint {

	public static void sendAck(Drone drone) {
		msg_mission_ack msg = new msg_mission_ack(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.type = MAV_MISSION_RESULT.MAV_MISSION_ACCEPTED;
		drone.getMavClient().sendMavPacket(msg.pack());

	}

	public static void requestWayPoint(Drone drone, int index) {
		msg_mission_request msg = new msg_mission_request(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.seq = (short) index;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void requestWaypointsList(Drone drone) {
		msg_mission_request_list msg = new msg_mission_request_list(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void sendWaypointCount(Drone drone, int count) {
		msg_mission_count msg = new msg_mission_count(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.count = (short) count;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

	public static void sendSetCurrentWaypoint(Drone drone, short i) {
		msg_mission_set_current msg = new msg_mission_set_current(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;
		msg.seq = i;
		drone.getMavClient().sendMavPacket(msg.pack());
	}

}
