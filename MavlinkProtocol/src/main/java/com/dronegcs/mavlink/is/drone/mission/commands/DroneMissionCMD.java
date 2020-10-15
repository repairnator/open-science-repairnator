package com.dronegcs.mavlink.is.drone.mission.commands;

import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.mission.DroneMissionItem;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;

public abstract class DroneMissionCMD extends DroneMissionItem {

	public DroneMissionCMD(DroneMission droneMission) {
		super(droneMission);
	}

	public DroneMissionCMD(DroneMissionCMD item) {
		super(item);
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		return super.packMissionItem(drone);
	}

	@Override
	public String toString() {
		return "DroneMissionCMD{" +
				super.toString() +
				'}';
	}
}