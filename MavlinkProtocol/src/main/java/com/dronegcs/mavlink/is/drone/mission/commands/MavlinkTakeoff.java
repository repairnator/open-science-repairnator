package com.dronegcs.mavlink.is.drone.mission.commands;

import java.io.Serializable;
import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.*;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_FRAME;

public class MavlinkTakeoff extends DroneMissionCMD implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1839449676706734507L;

	public static final double DEFAULT_TAKEOFF_ALTITUDE = 10.0;

	private double finishedAlt = 10;

	public MavlinkTakeoff(MavlinkTakeoff item) {
		super(item);
		finishedAlt = item.finishedAlt;
	}

	public MavlinkTakeoff(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission);
		unpackMAVMessage(msg);
	}

	public MavlinkTakeoff(DroneMission droneMission, double altitude) {
		super(droneMission);
		finishedAlt = altitude;
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = super.packMissionItem(drone);
		msg_mission_item mavMsg = list.get(0);
		mavMsg.command = MAV_CMD.MAV_CMD_NAV_TAKEOFF;
		mavMsg.frame = MAV_FRAME.MAV_FRAME_GLOBAL_RELATIVE_ALT;
		mavMsg.z = (float) finishedAlt;
		return list;
	}

	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
		finishedAlt = mavMsg.z;
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.TAKEOFF;
	}

	public double getFinishedAlt() {
		return finishedAlt;
	}

	public void setFinishedAlt(double finishedAlt) {
		this.finishedAlt = finishedAlt;
	}

	@Override
	public MavlinkTakeoff clone(DroneMission droneMission) {
		MavlinkTakeoff mavlinkTakeoff = new MavlinkTakeoff(this);
		mavlinkTakeoff.setDroneMission(droneMission);
		return mavlinkTakeoff;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MavlinkTakeoff{" +
				super.toString() +
				", finishedAlt=" + finishedAlt +
				'}';
	}
}