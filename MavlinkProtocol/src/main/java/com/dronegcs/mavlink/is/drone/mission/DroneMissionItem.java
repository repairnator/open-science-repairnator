package com.dronegcs.mavlink.is.drone.mission;

import java.util.ArrayList;
import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_FRAME;

public abstract class DroneMissionItem implements ConvertMavlinkVisited, Comparable<DroneMissionItem>  {

	protected DroneMission droneMission;

	public DroneMissionItem(DroneMission droneMission) {
		this.droneMission = droneMission;
	}

	public DroneMissionItem(DroneMissionItem item) {
		this(item.droneMission);
	}

	/**
	 * Return a new list (one or more) of MAVLinkMessage msg_mission_item that
	 * represent this DroneMissionItem
	 * 
	 * @return
	 */
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = new ArrayList<msg_mission_item>();
		msg_mission_item mavMsg = new msg_mission_item(drone.getGCS().getId());
		list.add(mavMsg);
		mavMsg.autocontinue = 1;
		mavMsg.target_component = 1;
		mavMsg.target_system = 1;
		mavMsg.frame = MAV_FRAME.MAV_FRAME_GLOBAL_RELATIVE_ALT;
		return list;
	}

	/**
	 * Gets data from MAVLinkMessage msg_mission_item for this DroneMissionItem
	 * 
	 * @return
	 */
	public abstract void unpackMAVMessage(msg_mission_item mavMsg);

	public abstract MissionItemType getType();

	public DroneMission getDroneMission() {
		return droneMission;
	}
	
	public void setDroneMission(DroneMission droneMission) {
		this.droneMission = droneMission;
	}

	@Override
	public int compareTo(DroneMissionItem another) {
		return droneMission.getOrder(this) - droneMission.getOrder(another);
	}
	
	public abstract DroneMissionItem clone(DroneMission droneMission);

	@Override
	public String toString() {
		return "DroneMissionItem{" +
				getType() +
				'}';
	}
}