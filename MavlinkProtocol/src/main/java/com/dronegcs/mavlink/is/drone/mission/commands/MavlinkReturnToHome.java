package com.dronegcs.mavlink.is.drone.mission.commands;

import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.*;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_FRAME;

public class MavlinkReturnToHome extends DroneMissionCMD {

	private double returnAltitude;

	public MavlinkReturnToHome(MavlinkReturnToHome item) {
		super(item);
		returnAltitude = item.returnAltitude;
	}

	public MavlinkReturnToHome(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission);
		unpackMAVMessage(msg);
	}

	public MavlinkReturnToHome(DroneMission droneMission) {
		super(droneMission);
		returnAltitude = 0.0;
	}

	public double getHeight() {
		return returnAltitude;
	}

	public void setHeight(double altitude) {
		returnAltitude = altitude;
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = super.packMissionItem(drone);
		msg_mission_item mavMsg = list.get(0);
		mavMsg.command = MAV_CMD.MAV_CMD_NAV_RETURN_TO_LAUNCH;
		mavMsg.frame = MAV_FRAME.MAV_FRAME_GLOBAL_RELATIVE_ALT;
		mavMsg.z = (float) returnAltitude;
		return list;
	}

	@Override
	public void unpackMAVMessage(msg_mission_item mavMessageItem) {
		returnAltitude = mavMessageItem.z;
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.RTL;
	}
	
	@Override
	public MavlinkReturnToHome clone(DroneMission droneMission) {
		MavlinkReturnToHome mavlinkReturnToHome = new MavlinkReturnToHome(this);
		mavlinkReturnToHome.setDroneMission(droneMission);
		return mavlinkReturnToHome;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MavlinkReturnToHome{" +
				super.toString() +
				", returnAltitude=" + returnAltitude +
				'}';
	}
}
